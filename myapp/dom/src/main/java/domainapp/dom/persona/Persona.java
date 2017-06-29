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

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Persona {

	public static final int NAME_LENGTH = 50;
	
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "true", length = NAME_LENGTH)
	private String apellido;
	@Property(editing = Editing.DISABLED)
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull = "true", length = NAME_LENGTH)
	private String name;
	@Property(editing = Editing.DISABLED)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull = "true", length = NAME_LENGTH)
	private String documento;
	@Property(editing = Editing.DISABLED)
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull = "true", length = NAME_LENGTH)
	private String cuil;
	@Property(editing = Editing.DISABLED)
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull = "true", length = NAME_LENGTH)
	private String direccion;
	@Property(editing = Editing.DISABLED)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
