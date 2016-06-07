package com.tesis.galeria.galeria.modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danie on 24/5/2016.
 */
public class DatosTranslado implements Serializable {
    public int id;
    public int sedeSalidaID;
    public int sedeEntradaID;
    public String fEntrada;
    public String fSalida;
    public String encargadoEntrada;
    public String encargadoSalida;
    public String comentario;
    public int tipoTransladoID;

    public TipoTranslado tipoTranslado;
    public Sede sedeEntrada;
    public Sede sedeSalida;
    public ArrayList<Obra> obras;

}
