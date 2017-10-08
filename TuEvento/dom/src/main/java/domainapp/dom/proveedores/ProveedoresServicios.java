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

import domainapp.dom.contacto.Contacto;
import domainapp.dom.contacto.ContactoServicio;
import domainapp.dom.estado.Estado;
import domainapp.dom.tiposervicios.TipoServicios;



@DomainService(
        repositoryFor = Proveedores.class,
        nature = NatureOfService.VIEW
)
@DomainServiceLayout(
        menuOrder = "20"
)
public class ProveedoresServicios {
	
	@ActionLayout(named = "Proveedores")
    @MemberOrder(name = "Listar", sequence = "2")
    public List<Proveedores> listarProveedores() {
        return repositoryService.allInstances(Proveedores.class);
    }
	
	@ActionLayout(named = "Buscar por Servicios")
	@MemberOrder(name = "Listar", sequence = "2.1")
	public List<Proveedores> buscarPorServicios(final TipoServicios servicios){
		return repositoryService.allMatches(new QueryDefault<>(Proveedores.class,"buscarPorServicios","servicios",servicios));
	}
	
	
	
	@ActionLayout(named = "Proveedores")
    @MemberOrder(name = "Crear", sequence = "2")
	public Proveedores create(
			@ParameterLayout(named="Organización") String organizacion,
			@ParameterLayout(named="Servicios") TipoServicios servicios,
			@ParameterLayout(named="Estado") Estado estado,
			@ParameterLayout(named="Dirección") String direccion,
			@ParameterLayout(named="Cuit") Integer cuit,
			@ParameterLayout(named="Email") String email,
			@ParameterLayout(named="Teléfono") Integer telefono,
			@ParameterLayout(named="Contacto") Contacto contacto
			){
		final Proveedores obj = repositoryService.instantiate(Proveedores.class);
		obj.setOrganizacion(organizacion);
		obj.setServicios(servicios);
		obj.setEstado(estado);
		obj.setDireccion(direccion);
		obj.setCuit(cuit);
		obj.setEmail(email);
		obj.setTelefono(telefono);
		obj.setContacto(contacto);
		repositoryService.persist(obj);
		return obj;
	}
	public TipoServicios default1Create(){
		return TipoServicios.Animación;
	}
	public Estado default2Create(){
		return Estado.Activo;
	}
	public List<Contacto> choices7Create(){ 
		return contactoServicio.listarContactosActivos();
	}
	
	
		
	
	

	@Inject
    RepositoryService repositoryService;

    @Inject
    FactoryService factoryService;
    
    @Inject
    ContactoServicio contactoServicio;
}
