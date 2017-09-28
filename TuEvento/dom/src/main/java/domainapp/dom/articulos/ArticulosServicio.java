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

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.dom.estado.Estado;
import domainapp.dom.proveedores.Proveedores;
import domainapp.dom.tiposervicios.TipoServicios;

@DomainService(
        repositoryFor = Articulos.class,
        nature = NatureOfService.VIEW
)
@DomainServiceLayout(
        menuOrder = "20"
)
public class ArticulosServicio {
	
	@ActionLayout(named = "Articulos")
    @MemberOrder(name = "Crear", sequence = "5")
	public Articulos create(
			@ParameterLayout(named="Descripcion") String descripcion,
			@ParameterLayout(named="Servicios") TipoServicios servicios,
			@ParameterLayout(named="TipoArticulo") TipoArticulo tipoArticulo,
			@ParameterLayout(named="Estado") Estado estado,
			@ParameterLayout(named="Cantidad") int cantidad,
			@ParameterLayout(named="Organización") Proveedores organizacion
			){
		final Articulos obj = repositoryService.instantiate(Articulos.class);
		obj.setDescripcion(descripcion);
		obj.setServicios(servicios);
		obj.setTipoArticulo(tipoArticulo);
		obj.setEstado(estado);
		obj.setCantidad(cantidad);
		//obj.setOrganizacion(organizacion);
		repositoryService.persist(obj);
		return obj;
	}
	
	@ActionLayout(named = "Articulos")
    @MemberOrder(name = "Listar", sequence = "5")
    public List<Articulos> listar() {
        return repositoryService.allInstances(Articulos.class);
    }
	
	//@ActionLayout(hidden=Where.EVERYWHERE)
	//public String buscarProveedor(final Proveedores organizacion){
	//	return "";
	//}
	/*@ActionLayout(hidden=Where.EVERYWHERE)
	public List<Proveedores> buscarProveedores(final Proveedores organizacion){
		return repositoryService.allMatches(QueryDefault.create(Proveedores.class, "listarProveedor", "organizacion", organizacion));
	}*/

	@Inject
    RepositoryService repositoryService;

    @Inject
    FactoryService factoryService;
    
    
}
