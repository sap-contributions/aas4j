/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
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
package org.eclipse.digitaltwin.aas4j.v3.dataformat.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.eclipse.digitaltwin.aas4j.v3.dataformat.SerializationException;
import org.eclipse.digitaltwin.aas4j.v3.model.AssetAdministrationShellDescriptor;
import org.eclipse.digitaltwin.aas4j.v3.model.Environment;
import org.eclipse.digitaltwin.aas4j.v3.model.Referable;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;
import org.eclipse.digitaltwin.aas4j.v3.model.SpecificAssetId;
import org.eclipse.digitaltwin.aas4j.v3.model.SubmodelDescriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

/**
 * Class for serializing of AAS instances.
 */
public class JsonSerializer {
    protected JsonMapper mapper;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public JsonSerializer() {
        mapper = new JsonMapperFactory().create(new SimpleAbstractTypeResolverFactory().create());
    }

    /**
     * Serialize a given object to a string
     *
     * @param aasInstance the object to serialize
     * @return the string representation
     * @throws SerializationException if serialization fails
     */
    public String write(Object aasInstance) throws SerializationException {
        try {
            return mapper.writeValueAsString(aasInstance);
        } catch (JsonProcessingException ex) {
            throw new SerializationException(
                String.format("error serializing %s", aasInstance.getClass().getSimpleName()), ex);
        }
    }

    /**
     * Converts a given object to a JSON node
     *
     * @param aasInstance the object to serialize
     * @return the JSON node representation
     * @throws IllegalArgumentException
     */
    public JsonNode toNode(Object aasInstance) {
        return mapper.valueToTree(aasInstance);
    }

    /**
     * Serializes a given object to an OutputStream using given charset
     *
     * @param out the Outputstream to serialize to
     * @param charset the Charset to use for serialization
     * @param aasInstance the object to serialize
     * @throws SerializationException if serialization fails
     */
    void write(OutputStream out, Charset charset, Object aasInstance) throws SerializationException {
        try {
            mapper.writeValue(new OutputStreamWriter(out, charset), aasInstance);
        } catch (IOException ex) {
            throw new SerializationException("error serializing " + aasInstance.getClass().getSimpleName() , ex);
        }
    }

    /**
     * Serialize a collection.
     * @param collection the collection to serialize
     * @return the string representation of the collection.
     * @throws SerializationException if serialization fails
     */
    public String write(Collection<?> collection) throws SerializationException {
        if (collection == null || collection.isEmpty()) {
            try {
                return mapper.writeValueAsString(collection);
            } catch (JsonProcessingException e) {
                throw new SerializationException("error serializing list");
            }
        }

        Class clazz = collection.iterator().next().getClass();
        try {
            return mapper.writerFor(mapper.getTypeFactory().constructCollectionType(List.class, clazz))
                .writeValueAsString(collection);
        } catch (JsonProcessingException ex) {
            throw new SerializationException("error serializing list of " + clazz.getSimpleName(), ex);
        }
    }

    /**
     * Serializes a given object to a java.io.File using given charset
     *
     * @param file the java.io.File to serialize to
     * @param aasInstance the object to serialize
     * @throws FileNotFoundException if the file does not exist
     * @throws SerializationException if serialization fails
     */
    void write(File file, Object aasInstance)
        throws FileNotFoundException,  SerializationException {
        write(new FileOutputStream(file), DEFAULT_CHARSET, aasInstance);
    }

    /**
     * Serialize a collection of referables to a JsonArray.
     * @param referables the referables to serialize
     * @return the JSON array representation of the list of referables
     */
    public JsonNode toNode(Collection<? extends Referable> referables) {
        return toNode((Object) referables);
    }

    /**
     * Serialize a referable to string.
     * @param referable the referable to serialize.
     * @return the string representation.
     * @throws SerializationException
     */
    public String writeReferable(Referable referable) throws SerializationException {
        return write(referable);
    }

    /**
     * Serialize a collection of referables
     * @param referables the collection to serialize
     * @return the string representation.
     * @throws SerializationException
     */
    public String writeReferables(List<Referable> referables) throws SerializationException {
        return write(referables);
    }

    /**
     * Serialize a reference
     * @param reference the collection to serialize
     * @return the string representation.
     * @throws SerializationException
     */
    public String writeReference(Reference reference) throws SerializationException {
        return write(reference);
    }

    /**
     * Serialize a collection of references
     * @param references the collection to serialize
     * @return the string representation
     * @throws SerializationException
     */
    public String writeReferences(List<Reference> references) throws SerializationException {
        return write(references);
    }

    /**
     * Serialize a specific asset ID
     * @param specificAssetId the asset ID to serialize
     * @return the string representation.
     * @throws SerializationException
     */
    public String writeSpecificAssetId(SpecificAssetId specificAssetId) throws SerializationException {
        return write(specificAssetId);
    }

    /**
     * Serialize a list of specific asset IDs
     * @param specificAssetIds the asset IDs to serialize
     * @return the string representation.
     * @throws SerializationException
     */
    public String writeSpecificAssetIds(List<SpecificAssetId> specificAssetIds) throws SerializationException {
        return write(specificAssetIds);
    }

    public String writeSubmodelDescriptor(SubmodelDescriptor submodelDescriptor) throws SerializationException {
        return write(submodelDescriptor);
    }


    public String writeSubmodelDescriptors(List<SubmodelDescriptor> submodelDescriptors) throws SerializationException {
        return write(submodelDescriptors);
    }

    public String writeAssetAdministrationShellDescriptor(AssetAdministrationShellDescriptor assetAdministrationShellDescriptor) throws SerializationException {
        return write(assetAdministrationShellDescriptor);
    }

    public String writeAssetAdministrationShellDescriptors(List<AssetAdministrationShellDescriptor> assetAdministrationShellDescriptors) throws SerializationException {
        return write(assetAdministrationShellDescriptors);
    }
}
