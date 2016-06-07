package com.tesis.galeria.galeria.modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danie on 24/5/2016.
 */
public class Obra implements Serializable {
    public int id;
    public String titulo;
    public String felaboracion;
    public String fecha;
    public int tecnicaID;
    public int tipoID;
    public int artistaID;
    public boolean publico;
    public int generoID;
    public String medida;
    public String imagen;

    public Tecnica tecnica;
    public Tipo tipo;
    public Artista artista;
    public Genero genero;

    public ArrayList<Asesoria> asesorias;
    public ArrayList<DatosTranslado> datosTranslados;
    public ArrayList<Avaluo> avaluos;
    public ArrayList<Inventario> inventarios;

}
