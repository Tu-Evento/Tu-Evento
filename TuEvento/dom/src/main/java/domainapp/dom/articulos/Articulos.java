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

package domainapp.dom.articulos;

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

import domainapp.dom.estado.Estado;
import domainapp.dom.proveedores.Proveedores;
import domainapp.dom.proveedores.ProveedoresServicios;
import domainapp.dom.tiposervicios.TipoServicios;

@PersistenceCapable(
		identityType = IdentityType.DATASTORE,
		schema = "TuEvento",
		table="Articulos"
		)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="articulos_id")
@javax.jdo.annotations.Queries({
	
})
public class Articulos implements Comparable<Articulos>{

	public TranslatableString title() { return TranslatableString.tr("Articulo: {descripcion} - {servicios}",
    		"nombre", getDescripcion(), "servicios", getServicios());}
	
	//ID Articulo
	
	
	//Nombre del Articulo
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	private String descripcion;
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//Servicio del Articulo
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	private TipoServicios servicios;
	public TipoServicios getServicios(){
		return servicios;
	}
	public void setServicios(TipoServicios servicios){
		this.servicios = servicios;
	}
	
	//Tipo de Articulo
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	@PropertyLayout(named="TipoArticulo")
	private TipoArticulo tipoArticulo;
	public TipoArticulo getTipoArticulo(){
		return tipoArticulo;
	}
	public void setTipoArticulo(TipoArticulo tipoArticulo){
		this.tipoArticulo = tipoArticulo;
	}
	
	//Disponibilidad en Stock
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	private Estado estado;
	public Estado getEstado(){
		return estado;
	}
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	//Cantidad en Stock
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	private int cantidad;
	public int getCantidad(){
		return cantidad;
	}
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}
	
	//Proveedores
	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	private Proveedores organizacion;
	public Proveedores getOrganizacion(){
		return organizacion;
	}
	public void setOrganizacion(Proveedores organizacion){
		this.organizacion=organizacion;
	}
	
	
	
	public static class EditarDomainEvent extends ActionDomainEvent<Articulos> { }

	@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, semantics = SemanticsOf.IDEMPOTENT, domainEvent = EditarDomainEvent.class)
	public Articulos editar(
			@ParameterLayout(named = "Descripcion") final String descripcion,
			@ParameterLayout(named = "Servicios") final TipoServicios servicios,
			@ParameterLayout(named = "TipoArticulo") final TipoArticulo tipoArticulo,
			@ParameterLayout(named = "Estado") final Estado estado,
			@ParameterLayout(named = "Cantidad") final Integer cantidad			 
			){
		setDescripcion(descripcion);
		setServicios(servicios);
		setTipoArticulo(tipoArticulo);
		setEstado(estado);
		setCantidad(cantidad);
		return this;
	}

	public String default0Editar() {
		return getDescripcion();
	}

	public TipoServicios default1Editar() {
		return getServicios();
	}
	
	public TipoArticulo default2Editar(){
		return getTipoArticulo();
	}
	
	public Estado default3Editar() {
		return getEstado();
	}

	public Integer default4Editar() {
		return getCantidad();
	}
	
		
	// region > delete (action)
	public static class EliminarDomainEvent extends ActionDomainEvent<Articulos> {
		private static final long serialVersionUID = 1L;
	}
	
	@Action(domainEvent = EliminarDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
 	public void eliminar() {
 		final String title = titleService.titleOf(this);
 		messageService.informUser(String.format("'%s' eliminado", title));
 		repositoryService.remove(this);
 	}
	
	
	@Override
	public int compareTo(Articulos o) {

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
	ProveedoresServicios proveedoresServicios;

	// endregion
}
