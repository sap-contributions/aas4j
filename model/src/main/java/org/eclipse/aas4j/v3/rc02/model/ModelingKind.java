/*
Copyright (c) 2022 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package org.eclipse.aas4j.v3.rc02.model;




import org.eclipse.aas4j.v3.rc02.model.annotations.IRI;

/**
 * Enumeration for denoting whether an element is a template or an instance.
 */
@IRI("aas:ModelingKind")
public enum ModelingKind {

    /**
     * Concrete, clearly identifiable component of a certain template.
     */
    @IRI("https://admin-shell.io/aas/3/0/RC02/ModelingKind/Instance")
    INSTANCE,

    /**
     * Software element which specifies the common attributes shared by all instances of the template.
     */
    @IRI("https://admin-shell.io/aas/3/0/RC02/ModelingKind/Template")
    TEMPLATE;

}