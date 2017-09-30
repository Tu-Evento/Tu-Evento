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

package domainapp.dom.tiposervicios;

import java.util.Arrays;
import java.util.List;

import domainapp.dom.articulos.TipoArticulo;

public enum TipoServicios {
	
	Animación{
		@Override
		public List<TipoArticulo> subcategorias() {
            return Arrays.asList(null, TipoArticulo.Animador, TipoArticulo.Payaso, TipoArticulo.Malabarista);
        }
	},
	Sonido{
		@Override
		public List<TipoArticulo> subcategorias() {
            return Arrays.asList(null, TipoArticulo.EquipoSonido, TipoArticulo.PersonalSonido);
        }},
	Musicalización{
		@Override
		public List<TipoArticulo> subcategorias() {
			return Arrays.asList(null, TipoArticulo.Dj);
		}},
	Limpieza{
    	@Override
		public List<TipoArticulo> subcategorias() {
	        return Arrays.asList(null, TipoArticulo.PersonalLimpieza);
	    }},
	Seguridad{
    	@Override
    	public List<TipoArticulo> subcategorias() {
    		return Arrays.asList(null, TipoArticulo.PersonalSeguridad);
    	}},
	Gastronomía{
    		@Override
    		public List<TipoArticulo> subcategorias() {
    			return Arrays.asList(null, TipoArticulo.Barman, TipoArticulo.Mozo, TipoArticulo.Cubiertos, TipoArticulo.Jarra, 
    					TipoArticulo.Mesa, TipoArticulo.Silla, TipoArticulo.Vajilla, TipoArticulo.Vaso);
    		}},
	Visual{
    			@Override
    	    	public List<TipoArticulo> subcategorias() {
    	    		return Arrays.asList(null, TipoArticulo.Camarografo, TipoArticulo.Fotografo);
    	    }},
	Decoración{
    	@Override
    	public List<TipoArticulo> subcategorias() {
    		return Arrays.asList(null, TipoArticulo.PersonalDecoracion, TipoArticulo.Cotillon);
    	}};
	
	
	
	public abstract List<TipoArticulo> subcategorias();
	
	/*private final String nombre;

	public String getNombre() {return nombre;}
	private TipoServicios(String nom) {nombre = nom;}

	@Override
	public String toString() {
		return this.nombre;
	}*/

}
