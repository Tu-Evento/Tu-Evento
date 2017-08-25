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
package domainapp.dom.empleado;

import domainapp.dom.persona.Persona;
import domainapp.dom.tipocategoria.TipoCategoria;
import domainapp.dom.tipodocumento.TipoDocumento;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.CommandReification;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.util.ObjectContracts;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created for domainapp.dom.empleado on 17/06/2017.
 */
@PersistenceCapable(
		identityType = IdentityType.DATASTORE,
		schema = "TuEvento",
		table="Empleado"
		)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="empleado_id")
public class Empleado extends Persona implements Comparable<Empleado> {

    public TranslatableString title() { return TranslatableString.tr("Empleado: {apellido}, {nombre} - {rol}",
    		"apellido", getApellido(), "nombre", getNombre(), "rol", getCategoria());}

    @Column(allowsNull = "false")
	private TipoCategoria categoria;
	public TipoCategoria getCategoria(){
		return categoria;
	}
	public void setCategoria(TipoCategoria categoria){
		this.categoria = categoria;
	}
	
	
	public static class EditarDomainEvent extends ActionDomainEvent<Empleado> {	}

	@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, semantics = SemanticsOf.IDEMPOTENT, domainEvent = EditarDomainEvent.class)
	public Empleado editar(
			@ParameterLayout(named = "Nombre") final String nombre,
			@ParameterLayout(named = "Apellido") final String apellido,
			@ParameterLayout(named = "Tipo Documento") final TipoDocumento tipo,
			@ParameterLayout(named = "Documento") final Integer documento,
			@ParameterLayout(named = "Cuil") final Integer cuil,
			@ParameterLayout(named = "Direccion") final String direccion,
			@ParameterLayout(named = "Rol Trabajo") final TipoCategoria categoria) {
		setNombre(nombre);
		setApellido(apellido);
		setTipoDocumento(tipo);
		setDocumento(documento);
		setCuil(cuil);
		setDireccion(direccion);
		setCategoria(categoria);
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
		return getDocumento();
	}

	public Integer default4Editar() {
		return getCuil();
	}

	public String default5Editar() {
		return getDireccion();
	}

	public TipoCategoria default6Editar() {
		return getCategoria();
	}
    
 // region > delete (action)
 	public static class EliminarDomainEvent extends ActionDomainEvent<Empleado> {}

 	@Action(domainEvent = EliminarDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
 	public void eliminar() {
 		final String title = titleService.titleOf(this);
 		messageService.informUser(String.format("'%s' eliminado", title));
 		repositoryService.remove(this);
 	}
    
    
    @Override
    public int compareTo(Empleado o) {
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
