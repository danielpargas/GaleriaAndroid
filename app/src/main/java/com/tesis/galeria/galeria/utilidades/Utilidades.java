package com.tesis.galeria.galeria.utilidades;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.ArraySet;

import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.modelos.Usuario;

import java.util.Set;

/**
 * Created by danie on 20/5/2016.
 */
public class Utilidades {

    public static SharedPreferences getSharedPreferences(AppCompatActivity context) {
        return context.getSharedPreferences(Constantes.PREFERENCIA, Context.MODE_PRIVATE);
    }

    public static boolean estaRegistrado(AppCompatActivity context) {
        SharedPreferences preferences = getSharedPreferences(context);
        boolean registrado = preferences.getBoolean(Constantes.PARAM_REGISTRO, false);
        return registrado;
    }

    public static boolean setIngreso(Usuario u, AppCompatActivity context) {
        SharedPreferences preferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(Constantes.PARAM_ID_USUARIO, u.id);
        editor.putString(Constantes.PARAM_IMAGEN_USUARIO, u.imagen);
        editor.putString(Constantes.PARAM_CORREO, u.email);
        editor.putBoolean(Constantes.PARAM_REGISTRO, true);
        editor.putStringSet(Constantes.PARAM_ROLES, u.roles);

        return editor.commit();
    }

    public static boolean finalizarSesion(AppCompatActivity context) {
        SharedPreferences preferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove(Constantes.PARAM_ID_USUARIO);
        editor.remove(Constantes.PARAM_IMAGEN_USUARIO);
        editor.remove(Constantes.PARAM_CORREO);
        editor.remove(Constantes.PARAM_REGISTRO);
        editor.remove(Constantes.PARAM_ROLES);

        return editor.commit();
    }

    public static String getIdUsuario(AppCompatActivity context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(Constantes.PARAM_ID_USUARIO, null);
    }

    public static boolean poseeRol(AppCompatActivity context, String rol) {
        SharedPreferences preferences = getSharedPreferences(context);

        Set<String> roles = preferences.getStringSet(Constantes.PARAM_ROLES, null);

        if (roles == null) {
            return false;
        } else {
            return roles.contains(rol);
        }
    }

    public static String getImagenUsuario(AppCompatActivity context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(Constantes.PARAM_IMAGEN_USUARIO, null);
    }

    public static String getNombreUsuario(AppCompatActivity context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(Constantes.PARAM_CORREO, null);
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

}
