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

import java.util.Collections;
import java.util.List;

import domainapp.dom.tiposervicios.TipoServicios;

public enum TipoArticulo {

		//Animación
		Animador, Payaso, Malabarista,
		//Limpieza
		PersonalLimpieza,
		//Seguridad
		PersonalSeguridad,
		//Decoración
		PersonalDecoracion, Cotillon,
		//Sonido
		PersonalSonido, EquipoSonido,
		//Musicalización
		Dj,
		//Visual
		Fotografo, Camarografo,
		//Gastronomía
		Barman, Mozo, Vajilla, Cubiertos, Vaso, Jarra, Mesa, Silla, Toldos;
		
		public static List<TipoArticulo> listFor(final TipoServicios categoria) {
	        return categoria != null? categoria.subcategorias(): Collections.<TipoArticulo>emptyList();
	    }
}
