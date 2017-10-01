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

package domainapp.dom.locales;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

@PersistenceCapable(identityType = IdentityType.DATASTORE, 
	schema = "TuEvento", 
	table = "Locales")
@javax.jdo.annotations.DatastoreIdentity(
		strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, 
		column = "locales_id")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(
			name="listarLocales", language="JDOQL",
			value="SELECT "
				+"FROM domainapp.dom.TuEvento.Locales "
	)
})
public class Locales implements Comparable<Locales> {

	public TranslatableString title() {
		return TranslatableString.tr("Locales");
	}

	// Descripción
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	private String descripcion;
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// Capacidad del Local
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	private Integer capacidad;
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	// Capacidad del Local
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	private String direccion;
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// Capacidad del Local
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	private Condicion condicion;
	public Condicion getCondicion() {
		return condicion;
	}
	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	//region > editar
	public static class EditarDomainEvent extends ActionDomainEvent<Locales> {	}

	@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, 
			semantics = SemanticsOf.IDEMPOTENT, domainEvent = EditarDomainEvent.class)
	public Locales editar(
			@ParameterLayout(named = "Descripción") final String descripcion,
			@ParameterLayout(named = "Capacidad") final Integer capacidad,
			@ParameterLayout(named = "Dirección") final String direccion,
			@ParameterLayout(named = "Condición") final Condicion condicion 
			){
		setDescripcion(descripcion);
		setCapacidad(capacidad);
		setDireccion(direccion);
		setCondicion(condicion);
		return this;
	}

	public String default0Editar() {
		return getDescripcion();
	}

	public Integer default1Editar() {
		return getCapacidad();
	}
	
	public String default2Editar() {
		return getDireccion();
	}

	public Condicion default3Editar() {
		return getCondicion();
	}

	//end
	
	// region > delete (action)
	public static class EliminarDomainEvent extends ActionDomainEvent<Locales> {
		private static final long serialVersionUID = 1L;
	}

	@Action(domainEvent = EliminarDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
	public void eliminar() {
		final String title = titleService.titleOf(this);
		messageService.informUser(String.format("'%s' eliminado", title));
		repositoryService.remove(this);
	}
	
	//end
	
	@Override
	public int compareTo(Locales o) {
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

	// endregion

}
