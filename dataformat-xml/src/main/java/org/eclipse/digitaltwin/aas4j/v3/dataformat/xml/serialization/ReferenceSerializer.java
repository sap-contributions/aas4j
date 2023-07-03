/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 * Copyright (C) 2023 SAP SE or an SAP affiliate company. All rights reserved.
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
package org.eclipse.digitaltwin.aas4j.v3.dataformat.xml.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.eclipse.digitaltwin.aas4j.v3.model.Key;
import org.eclipse.digitaltwin.aas4j.v3.model.Reference;

public class ReferenceSerializer extends JsonSerializer<Reference> {
    private NoEntryWrapperListSerializer<Key> keyListSerializer;

    public ReferenceSerializer() {
//        this.keyListSerializer = new NoEntryWrapperListSerializer<>();
//        this.keyListSerializer.setOuterWrapper("keys");
    }

    @Override
    public void serialize(Reference reference, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ToXmlGenerator xgen = (ToXmlGenerator) gen;
//        xgen.setNextIsUnwrapped(true);
//        xgen.writeObjectFieldStart("type");
        xgen.writeString(reference.getType().name());

//        List<Key> keys = reference.getKeys();
//        this.keyListSerializer.serialize(keys, gen, serializers);
    }

}