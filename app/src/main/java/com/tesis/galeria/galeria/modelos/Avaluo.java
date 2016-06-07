package com.tesis.galeria.galeria.modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danie on 24/5/2016.
 */
public class Avaluo implements Serializable{

    public int id;
    public String comentario;
    public int estatusAvaluoID;
    public String applicacionUserID;
    public int fichaAvaluoID;
    public int obraID;

    public Obra obra;
    public EstatusAvaluo estatusAvaluo;
    public FichaAvaluo fichaAvaluo;
    public ApplicacionUser applicacionUser;
    public ArrayList<Publicacion> publicaciones;
    public ArrayList<ImagenAvaluo> imagenesAvaluos;
}
