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

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.util.ObjectContracts;



@PersistenceCapable(
		schema = "proveedores", 
		identityType = IdentityType.DATASTORE)
public class Proveedores implements Comparable<Proveedores>{
	
	public TranslatableString title() { return TranslatableString.tr("Proveedores: ");}
	
	//Nombre de Organización
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	private String nombreOrganizacion;
	public String getNombreOrganizacion(){
		return nombreOrganizacion;
	}
	public void setNombreOrganizacion(String nombreOrganizacion){
		this.nombreOrganizacion = nombreOrganizacion;
	}
	
	//Dirección de Organización
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	private String direccion;
	public String getDireccion(){
		return direccion;
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	
	//CUIT de Organizacion
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	private Integer cuit;
	public Integer getCuit(){
		return cuit;
	}
	public void setCuit(Integer cuit){
		this.cuit = cuit;
	}
	
	//Email de Organización
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	private String email;
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	//Número de Teléfono
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "false")
	private Integer telefono;
	public Integer getTelefono(){
		return telefono;
	}
	public void setTelefono(Integer telefono){
		this.telefono = telefono;
	}
	
	//Contacto de Referencia de la Organización
	@MemberOrder(sequence = "6")
	@Column(allowsNull = "false")
	private String nombreContacto;
	public String getNombreContacto(){
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto){
		this.nombreContacto = nombreContacto;
	}

	@Override
	public int compareTo(Proveedores o) {
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
