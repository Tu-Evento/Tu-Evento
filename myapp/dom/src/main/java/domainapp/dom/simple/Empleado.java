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
package domainapp.dom.simple;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.eventbus.PropertyDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.util.ObjectContracts;

import domainapp.dom.persona.PersonaSimple;
import domainapp.dom.tipodocumento.TipoDoc;

@javax.jdo.annotations.PersistenceCapable(
		identityType = IdentityType.DATASTORE, 
		schema = "simple", 
		table = "Empleado")
@javax.jdo.annotations.DatastoreIdentity(
		strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, 
		column = "id")
@javax.jdo.annotations.Version(
		strategy = VersionStrategy.DATE_TIME, 
		column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(
				name = "findByNombre", language = "JDOQL", 
				value = "SELECT "
						+ "FROM domainapp.dom.simple.Empleado " 
						+ "WHERE name.indexOf(:name) >= 0 "), 
		@javax.jdo.annotations.Query(
				name = "buscarPorRol", language = "JDOQL",
				value = "SELECT"
						+ "FROM domainapp.dom.simple.Empleado "
						+ "WHERE rol.indexOf(:rol)>= 0 ")
		})
@javax.jdo.annotations.Unique(name = "Empleado_name_UNQ", members = { "name" })
@DomainObject(publishing = Publishing.ENABLED, auditing = Auditing.ENABLED)
public class Empleado extends PersonaSimple implements Comparable<Empleado> {

	// region > title
	public TranslatableString title() {
		return TranslatableString.tr("Empleado: {apellido}, {name} - {rol}", "name", getName(), "apellido",
				getApellido(), "rol", getRol());
	}
	// endregion

	// region > constructor
	public Empleado(final String name, final String apellido, final String documento, final TipoDoc tipo, final String cuil,
			final String direccion, final String rol) {
		setName(name);
		setApellido(apellido);
		setDocumento(documento);
		setTipoDoc(tipo);
		setCuil(cuil);
		setDireccion(direccion);
		setRol(rol);
	}
	// endregion

	// region > name (read-only property)
	public static final int NAME_LENGTH = 40;


	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "true", length = NAME_LENGTH)
	private String rol;
	@Property(editing = Editing.DISABLED)
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	// endregion

	// region > updateName (action)
	public static class UpdateNameDomainEvent extends ActionDomainEvent<Empleado> {
	}

	@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, semantics = SemanticsOf.IDEMPOTENT, domainEvent = UpdateNameDomainEvent.class)
	public Empleado updateName(@ParameterLayout(named = "Name") final String name,
			@ParameterLayout(named = "Apellido") final String apellido,
			@ParameterLayout(named = "Tipo Documento") final TipoDoc tipo,
			@ParameterLayout(named = "Documento") final String documento,
			@ParameterLayout(named = "Cuil") final String cuil,
			@ParameterLayout(named = "Direccion") final String direccion,
			@ParameterLayout(named = "Rol") final String rol) {
		setName(name);
		setApellido(apellido);
		setTipoDoc(tipo);
		setDocumento(documento);
		setCuil(cuil);
		setDireccion(direccion);
		setRol(rol);
		return this;
	}

	public String default0UpdateName() {
		return getName();
	}

	public String default1UpdateName() {
		return getApellido();
	}
	
	public TipoDoc default2UpdateName() {
		return getTipo();
	}

	public String default3UpdateName() {
		return getDocumento();
	}

	public String default4UpdateName() {
		return getCuil();
	}

	public String default5UpdateName() {
		return getDireccion();
	}

	public String default6UpdateName() {
		return getRol();
	}

	public TranslatableString validate0UpdateName(final String name) {
		return name != null && name.contains("!") ? TranslatableString.tr("Exclamation mark is not allowed") : null;
	}

	// endregion

	// region > notes (editable property)
	public static final int NOTES_LENGTH = 4000;

	public static class NotesDomainEvent extends PropertyDomainEvent<Empleado, String> {
	}

	@javax.jdo.annotations.Column(allowsNull = "true", length = NOTES_LENGTH)
	private String notes;

	@Property(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, domainEvent = NotesDomainEvent.class)
	public String getNotes() {
		return notes;
	}

	public void setNotes(final String notes) {
		this.notes = notes;
	}
	// endregion

	// region > delete (action)
	public static class DeleteDomainEvent extends ActionDomainEvent<Empleado> {
	}

	@Action(domainEvent = DeleteDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
	public void delete() {
		final String title = titleService.titleOf(this);
		messageService.informUser(String.format("'%s' deleted", title));
		repositoryService.remove(this);
	}

	// endregion

	// region > toString, compareTo
	@Override
	public String toString() {
		return ObjectContracts.toString(this, "name");
	}

	@Override
	public int compareTo(final Empleado other) {
		return ObjectContracts.compare(this, other, "name");
	}

	// endregion

	// region > injected dependencies

	@javax.inject.Inject
	RepositoryService repositoryService;

	@javax.inject.Inject
	TitleService titleService;

	@javax.inject.Inject
	MessageService messageService;

	// endregion

}
