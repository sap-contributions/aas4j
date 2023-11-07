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

package org.eclipse.digitaltwin.aas4j.v3.model.builder;


import org.eclipse.digitaltwin.aas4j.v3.model.ReferenceValue;
import org.eclipse.digitaltwin.aas4j.v3.model.RelationshipElementValue;


public abstract class RelationshipElementValueBuilder<T extends RelationshipElementValue, B extends RelationshipElementValueBuilder<T, B>>
    extends ExtendableBuilder<T, B> {

    /**
     * This function allows setting a value for first
     * 
     * @param first desired value to be set
     * @return Builder object with new value for first
     */
    public B first(ReferenceValue first) {
        getBuildingInstance().setFirst(first);
        return getSelf();
    }

    /**
     * This function allows setting a value for second
     * 
     * @param second desired value to be set
     * @return Builder object with new value for second
     */
    public B second(ReferenceValue second) {
        getBuildingInstance().setSecond(second);
        return getSelf();
    }
}