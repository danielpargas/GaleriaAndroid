package com.tesis.galeria.galeria.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.modelos.Respaldo;
import com.tesis.galeria.galeria.utilidades.NotificationUtils;

/**
 * Created by danie on 25/7/2016.
 */
public class ResponseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constantes.ACTION_RESPALDO_READY)) {
            Respaldo respaldo = (Respaldo) intent.getSerializableExtra(Constantes.PARAM_DATOS_RESPALDO);
            NotificationUtils.updateNotification(context, "Respaldo añadido", "Se ha completado el respaldo");
        } else if (intent.getAction().equals(Constantes.ACTION_RESPALDO_FAIL)) {
            NotificationUtils.updateErrorNotification(context, "Ha ocurrido un error", "Ocurrio un error al hacer el respaldo");
        }
    }
}
