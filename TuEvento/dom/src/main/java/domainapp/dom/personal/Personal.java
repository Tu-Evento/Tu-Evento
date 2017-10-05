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
package domainapp.dom.personal;

import domainapp.dom.persona.Persona;
import domainapp.dom.tipodocumento.TipoDocumento;
import domainapp.dom.tiposervicios.TipoServicios;

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

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created for domainapp.dom.empleado on 17/06/2017.
 */
@PersistenceCapable(
		identityType = IdentityType.DATASTORE,
		schema = "TuEvento",
		table="Personal"
		)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="personal_id")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(
			name="listarNombresJose", language="JDOQL",
			value="SELECT "
				+"FROM domainapp.dom.TuEvento.Personal "
				+"WHERE nombre.indexOf(:nombre)>=0 "
	)
})
public class Personal extends Persona implements Comparable<Personal> {

    public TranslatableString title() { return TranslatableString.tr("Personal: {apellido}, {nombre} - {servicios}",
    		"apellido", getApellido(), "nombre", getNombre(), "servicios", getServicios());}

    @MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	private String cargo;
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@MemberOrder(sequence = "9")
	@Column(allowsNull = "false")
	private String sexo;
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	private Date fechaNacimiento;
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@MemberOrder(sequence = "11")
	@Column(allowsNull = "false")
	private EstadoCivil estadoCivil;
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	@MemberOrder(sequence = "12")
	@Column(allowsNull = "false")
	private Integer cuilCuit;
	public Integer getCuilCuit() {
		return cuilCuit;
	}
	public void setCuilCuit(Integer cuilCuit) {
		this.cuilCuit = cuilCuit;
	}
    
    @MemberOrder(sequence = "13")
    @Column(allowsNull = "false")
	private TipoServicios servicios;
	public TipoServicios getServicios(){
		return servicios;
	}
	public void setServicios(TipoServicios servicios){
		this.servicios = servicios;
	}
	
	
	public static class EditarDomainEvent extends ActionDomainEvent<Personal> {	}

	@Action(command = CommandReification.ENABLED, publishing = Publishing.ENABLED, 
			semantics = SemanticsOf.IDEMPOTENT, domainEvent = EditarDomainEvent.class)
	public Personal editar(
			@ParameterLayout(named = "Nombre") final String nombre,
			@ParameterLayout(named = "Apellido") final String apellido,
			@ParameterLayout(named = "Tipo Documento") final TipoDocumento tipo,
			@ParameterLayout(named = "Nº Documento") final Integer nroDocumento,
			@ParameterLayout(named = "Cuil/Cuit") final Integer cuilCuit,
			@ParameterLayout(named = "Direccion") final String direccion,
			@ParameterLayout(named = "Telefono") final Integer telefono,
			@ParameterLayout(named = "Email") final String email,
			@ParameterLayout(named = "Cargo") final String cargo,
			@ParameterLayout(named = "Sexo") final String sexo,
			@ParameterLayout(named = "Fecha Nacimiento") final Date fechaNacimiento,
			@ParameterLayout(named = "Estado Civil") final EstadoCivil estadoCivil,
			@ParameterLayout(named = "Area de Trabajo") final TipoServicios servicios) {
		setNombre(nombre);
		setApellido(apellido);
		setTipoDocumento(tipo);
		setNroDocumento(nroDocumento);
		setCuilCuit(cuilCuit);
		setDireccion(direccion);
		setTelefono(telefono);
		setEmail(email);
		setCargo(cargo);
		setSexo(sexo);
		setFechaNacimiento(fechaNacimiento);
		setEstadoCivil(estadoCivil);
		setServicios(servicios);
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
	
	public String default8Editar() {
		return getCargo();
	}
	
	public String default9Editar() {
		return getSexo();
	}
	
	public Date default10Editar() {
		return getFechaNacimiento();
	}
	
	public EstadoCivil default11Editar() {
		return getEstadoCivil();
	}

	public TipoServicios default12Editar() {
		return getServicios();
	}
    
 // region > delete (action)
 	public static class EliminarDomainEvent extends ActionDomainEvent<Personal> { }

 	@Action(domainEvent = EliminarDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
 	public void eliminar() {
 		final String title = titleService.titleOf(this);
 		messageService.informUser(String.format("'%s' eliminado", title));
 		repositoryService.remove(this);
 	}
    
    
    @Override
    public int compareTo(Personal o) {
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
