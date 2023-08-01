/*
 * Copyright (C) 2023 SAP SE or an SAP affiliate company.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.digitaltwin.aas4j.v3.dataformat.json.valueonly;

import java.util.Iterator;
import java.util.List;

import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElement;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelElementCollection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * SubmodelElementCollection is serialized as named JSON object with ${SubmodelElementCollection/idShort} as the name of
 * the containing JSON property. The elements contained within the struct are serialized according to their respective
 * type with ${SubmodelElement/idShort} as the name of the containing JSON property.
 */
class ElementsCollectionMapper extends AbstractListMapper<SubmodelElementCollection> {
    ElementsCollectionMapper(SubmodelElementCollection elementCollection, List<SubmodelElement> values, String idShortPath) {
        super(elementCollection, values, idShortPath);
    }

    @Override
    public JsonNode toJson() throws ValueOnlySerializationException {
        ObjectNode elementsNode = JsonNodeFactory.instance.objectNode();
        for (SubmodelElement submodelElement : element.getValue()) {
            String idShort = submodelElement.getIdShort();
            if(elementsNode.has(idShort)) {
                throw new ValueOnlySerializationException("Duplicated idShort name '" + idShort +
                    "' under idShort path '" + idShortPath + "'.", idShortPath);
            }
            ValueOnlyMapper mapper = ValueOnlyMapper.createMapper(submodelElement, idShortPath + "." + idShort);
            if(mapper == null) {
                // This type of submodel elements are not serialized in value-only format.
                continue;
            }
            JsonNode mapperNode = mapper.toJson();
            if (elementsNode.isObject()) {
                elementsNode.setAll((ObjectNode) mapperNode);
            } else {
                elementsNode.set(idShort, mapperNode);
            }
        }
        return asValueNode(elementsNode);
    }

    @Override
    public void update(JsonNode valueOnly) throws ValueOnlySerializationException {
        if(!valueOnly.isObject()) {
            throw new ValueOnlySerializationException(
                "Cannot update the submodel elements collection at idShort path '" + idShortPath +
                "', as the corresponding value-only is not a JSON object.", idShortPath);
        }
        ObjectNode objNode = (ObjectNode) valueOnly;
        for (Iterator<String> it = objNode.fieldNames(); it.hasNext(); ) {
            String idShort = it.next();
            SubmodelElement submodelElement = findElementByIdShort(idShort);
            ValueOnlyMapper mapper = ValueOnlyMapper.createMapper(submodelElement, idShortPath + "." + idShort);
            mapper.update(objNode.get(idShort));
        }
    }

    private SubmodelElement findElementByIdShort(String idShort) throws ValueOnlySerializationException {
        for (SubmodelElement submodelElement : element.getValue()) {
            if(idShort.equals(submodelElement.getIdShort())) {
                return submodelElement;
            }
        }
        throw new ValueOnlySerializationException("Cannot find submodel element with idShort '" + idShort +
            "' at idShort path '" + idShortPath + "'.", idShortPath);
    }
}
