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

package domainapp.dom.contacto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.dom.estado.Estado;
import domainapp.dom.personal.Personal;
import domainapp.dom.tipodocumento.TipoDocumento;

@DomainService(
        repositoryFor = Contacto.class,
        nature = NatureOfService.VIEW
)
@DomainServiceLayout(
        menuOrder = "20"
)
public class ContactoServicio {

	@ActionLayout(named = "Contacto")
    @MemberOrder(name = "Crear", sequence = "4")
    public Contacto create(
            @ParameterLayout(named="Nombre/s") final String nombre,
            @ParameterLayout(named="Apellido/s") final String apellido,
            @ParameterLayout(named="Tipo de Documento") final TipoDocumento tipoDocumento,
            @ParameterLayout(named="Nº Documento") final Integer nroDocumento,
            @ParameterLayout(named="Dirección") final String direccion,
            @ParameterLayout(named = "Teléfono") final Integer telefono,
			@ParameterLayout(named = "Email") final String email,
			@ParameterLayout(named="Tipo de Contacto") final TipoContacto tipoContacto,
			@ParameterLayout(named="Estado") final Estado estado
    ) {
        final Contacto obj = repositoryService.instantiate(Contacto.class);
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        obj.setTipoDocumento(tipoDocumento);
        obj.setNroDocumento(nroDocumento);
        obj.setDireccion(direccion);
        obj.setTelefono(telefono);
        obj.setEmail(email);
        obj.setTipoContacto(tipoContacto);
        obj.setEstado(estado);
        repositoryService.persist(obj);
        return obj;
    }
	public TipoDocumento default2Create(){
		return TipoDocumento.DNI;
	}
	public Estado default8Create(){
		return Estado.Activo;
	}
	
	
	@ActionLayout(named = "Contacto")
    @MemberOrder(name = "Listar", sequence = "4")
    public List<Contacto> listarContactos() {
        return repositoryService.allInstances(Contacto.class);
    }
	
	@ActionLayout(named = "Buscar por Tipo de Contacto")
	@MemberOrder(name = "Listar", sequence = "4.1")
	public List<Contacto> buscarPorTipoDeContacto(final TipoContacto tipoContacto){
		return repositoryService.allMatches(new QueryDefault<>(Contacto.class,"buscarPorTipoDeContacto","tipoContacto",tipoContacto));
	}
	
	@ActionLayout(named = "Listar Contactos Activos")
	@MemberOrder(name = "Listar", sequence = "4.2")
	public List<Contacto> listarContactosActivos(){
		return repositoryService.allMatches(new QueryDefault<>(Contacto.class,"listarContactosActivos"));
	}
	
	@ActionLayout(hidden=Where.EVERYWHERE)
	public List<Contacto> buscarContactoProveedor(){
		return repositoryService.allMatches(new QueryDefault<>(Contacto.class, "buscarContactoProveedor"));
	}
	@ActionLayout(hidden=Where.EVERYWHERE)
	public List<Contacto> buscarContactoEvento(){
		return repositoryService.allMatches(new QueryDefault<>(Contacto.class, "buscarContactoEvento"));
	}
	
	
	@Inject
    RepositoryService repositoryService;

    @Inject
    FactoryService factoryService;
}
