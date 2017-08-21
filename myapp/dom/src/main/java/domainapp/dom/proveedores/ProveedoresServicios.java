/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.dom.proveedores;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;



@DomainService(
        repositoryFor = Proveedores.class,
        nature = NatureOfService.VIEW
)
@DomainServiceLayout(
        menuOrder = "20"
)
public class ProveedoresServicios {
	
	@ActionLayout(named = "Proveedores")
    @MemberOrder(name = "Crear", sequence = "2")
	public Proveedores create(
			@ParameterLayout(named="Nombre") String nombre
			){
		final Proveedores obj = repositoryService.instantiate(Proveedores.class);
		obj.setNombre(nombre);
		repositoryService.persist(obj);
		return obj;
	}
	
	@ActionLayout(named = "Proveedores")
    @MemberOrder(name = "Listar", sequence = "3")
    public List<Proveedores> listar() {
        return repositoryService.allInstances(Proveedores.class);
    }

	@Inject
    RepositoryService repositoryService;

    @Inject
    FactoryService factoryService;
}
