package com.tesis.galeria.galeria.modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danie on 24/5/2016.
 */
public class Artista implements Serializable {
    public int id;
    public String nombre;
    public String apellido;
    public String fecha;
    public String biografia;
    public boolean publico;
    public String imagen;

    public ArrayList<Obra> obras;

}
