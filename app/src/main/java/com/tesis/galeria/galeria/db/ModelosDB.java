package com.tesis.galeria.galeria.db;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.modelos.Artista;
import com.tesis.galeria.galeria.modelos.Asesoria;
import com.tesis.galeria.galeria.modelos.Avaluo;
import com.tesis.galeria.galeria.modelos.ChangePasswordBindingModel;
import com.tesis.galeria.galeria.modelos.DatosRegistroUsuario;
import com.tesis.galeria.galeria.modelos.Ingreso;
import com.tesis.galeria.galeria.modelos.Lista.Artistas;
import com.tesis.galeria.galeria.modelos.Lista.Asesorias;
import com.tesis.galeria.galeria.modelos.Lista.Avaluos;
import com.tesis.galeria.galeria.modelos.Lista.Noticias;
import com.tesis.galeria.galeria.modelos.Lista.Obras;
import com.tesis.galeria.galeria.modelos.Lista.Publicaciones;
import com.tesis.galeria.galeria.modelos.Lista.Resplados;
import com.tesis.galeria.galeria.modelos.Noticia;
import com.tesis.galeria.galeria.modelos.Obra;
import com.tesis.galeria.galeria.modelos.Publicacion;
import com.tesis.galeria.galeria.modelos.Respaldo;
import com.tesis.galeria.galeria.modelos.Usuario;

import java.io.File;
import java.io.IOException;

import okhttp3.Response;

/**
 * Created by danie on 18/5/2016.
 */
public class ModelosDB {

    private static Gson gson = new Gson();

    public static Ingreso comprobarRegitroUsuario(String email, String clave) {

        String url = Constantes.DOMINIO + "/Token";

        Log.d("LOGIN URL", url);

        String postBody = "grant_type=password"
                + "&username=" + email
                + "&Password=" + clave;

        String respuesta = null;

        try {
            respuesta = ConexionDB.post(url, postBody);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Ingreso ingreso = null;
        try {
            ingreso = gson.fromJson(respuesta, Ingreso.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return ingreso;
    }

    public static Usuario getInformacionUsuario(String token) {

        String url = Constantes.DOMINIO + "/api/Account/UserInfo";
        Log.d("LOGIN URL", url);
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url, token);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Usuario usuario = null;
        try {
            usuario = gson.fromJson(respuesta, Usuario.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return usuario;
    }

/*
    public static Usuario registarUsuario(String email, String clave, String fNacimiento) {

        String url = Constantes.DOMINIO + "/api/Account/Registrar";

        String datosRegitro = gson.toJson(new DatosRegistroUsuario(email, clave, clave, fNacimiento));

        String postBody = "usuario=" + datosRegitro;
        String respuesta = null;

        try {
            respuesta = ConexionDB.post(url, postBody);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
*/

    public static Noticias getNotcias() {

        String url = Constantes.DOMINIO + "/api/Noticia";
        Log.d("LOGIN URL", url);
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Noticias noticias = null;
        try {
            noticias = gson.fromJson(respuesta, Noticias.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return noticias;
    }

    public static Avaluos getAvaluosUsuario(String id) {
        String url = Constantes.DOMINIO + "/api/Avaluo/Usuario/" + id;
        Log.d("URL AVALUO", url);
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Avaluos avaluos;
        try {
            avaluos = gson.fromJson(respuesta, Avaluos.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return avaluos;
    }

    public static Avaluos getAvaluosUsuarios() {
        String url = Constantes.DOMINIO + "/api/Avaluo";
        Log.d("URL AVALUO", url);
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Avaluos avaluos;
        try {
            avaluos = gson.fromJson(respuesta, Avaluos.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return avaluos;
    }

    public static Publicaciones getPublicacionesUsuario(String id) {
        String url = Constantes.DOMINIO + "/api/Publicacion/Usuario/" + id;
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Publicaciones publicaciones;
        try {
            publicaciones = gson.fromJson(respuesta, Publicaciones.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return publicaciones;
    }

    public static Asesorias getAsesoriasUsuario(String id) {
        String url = Constantes.DOMINIO + "/api/Asesoria/Usuario/" + id;
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Asesorias asesorias;
        try {
            asesorias = gson.fromJson(respuesta, Asesorias.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return asesorias;
    }

    public static Artistas getArtistas() {
        String url = Constantes.DOMINIO + "/api/Artista";
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Artistas artistas;
        try {
            artistas = gson.fromJson(respuesta, Artistas.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return artistas;
    }

    public static Obras getObras() {
        String url = Constantes.DOMINIO + "/api/Obras/Todas";
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Obras obras;
        try {
            obras = gson.fromJson(respuesta, Obras.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return obras;
    }

    public static Obras getObrasArtista(int id) {
        String url = Constantes.DOMINIO + "/api/Obras/Artista/" + id;
        String respuesta = null;
        try {
            respuesta = ConexionDB.get(url);
            Log.d("RESPUESTA", respuesta);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Obras obras;
        try {
            obras = gson.fromJson(respuesta, Obras.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return obras;
    }

    public static Resplados getRespaldos() {
        String url = Constantes.DOMINIO + "/api/Respaldo";
        String respuesta = null;

        try {
            respuesta = ConexionDB.get(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Resplados resplados;

        try {
            resplados = gson.fromJson(respuesta, Resplados.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }

        return resplados;
    }

    public static boolean cambiarClave(ChangePasswordBindingModel model) {

        String url = Constantes.DOMINIO + "/api/Account/ChangePassword";
        String postBody = gson.toJson(model);
        Response respuesta = null;

        try {
            respuesta = ConexionDB.postResponse(url, postBody);
            Log.d("Respuesta cambiar clave", respuesta.body().toString());

            return respuesta.isSuccessful();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Respaldo addRepaldo() {
        String url = Constantes.DOMINIO + "/api/Respaldo";
        String respuesta = null;

        try {
            respuesta = ConexionDB.post(url, "");
            return gson.fromJson(respuesta, Respaldo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void procesarAvaluo(File file, String idAvaluo, String precio) {
        Log.d("COMENZO", "AHORA");
        String url = Constantes.DOMINIO + "/api/avaluo/ProcesarAvaluo";
        String respuesta = null;

        try {
            //respuesta = ConexionDB.post(url, "");
            ConexionDB.uploadFileProcesarAvaluo(url, file, "image/jpg", idAvaluo, precio);
            //return gson.fromJson(respuesta, Respaldo.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            //return null;
        }
    }

}
