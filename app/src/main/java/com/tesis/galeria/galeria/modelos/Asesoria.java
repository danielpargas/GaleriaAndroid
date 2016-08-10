package com.tesis.galeria.galeria.modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danie on 24/5/2016.
 */
public class Asesoria implements Serializable {
    public int id;
    public String telefono;
    public boolean presupuesto;
    public String direccion;
    public String fsolicitud;
    public String fAsesoria;
    public int tipoInmuebleID;
    public String applicationUserID;
    public int estatusAsesoriaID;

    public ApplicacionUser applicacionUser;
    public TipoInmueble tipoInmueble;
    public EstatusAsesoria estatusAsesoria;
    public ArrayList<ImagenAsesoria> imagenesAsesorias;
    public ArrayList<Obra> obras;

}
