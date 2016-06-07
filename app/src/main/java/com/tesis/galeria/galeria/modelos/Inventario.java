package com.tesis.galeria.galeria.modelos;

import java.io.Serializable;

/**
 * Created by danie on 24/5/2016.
 */
public class Inventario implements Serializable {
    public int id;
    public int disponibilidad;
    public int cantidad;
    public String fecha;
    public double precio;
    public int sedeID;
    public int obraID;
    public String applicationUserID;

    public Sede sede;
    public Obra obra;
    public ApplicacionUser applicacionUser;
}
