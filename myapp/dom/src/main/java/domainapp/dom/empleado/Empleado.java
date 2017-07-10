package domainapp.dom.empleado;

import domainapp.dom.persona.Persona;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
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

    public TranslatableString title() { return TranslatableString.tr("Empleado: {apellido}, {nombre}",
    		"apellido", getApellido(), "nombre", getNombre());}

 // region > delete (action)
 	public static class EliminarDomainEvent extends ActionDomainEvent<Empleado> {}

 	@Action(domainEvent = EliminarDomainEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
 	public void eliminar() {
 		final String title = titleService.titleOf(this);
 		messageService.informUser(String.format("'%s' eliminado", title));
 		repositoryService.remove(this);
 	}
    
    
    @Override
    public int compareTo(Empleado o) {
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
