package domainapp.dom.empleado;

import domainapp.dom.persona.Persona;
import org.apache.isis.applib.util.ObjectContracts;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created for domainapp.dom.empleado on 17/06/2017.
 */
@PersistenceCapable(schema = "empleado", identityType = IdentityType.DATASTORE)
public class Empleado extends Persona implements Comparable<Empleado> {

    public String title() { return super.getNombre(); }

    @Override
    public int compareTo(Empleado o) {
        return ObjectContracts.compare(o, this, "nombre");
    }
}
