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
	
	@Column(allowsNull = "false")
	private String nombre;
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
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
