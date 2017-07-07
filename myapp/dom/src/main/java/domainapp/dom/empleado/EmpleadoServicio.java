package domainapp.dom.empleado;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;

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
            @ParameterLayout(named="Documento") Integer documento,
            @ParameterLayout(named="CUIL") Integer cuil,
            @ParameterLayout(named="Direccion") String direccion
    ) {
        final Empleado obj = repositoryService.instantiate(Empleado.class);
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        obj.setDocumento(documento);
        obj.setCuil(cuil);
        obj.setDireccion(direccion);
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
