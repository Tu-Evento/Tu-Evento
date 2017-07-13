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
package domainapp.dom.empleado;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.dom.tipodocumento.TipoDocumento;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created for domainapp.dom.empleado on 17/06/2017.
 */
@DomainService(
        repositoryFor = Empleado.class,
        nature = NatureOfService.VIEW
)
@DomainServiceLayout(
        menuOrder = "20"
)
public class EmpleadoServicio {

    @ActionLayout(named = "Empleado")
    @MemberOrder(name = "Crear", sequence = "1")
    public Empleado create(
            @ParameterLayout(named="Nombre") String nombre,
            @ParameterLayout(named="Apellido") String apellido,
            @ParameterLayout(named="TipoDocumento") TipoDocumento tipoDocumento,
            @ParameterLayout(named="Documento") Integer documento,
            @ParameterLayout(named="CUIL") Integer cuil,
            @ParameterLayout(named="Direccion") String direccion,
            @ParameterLayout(named="RolTipo") RolTipoEnum rolTipo
    ) {
        final Empleado obj = repositoryService.instantiate(Empleado.class);
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        obj.setTipoDocumento(tipoDocumento);
        obj.setDocumento(documento);
        obj.setCuil(cuil);
        obj.setDireccion(direccion);
        obj.setRolTipo(rolTipo);
        repositoryService.persist(obj);
        return obj;
    }

    @ActionLayout(named = "Empleado")
    @MemberOrder(name = "Listar", sequence = "1")
    public List<Empleado> listar() {
        return repositoryService.allInstances(Empleado.class);
    }

    @ActionLayout(named = "Empleado de nombre Jose")
    @MemberOrder(name = "Listar", sequence = "1")
    public List<Empleado> listarNombresJose() {
        return repositoryService.allInstances(Empleado.class)
                .stream()
                .filter(x -> x.getNombre().compareTo("jose") == 0)
                .collect(Collectors.toList());
    }
    
    

    @Inject
    RepositoryService repositoryService;

    @Inject
    FactoryService factoryService;
}
