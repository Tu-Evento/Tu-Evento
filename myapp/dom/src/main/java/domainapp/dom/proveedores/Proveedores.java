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
import org.apache.isis.applib.util.ObjectContracts;

import domainapp.dom.empleado.Empleado;
import domainapp.dom.empleado.Empleado.EditarDomainEvent;
import domainapp.dom.empleado.Empleado.EliminarDomainEvent;
import domainapp.dom.estado.Estado;
import domainapp.dom.tipocategoria.TipoCategoria;
import domainapp.dom.tipodocumento.TipoDocumento;



@PersistenceCapable(
		identityType = IdentityType.DATASTORE,
		schema = "TuEvento",
		table="Proveedores"
		)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="proveedores_id")
public class Proveedores implements Comparable<Proveedores>{
	
	public TranslatableString title() { return TranslatableString.tr("Proveedores: {organizacion} - {categoria}", 
			"organizacion",getOrganizacion(), "categoria",getCategoria());}
	
	//Nombre de Organización
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	private String organizacion;
	public String getOrganizacion(){
		return organizacion;
	}
	public void setOrganizacion(String organizacion){
		this.organizacion = organizacion;
	}
	
	//Categoria del servicio del Proveedor
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	private TipoCategoria categoria;
	public TipoCategoria getCategoria(){
		return categoria;
	}
	public void setCategoria(TipoCategoria categoria){
		this.categoria = categoria;
	}
	
	//Disponibilidad del proveedor para los Eventos
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	private Estado estado;
	public Estado getEstado(){
		return estado;
	}
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	//Dirección de Organización
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	private String direccion;
	public String getDireccion(){
		return direccion;
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	
	//CUIT de Organizacion
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	private Integer cuit;
	public Integer getCuit(){
		return cuit;
	}
	public void setCuit(Integer cuit){
		this.cuit = cuit;
	}
	
	//Email de Organización
	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	private String email;
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	//Número de Teléfono
	@MemberOrder(sequence = "7")
	@Column(allowsNull = "false")
	private Integer telefono;
	public Integer getTelefono(){
		return telefono;
	}
	public void setTelefono(Integer telefono){
		this.telefono = telefono;
	}
	
	//Contacto de Referencia de la Organización
	@MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	private String contacto;
	public String getContacto(){
		return contacto;
	}
	public void setContacto(String contacto){
		this.contacto = contacto;
	}
	
	public static class EditarDomainEvent extends ActionDomainEvent<Proveedores> {	}

	@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, 
			semantics = SemanticsOf.IDEMPOTENT, domainEvent = EditarDomainEvent.class)
	public Proveedores editar(
			@ParameterLayout(named = "Organización") final String organizacion,
			@ParameterLayout(named = "Categoria") final TipoCategoria categoria,
			@ParameterLayout(named = "Estado") final Estado estado,
			@ParameterLayout(named = "Dirección") final String direccion,
			@ParameterLayout(named = "CUIT") final Integer cuit,
			@ParameterLayout(named = "Email") final String email,
			@ParameterLayout(named = "Teléfono") final Integer telefono,
			@ParameterLayout(named = "Contacto") final String contacto 
			){
		setOrganizacion(organizacion);
		setCategoria(categoria);
		setEstado(estado);
		setDireccion(direccion);
		setCuit(cuit);
		setEmail(email);
		setTelefono(telefono);
		setContacto(contacto);
		return this;
	}

	public String default0Editar() {
		return getOrganizacion();
	}

	public TipoCategoria default1Editar() {
		return getCategoria();
	}
	
	public Estado default2Editar() {
		return getEstado();
	}

	public String default3Editar() {
		return getDireccion();
	}

	public Integer default4Editar() {
		return getCuit();
	}

	public String default5Editar() {
		return getEmail();
	}

	public Integer default6Editar() {
		return getTelefono();
	}
	
	public String default7Editar(){
		return getContacto();
	}
	
	
	// region > delete (action)
	public static class EliminarDomainEvent extends ActionDomainEvent<Proveedores> {}
	
	@Action(domainEvent = EliminarDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
 	public void eliminar() {
 		final String title = titleService.titleOf(this);
 		messageService.informUser(String.format("'%s' eliminado", title));
 		repositoryService.remove(this);
 	}

	@Override
	public int compareTo(Proveedores o) {
		return ObjectContracts.compare(o, this, "organización");
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
