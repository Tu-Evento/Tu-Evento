package domainapp.dom.tiposervicios;

import domainapp.dom.articulos.TipoArticulo;

public interface Categorizar {

	TipoServicios getCategoria();
    void setCategoria(TipoServicios categoria);

    TipoArticulo getSubcategorias();
    void setSubcategoria(TipoArticulo subcategoria);
}
