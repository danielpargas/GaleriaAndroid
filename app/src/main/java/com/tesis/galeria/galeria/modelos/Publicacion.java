package com.tesis.galeria.galeria.modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danie on 24/5/2016.
 */
public class Publicacion implements Serializable{

    public int id;
    public String correo;
    public String telefono;
    public String descripcion;
    public String finicio;
    public String ffinal;
    public int avaluoID;
    public int estatusPublicacionID;
    public String applicacionUserID;

    public Avaluo avaluo;
    public ApplicacionUser applicacionUser;
    public EstatusPublicacion estatusPublicacion;

}
