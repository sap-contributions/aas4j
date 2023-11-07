/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 * Copyright (c) 2023, SAP SE or an SAP affiliate company
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.eclipse.digitaltwin.aas4j.v3.model;

import org.eclipse.digitaltwin.aas4j.v3.model.annotations.IRI;
import org.eclipse.digitaltwin.aas4j.v3.model.annotations.KnownSubtypes;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultAnnotatedRelationshipElementValue;

import java.util.List;


/**
*/
@KnownSubtypes({
    @KnownSubtypes.Type(value = DefaultAnnotatedRelationshipElementValue.class)
})
public interface AnnotatedRelationshipElementValue {

    /**
     *
     * More information under https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/first
     *
     * @return Returns the ReferenceValue for the property first.
     */
    @IRI("https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/first")
    ReferenceValue getFirst();

    /**
     *
     * More information under https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/first
     *
     * @param first desired value for the property first.
     */
    void setFirst(ReferenceValue first);

    /**
     *
     * More information under https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/second
     *
     * @return Returns the ReferenceValue for the property second.
     */
    @IRI("https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/second")
    ReferenceValue getSecond();

    /**
     *
     * More information under https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/second
     *
     * @param second desired value for the property second.
     */
    void setSecond(ReferenceValue second);

    /**
     *
     * More information under
     * https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/annotation
     *
     * @return Returns the List of Strings for the property annotation.
     */
    @IRI("https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/annotation")
    List<String> getAnnotation();

    /**
     *
     * More information under
     * https://admin-shell.io/aas/3/0/AnnotatedRelationshipElementValue/annotation
     *
     * @param annotations desired value for the property annotation.
     */
    void setAnnotation(List<String> annotations);

}