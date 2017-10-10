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

package domainapp.dom.cliente;

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
import domainapp.dom.tipodocumento.TipoDocumento;

@DomainService(
        repositoryFor = Cliente.class,
        nature = NatureOfService.VIEW
)
@DomainServiceLayout(
        menuOrder = "20"
)
public class ClienteServicio {

	@ActionLayout(named = "Cliente")
    @MemberOrder(name = "Crear", sequence = "3")
    public Cliente create(
            @ParameterLayout(named="Nombre") final String nombre,
            @ParameterLayout(named="Apellido") final String apellido,
            @ParameterLayout(named="TipoDocumento") final TipoDocumento tipoDocumento,
            @ParameterLayout(named="NºDocumento") final Integer nroDocumento,
            @ParameterLayout(named="Cuil/Cuit") final Integer cuilCuit,
            @ParameterLayout(named="Direccion") final String direccion,
            @ParameterLayout(named = "Telefono") final Integer telefono,
			@ParameterLayout(named = "Email") final String email,
			@ParameterLayout(named = "TipoCliente") final TipoCliente tipoCliente,
			@ParameterLayout(named ="Contacto") final Contacto contacto
    ) {
        final Cliente obj = repositoryService.instantiate(Cliente.class);
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        obj.setTipoDocumento(tipoDocumento);
        obj.setNroDocumento(nroDocumento);
        obj.setCuilCuit(cuilCuit);
        obj.setDireccion(direccion);
        obj.setTelefono(telefono);
        obj.setEmail(email);
        obj.setTipoCliente(tipoCliente);
        obj.setContacto(contacto);
        repositoryService.persist(obj);
        return obj;
    }
	public TipoDocumento default2Create(){
		return TipoDocumento.DNI;
	}
	public TipoCliente default8Create(){
		return TipoCliente.Persona_Física;
	}
	public List<Contacto> choices9Create(){ 
		return contactoServicio.buscarContactoEvento();
	}
	
	
	@ActionLayout(named = "Clientes")
    @MemberOrder(name = "Listar", sequence = "3")
    public List<Cliente> listar() {
        return repositoryService.allInstances(Cliente.class);
    }
	
	@ActionLayout(named = "Buscar por Tipo de Cliente")
	@MemberOrder(name = "Listar", sequence = "3.1")
	public List<Cliente> buscarPorTipoDeCliente(final TipoCliente tipoCliente){
		return repositoryService.allMatches(new QueryDefault<>(Cliente.class,"buscarPorTipoDeCliente","tipoCliente",tipoCliente));
	}
	
	@Inject
    RepositoryService repositoryService;

    @Inject
    FactoryService factoryService;
    
    @Inject
    ContactoServicio contactoServicio;
}
