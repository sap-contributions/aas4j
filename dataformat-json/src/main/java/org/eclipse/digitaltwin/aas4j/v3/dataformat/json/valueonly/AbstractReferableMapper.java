package org.eclipse.digitaltwin.aas4j.v3.dataformat.json.valueonly;

import java.util.Iterator;

import org.eclipse.digitaltwin.aas4j.v3.model.Referable;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public abstract class AbstractReferableMapper<T extends Referable> extends AbstractMapper<T> {

    /**
     * @param element     the submodel element that has to be mapped.
     * @param idShortPath the idShort path is a dot separated chain of idShorts, that can be used in case of
     *                    troubleshooting.
     */
    protected AbstractReferableMapper(T element, String idShortPath) {
        super(element, idShortPath);
    }

    JsonNode asValueNode(JsonNode value) {
        return JsonNodeFactory.instance.objectNode().set(element.getIdShort(), value);
    }

    static JsonNode valueFromNode(String msg, String idShortPath, JsonNode valueOnly) {
        Iterator<String> fieldNames = valueOnly.fieldNames();
        if (!fieldNames.hasNext()) {
            // throw exception as value-only nodes must have exactly one field!
            throw new ValueOnlySerializationException(
                    msg + " at idShort path '" + idShortPath +
                            "', as the passed value does have no fields!", idShortPath);
        }
        String fieldName = fieldNames.next();
        if (fieldNames.hasNext()) {
            // throw exception as value-only nodes must have exactly one field!
            throw new ValueOnlySerializationException(
                    msg + " at idShort path '" + idShortPath +
                            "', as the passed value has more than one field!", idShortPath);
        }
        return valueOnly.get(fieldName);
    }
}
