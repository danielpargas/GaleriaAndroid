package com.tesis.galeria.galeria;

/**
 * Created by danie on 18/5/2016.
 */
public class Constantes {

    public static final String DOMINIO = "https://192.168.0.102";
    //public static String DOMINIO = "https://192.168.13.2";
    //public static String DOMINIO = "http://galeriaascaso.azurewebsites.net";

    public static final String URL_IMAGEN_USUARIO = "/Imagenes/Usuario/";
    public static final String URL_IMAGEN_NOTCIA = "/Imagenes/Noticia/";
    public static final String URL_IMAGEN_OBRA = "/Imagenes/Obra/";
    public static final String URL_IMAGEN_ASESORIA = "/Imagenes/Asesoria/";
    public static final String URL_IMAGEN_ARTISTA = "/Imagenes/Artista/";
    public static final String URL_IMAGEN_AVALUO = "/Imagenes/Avaluo/";

    public static final String PREFERENCIA = "preferencia";

    public static final String PARAM_REGISTRO = "registro";
    public static final String PARAM_ID_USUARIO = "id_usuario";
    public static final String PARAM_CORREO = "correo";
    public static final String PARAM_IMAGEN_USUARIO = "imagen_usuario";
    public static final String PARAM_DATOS_AVALUO = "datos_avaluo";
    public static final String PARAM_DATOS_ASESORIA = "datos_asesoria";
    public static final String PARAM_DATOS_PUBLICACION = "datos_publicacion";
    public static final String PARAM_DATOS_ARTISTA = "datos_artista";
    public static final String PARAM_DATOS_OBRA = "datos_obra";
    public static final String PARAM_DATOS_RESPALDO = "datos_respaldo";
    public static final String PARAM_EXTRA = "com.tesis.galeria.galeria";
    public static final String PARAM_ROLES = "roles";

    public static final String ROL_ADMIN = "Admin";
    public static final String ROL_DIRECTOR = "Director";
    public static final String ROL_EMPLEADO = "Empleado";

    public static final String ACTION_INGRESO = "com.tesis.galeria.galeria.action.INGRESO";

    public static final String ACTION_RESPALDO_READY = "com.tesis.galeria.galeria.action.RESPALDO_READY";
    public static final String ACTION_RESPALDO_FAIL = "com.tesis.galeria.galeria.action.RESPALDO_FAIL";


    public static final String FRAGMENT_AVALUOS = "fragment_avaluos";
    public static final String FRAGMENT_NOTCIAS = "fragment_noticias";
    public static final String FRAGMENT_ARTISTAS = "fragment_artistas";
    public static final String FRAGMENT_OBRAS = "fragment_obras";
    public static final String FRAGMENT_CUENTA = "fragment_cuenta";
    public static final String FRAGMENT_PERFIL = "fragment_perfil";
    public static final String FRAGMENT_RESPALDOS = "fragment_respaldos";
    public static final String FRAGMENT_IMAGENES = "fragment_imagenes";

    public enum ESTATUS_AVALUO {
        DEFECTO, AVALUADO, ESPERA, PROCESO
    }

}
