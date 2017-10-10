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
import org.apache.isis.applib.util.ObjectContracts;

import domainapp.dom.estado.Estado;
import domainapp.dom.persona.Persona;
import domainapp.dom.tipodocumento.TipoDocumento;

@PersistenceCapable(
		identityType = IdentityType.DATASTORE,
		schema = "TuEvento",
		table="Contactos"
		)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="contacto_id")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(
            name = "listarContactos", language = "JDOQL",
            value = "SELECT "
                    + "FROM domainapp.dom.TuEvento.Contactos"),
	@javax.jdo.annotations.Query(
			name="buscarPorTipoDeContacto", language="JDOQL",
			value="SELECT "
				+"FROM domainapp.dom.TuEvento.Contactos "
				+"WHERE tipoContacto == :tipoContacto"),
	@javax.jdo.annotations.Query(
			name = "buscarContactoProveedor", language = "JDOQL",
			value = "SELECT "
			+ "FROM domainapp.dom.TuEvento.Contactos "
			+ "WHERE estado == 'Activo' && tipoContacto == 'Contacto_Proveedor'"),
	@javax.jdo.annotations.Query(
			name = "buscarContactoEvento", language = "JDOQL",
			value = "SELECT "
			+ "FROM domainapp.dom.TuEvento.Contactos "
			+ "WHERE estado == 'Activo' && tipoContacto == 'Contacto_Evento'"),
	@javax.jdo.annotations.Query(
			name = "listarContactosActivos", language = "JDOQL",
			value = "SELECT  "
					+ "FROM domainapp.dom.TuEvento.Contactos "
					+ "WHERE estado == 'Activo' ")
})
public class Contacto extends Persona implements Comparable<Contacto>{

	public TranslatableString title() {
		return TranslatableString.tr("{apellido}", "apellido", this.getApellido());
    }
	
	@MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	private TipoContacto tipoContacto;
	public TipoContacto getTipoContacto() {
		return tipoContacto;
	}
	public void setTipoContacto(TipoContacto tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	
	//Continuidad de representación del contacto a proveedor o al Evento
	@MemberOrder(sequence = "9")
	@Column(allowsNull = "false")
	private Estado estado;
	public Estado getEstado(){
		return estado;
	}
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	
	//Editar
	public static class EditarDomainEvent extends ActionDomainEvent<Contacto> {	}

	@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, 
			semantics = SemanticsOf.IDEMPOTENT, domainEvent = EditarDomainEvent.class)
	public Contacto editar(
			@ParameterLayout(named="Nombre/s") final String nombre,
            @ParameterLayout(named="Apellido/s") final String apellido,
            @ParameterLayout(named="Tipo de Documento") final TipoDocumento tipoDocumento,
            @ParameterLayout(named="Nº Documento") final Integer nroDocumento,
            @ParameterLayout(named="Dirección") final String direccion,
            @ParameterLayout(named = "Teléfono") final Integer telefono,
			@ParameterLayout(named = "Email") final String email,
			@ParameterLayout(named="Tipo de Contacto") final TipoContacto tipoContacto,
			@ParameterLayout(named="Estado") final Estado estado
			){
		setNombre(nombre);
        setApellido(apellido);
        setTipoDocumento(tipoDocumento);
        setNroDocumento(nroDocumento);
        setDireccion(direccion);
        setTelefono(telefono);
        setEmail(email);
        setTipoContacto(tipoContacto);
        setEstado(estado);
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

	public String default4Editar() {
		return getDireccion();
	}

	public Integer default5Editar() {
		return getTelefono();
	}
	
	public String default6Editar() {
		return getEmail();
	}

	public TipoContacto default7Editar(){
		return getTipoContacto();
	}
	
	public Estado default8Editar(){
		return getEstado();
	}
	
	
	// region > delete (action)
	public static class EliminarDomainEvent extends ActionDomainEvent<Contacto> {}
	
	@Action(domainEvent = EliminarDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
 	public void eliminar() {
 		final String title = titleService.titleOf(this);
 		messageService.informUser(String.format("'%s' eliminado", title));
 		repositoryService.remove(this);
 	}
	//end-region > delete (action)
	
	@Override
	public int compareTo(Contacto o) {
		// TODO Apéndice de método generado automáticamente
		return ObjectContracts.compare(o, this, "nombre");
	}
	
	
	// region > injected dependencies

	@javax.inject.Inject
	RepositoryService repositoryService;

	@javax.inject.Inject
	TitleService titleService;

	@javax.inject.Inject
	MessageService messageService;

	// endregion

}
