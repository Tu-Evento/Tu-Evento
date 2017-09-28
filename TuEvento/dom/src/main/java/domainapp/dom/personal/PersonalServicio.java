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

import java.util.Date;
import java.util.List;

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
            @ParameterLayout(named="Nombre") final String nombre,
            @ParameterLayout(named="Apellido") final String apellido,
            @ParameterLayout(named="TipoDocumento") final TipoDocumento tipoDocumento,
            @ParameterLayout(named="NºDocumento") final Integer nroDocumento,
            @ParameterLayout(named="Cuil/Cuit") final Integer cuilCuit,
            @ParameterLayout(named="Direccion") final String direccion,
            @ParameterLayout(named = "Telefono") final Integer telefono,
			@ParameterLayout(named = "Email") final String email,
			@ParameterLayout(named = "Cargo") final String cargo,
			@ParameterLayout(named = "Sexo") final String sexo,
			@ParameterLayout(named = "Fecha Nacimiento") final Date fechaNacimiento,
			@ParameterLayout(named = "Estado Civil") final EstadoCivil estadoCivil,
            @ParameterLayout(named="Servicios") final TipoServicios servicios
    ) {
        final Personal obj = repositoryService.instantiate(Personal.class);
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        obj.setTipoDocumento(tipoDocumento);
        obj.setNroDocumento(nroDocumento);
        obj.setCuilCuit(cuilCuit);
        obj.setDireccion(direccion);
        obj.setTelefono(telefono);
        obj.setEmail(email);
        obj.setCargo(cargo);
        obj.setSexo(sexo);
        obj.setFechaNacimiento(fechaNacimiento);
        obj.setEstadoCivil(estadoCivil);
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
