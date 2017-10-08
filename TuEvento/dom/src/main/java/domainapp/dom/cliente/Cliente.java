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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import domainapp.dom.contacto.Contacto;
import domainapp.dom.contacto.ContactoServicio;
import domainapp.dom.persona.Persona;
import domainapp.dom.tipodocumento.TipoDocumento;

@PersistenceCapable(
		identityType = IdentityType.DATASTORE,
		schema = "TuEvento",
		table="Clientes"
		)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="cliente_id")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(
            name = "listar", language = "JDOQL",
            value = "SELECT "
                    + "FROM domainapp.dom.TuEvento.Clientes"),
	@javax.jdo.annotations.Query(
			name="buscarPorTipoDeCliente", language="JDOQL",
			value="SELECT "
				+"FROM domainapp.dom.TuEvento.Clientes "
				+"WHERE tipoCliente == :tipoCliente")
})
public class Cliente extends Persona implements Comparable<Cliente>{

	public TranslatableString title() {
		return TranslatableString.tr("{apellido}", "apellido", this.getApellido());
    }
	
	@MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	private Integer cuilCuit;
	public Integer getCuilCuit() {
		return cuilCuit;
	}
	public void setCuilCuit(Integer cuilCuit) {
		this.cuilCuit = cuilCuit;
	}
	
	@MemberOrder(sequence = "9")
	@Column(allowsNull = "false")
	private TipoCliente tipoCliente;
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	//Contacto de Referencia de la Organización
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	@PropertyLayout(named="Contacto")
	private Contacto contacto;
	public Contacto getContacto(){
		return contacto;
	}
	public void setContacto(Contacto contacto){
		this.contacto = contacto;
	}
	
	//Editar
		public static class EditarDomainEvent extends ActionDomainEvent<Cliente> {	}

		@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, 
				semantics = SemanticsOf.IDEMPOTENT, domainEvent = EditarDomainEvent.class)
		public Cliente editar(
				@ParameterLayout(named="Nombre") final String nombre,
	            @ParameterLayout(named="Apellido") final String apellido,
	            @ParameterLayout(named="TipoDocumento") final TipoDocumento tipoDocumento,
	            @ParameterLayout(named="NºDocumento") final Integer nroDocumento,
	            @ParameterLayout(named="Cuil/Cuit") final Integer cuilCuit,
	            @ParameterLayout(named="Direccion") final String direccion,
	            @ParameterLayout(named = "Telefono") final Integer telefono,
				@ParameterLayout(named = "Email") final String email,
				@ParameterLayout(named = "TipoCliente") final TipoCliente tipoCliente,
				@ParameterLayout(named = "Contacto") final Contacto contacto
				){
			setNombre(nombre);
	        setApellido(apellido);
	        setTipoDocumento(tipoDocumento);
	        setNroDocumento(nroDocumento);
	        setCuilCuit(cuilCuit);
	        setDireccion(direccion);
	        setTelefono(telefono);
	        setEmail(email);
	        setTipoCliente(tipoCliente);
	        setContacto(contacto);
			return this;
		}

		public String default0Editar() {
			return getNombre();
		}

		public String default1Editar() {
			return getApellido();
		}
		
		public TipoDocumento default2Editar() {
			return getTipoDocumento();
		}

		public Integer default3Editar() {
			return getNroDocumento();
		}
		
		public Integer default4Editar() {
			return getCuilCuit();
		}

		public String default5Editar() {
			return getDireccion();
		}

		public Integer default6Editar() {
			return getTelefono();
		}
		
		public String default7Editar() {
			return getEmail();
		}

		public TipoCliente default8Editar(){
			return getTipoCliente();
		}
		
		public Contacto default9Editar(){
			return getContacto();
		}
		public List<Contacto> choices9Editar(){ 
			return contactoServicio.listarContactosActivos();
		}
		
		
		// region > delete (action)
		public static class EliminarDomainEvent extends ActionDomainEvent<Cliente> {}
		
		@Action(domainEvent = EliminarDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
	 	public void eliminar() {
	 		final String title = titleService.titleOf(this);
	 		messageService.informUser(String.format("'%s' eliminado", title));
	 		repositoryService.remove(this);
	 	}
	
	@Override
	public int compareTo(Cliente o) {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
	
	// region > injected dependencies

	@javax.inject.Inject
	RepositoryService repositoryService;

	@javax.inject.Inject
	TitleService titleService;

	@javax.inject.Inject
	MessageService messageService;
	
	@javax.inject.Inject
 	ContactoServicio contactoServicio;

	// endregion


}
