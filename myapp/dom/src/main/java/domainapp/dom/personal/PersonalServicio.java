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

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.dom.tipodocumento.TipoDocumento;
import domainapp.dom.tiposervicios.TipoServicios;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created for domainapp.dom.empleado on 17/06/2017.
 */
@DomainService(
        repositoryFor = Personal.class,
        nature = NatureOfService.VIEW
)
@DomainServiceLayout(
        menuOrder = "20"
)
public class PersonalServicio {

    @ActionLayout(named = "Personal")
    @MemberOrder(name = "Crear", sequence = "1")
    public Personal create(
            @ParameterLayout(named="Nombre") String nombre,
            @ParameterLayout(named="Apellido") String apellido,
            @ParameterLayout(named="TipoDocumento") TipoDocumento tipoDocumento,
            @ParameterLayout(named="Documento") Integer documento,
            @ParameterLayout(named="CUIL") Integer cuil,
            @ParameterLayout(named="Direccion") String direccion,
            @ParameterLayout(named="Servicios") TipoServicios servicios
    ) {
        final Personal obj = repositoryService.instantiate(Personal.class);
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        obj.setTipoDocumento(tipoDocumento);
        obj.setDocumento(documento);
        obj.setCuil(cuil);
        obj.setDireccion(direccion);
        obj.setServicios(servicios);
        repositoryService.persist(obj);
        return obj;
    }

    @ActionLayout(named = "Personal")
    @MemberOrder(name = "Listar", sequence = "1")
    public List<Personal> listar() {
        return repositoryService.allInstances(Personal.class);
    }

    @ActionLayout(named = "Empleado de nombre Jose")
    @MemberOrder(name = "Listar", sequence = "1.1")
    public List<Personal> listarNombresJose() {
        return repositoryService.allInstances(Personal.class);
                //.stream()
                //.filter(x -> x.getNombre().compareTo("jose") == 0)
                //.collect(Collectors.toList());
    }
    
    

    @Inject
    RepositoryService repositoryService;

    @Inject
    FactoryService factoryService;
}
