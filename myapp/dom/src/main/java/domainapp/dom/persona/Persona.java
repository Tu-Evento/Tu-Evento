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
package domainapp.dom.persona;

import javax.jdo.annotations.*;

import org.apache.isis.applib.annotation.MemberOrder;

import domainapp.dom.tipodocumento.TipoDocumento;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Persona {

	public Persona() {}

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	private String apellido;
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	private TipoDocumento tipoDocumento;
	public TipoDocumento getTipoDocumento(){
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento){
		this.tipoDocumento = tipoDocumento;
	}

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	private Integer documento;
	public Integer getDocumento() {
		return documento;
	}
	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	private Integer cuil;
	public Integer getCuil() {
		return cuil;
	}
	public void setCuil(Integer cuil) {
		this.cuil = cuil;
	}

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	private String direccion;
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
