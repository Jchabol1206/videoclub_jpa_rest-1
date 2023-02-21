export interface Pelicula {
  idPelicula:                number;
  titulo:                    string;
  descripcion:               string;
  anyoLanzamiento:           string;
  idioma:                    Idioma;
  idiomaOriginal:            null;
  duracionAlquiler:          number;
  rentalRate:                number;
  duracion:                  number;
  replacementCost:           number;
  clasificacion:             string;
  caracteristicasEspeciales: string;
  categorias:                any[];
  ultimaActualizacion:       Date;
}

export interface Idioma {
  id:                  number;
  nombre:              string;
  ultimaActualizacion: string;
}
