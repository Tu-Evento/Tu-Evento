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

package domainapp.dom.contacto;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import domainapp.dom.persona.Persona;

@PersistenceCapable(
		identityType = IdentityType.DATASTORE,
		schema = "TuEvento",
		table="Contactos"
		)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="contacto_id")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(
            name = "listarTodos", language = "JDOQL",
            value = "SELECT "
                    + "FROM domainapp.dom.TuEvento.Contactos"),
	@javax.jdo.annotations.Query(
			name="buscarPorTipoDeContacto", language="JDOQL",
			value="SELECT "
				+"FROM domainapp.dom.TuEvento.Contactos "
				+"WHERE tipoContacto == :tipoContacto")
})
public class Contacto extends Persona implements Comparable<Contacto>{

	@MemberOrder(sequence = "8")
	@Column(allowsNull = "false")
	private TipoContacto tipoContacto;
	public TipoContacto getTipoContacto() {
		return tipoContacto;
	}
	public void setTipoContacto(TipoContacto tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	
	
	@Override
	public int compareTo(Contacto o) {
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
