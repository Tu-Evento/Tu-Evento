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
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.dom.estado.Estado;
import domainapp.dom.tipocategoria.TipoCategoria;



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
			@ParameterLayout(named="Organización") String organizacion,
			@ParameterLayout(named="Categoria") TipoCategoria categoria,
			@ParameterLayout(named="Estado") Estado estado,
			@ParameterLayout(named="Dirección") String direccion,
			@ParameterLayout(named="Cuit") Integer cuit,
			@ParameterLayout(named="Email") String email,
			@ParameterLayout(named="Teléfono") Integer telefono,
			@ParameterLayout(named="Contacto") String contacto
			){
		final Proveedores obj = repositoryService.instantiate(Proveedores.class);
		obj.setOrganizacion(organizacion);
		obj.setCategoria(categoria);
		obj.setEstado(estado);
		obj.setDireccion(direccion);
		obj.setCuit(cuit);
		obj.setEmail(email);
		obj.setTelefono(telefono);
		obj.setContacto(contacto);
		repositoryService.persist(obj);
		return obj;
	}
	
	@ActionLayout(named = "Proveedores")
    @MemberOrder(name = "Listar", sequence = "3")
    public List<Proveedores> listar() {
        return repositoryService.allInstances(Proveedores.class);
    }
	
	@ActionLayout(named = "Buscar por Categoria")
	@MemberOrder(name = "Listar", sequence = "3.1")
	public List<Proveedores> buscarPorCategoria(final TipoCategoria categoria){
		return repositoryService.allMatches(new QueryDefault<>(Proveedores.class,"buscarPorCategoria","categoria",categoria));
	}

	@Inject
    RepositoryService repositoryService;

    @Inject
    FactoryService factoryService;
}
