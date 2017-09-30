package domainapp.dom.tiposervicios;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.SemanticsOf;

import domainapp.dom.articulos.TipoArticulo;

@Mixin
public class ActualizarCategoria {

public final Categorizar categorizar;
	
	public ActualizarCategoria(final Categorizar categorizar)
	{
		this.categorizar=categorizar;
	}
	
    
    
    @ActionLayout(
            describedAs = "Update category and subcategory"
    )
    @Action(semantics = SemanticsOf.IDEMPOTENT)
    public Categorizar $$(
            final TipoServicios categoria,
            final @Parameter(optionality = Optionality.OPTIONAL) TipoArticulo subcategoria) {
        categorizar.setCategoria(categoria);
        categorizar.setSubcategoria(subcategoria);
        return categorizar;
    }
    public TipoServicios default0$$() {
        return categorizar != null? categorizar.getCategoria(): null;
    }
    public TipoArticulo default1$$() {
        return categorizar != null? categorizar.getSubcategorias(): null;
    }

    public List<TipoArticulo> choices1$$(final TipoServicios categoria) {
        return TipoArticulo.listFor(categoria);
    }
}
