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
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import domainapp.dom.estado.Estado;
import domainapp.dom.tipocategoria.TipoCategoria;

@PersistenceCapable(
		identityType = IdentityType.DATASTORE,
		schema = "TuEvento",
		table="Articulos"
		)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="articulos_id")
public class Articulos implements Comparable<Articulos>{

	public TranslatableString title() { return TranslatableString.tr("Articulo: {nombre} - {categoria}",
    		"nombre", getNombre(), "categoria", getCategoria());}
	
	//Nombre del Articulo
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//Categoria del Articulo
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	private TipoCategoria categoria;
	public TipoCategoria getCategoria(){
		return categoria;
	}
	public void setCategoria(TipoCategoria categoria){
		this.categoria = categoria;
	}
	
	//Disponibilidad en Stock
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	private Estado estado;
	public Estado getEstado(){
		return estado;
	}
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	//Cantidad en Stock
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	private int cantidad;
	public int getCantidad(){
		return cantidad;
	}
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}
	
	public static class EditarDomainEvent extends ActionDomainEvent<Articulos> { }

	@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, semantics = SemanticsOf.IDEMPOTENT, domainEvent = EditarDomainEvent.class)
	public Articulos editar(
			@ParameterLayout(named = "Nombre") final String nombre,
			@ParameterLayout(named = "Categoria") final TipoCategoria categoria,
			@ParameterLayout(named = "Estado") final Estado estado,
			@ParameterLayout(named = "Cantidad") final Integer cantidad			 
			){
		setNombre(nombre);
		setCategoria(categoria);
		setEstado(estado);
		setCantidad(cantidad);
		return this;
	}

	public String default0Editar() {
		return getNombre();
	}

	public TipoCategoria default1Editar() {
		return getCategoria();
	}
	
	public Estado default2Editar() {
		return getEstado();
	}

	public Integer default3Editar() {
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

	// endregion
}
