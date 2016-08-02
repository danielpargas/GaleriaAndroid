package com.tesis.galeria.galeria.utilidades;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.tesis.galeria.R;

/**
 * Created by Saleventa 6 on 29/3/2016.
 */
public class NotificationUtils {

    public static final int NOTIF_ID_PUBLICAR = 1;
    public static final int NOTIF_ID_COMENTAR = 2;

    public static void updateNotification(Context context, String title, String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.check)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManager notifMngr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifMngr.notify(NOTIF_ID_PUBLICAR, builder.build());
    }

    public static void updateNotification(Context context, String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.check)
                .setContentTitle(title)
                .setAutoCancel(true);

        NotificationManager notifMngr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifMngr.notify(NOTIF_ID_PUBLICAR, builder.build());
    }

    public static void updateNotification(Context context, int id, int tipo) {
        String titulo = null;
        String msg = null;

        switch (tipo) {
            case 1:
                titulo = "¡Servicio publicado!";
                msg = "Haz click aqui para ver tu servicio";
                break;
            case 3:
                titulo = "¡Producto publicado!";
                msg = "Haz click aqui para ver tu producto";
                break;
            case 4:
                titulo = "¡Inmueble publicado!";
                msg = "Haz click aqui para ver tu inmueble";
                break;
            case 5:
                titulo = "¡Vehículo publicado!";
                msg = "Haz click aquí para ver tu vehículo";
                break;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.check)
                .setContentTitle(titulo)
                .setContentText(msg)
                .setAutoCancel(true);
/*
        Intent resultIntent = new Intent(context, DetalleProductoActivity.class);
        Bundle b = new Bundle();
        b.putInt(Constants.PARAM_TIPO, tipo);
        b.putInt(Constants.PARAM_ID, id);
        resultIntent.putExtras(b);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        builder.setContentIntent(resultPendingIntent);
        NotificationManager notifMngr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifMngr.notify(NOTIF_ID_PUBLICAR, builder.build());
        */
    }

    public static void updateCommentNotification(Context context) {
        String titulo = "Comentario publicado";
        String msg = "Tu comentario fue publicado con éxito";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.check)
                .setContentTitle(titulo)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManager notifMngr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifMngr.notify(NOTIF_ID_COMENTAR, builder.build());
    }

    public static void updateErrorNotification(Context context, int tipo) {
        String titulo = null;
        String msg = null;

        switch (tipo) {
            case 1:
                titulo = "Error al publicar servicio";
                msg = "El servicio no pudo ser publicado";
                break;
            case 3:
                titulo = "Error al publicar producto";
                msg = "El producto no pudo ser publicado";
                break;
            case 4:
                titulo = "Error al publicar inmueble";
                msg = "El inmueble no pudo ser publicado";
                break;
            case 5:
                titulo = "Error al publicar vehículo";
                msg = "El vehículo no pudo ser publicado";
                break;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.error)
                .setContentTitle(titulo)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManager notifMngr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifMngr.notify(NOTIF_ID_PUBLICAR, builder.build());
    }

    public static void updateErrorNotification(Context context, String title, String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.error)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManager notifMngr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifMngr.notify(NOTIF_ID_PUBLICAR, builder.build());
    }

    public static void updateCommentErrorNotification(Context context) {
        String titulo = "Error al publicar el comentario";
        String msg = "El comentario no pudo ser publicado";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.error)
                .setContentTitle(titulo)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManager notifMngr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifMngr.notify(NOTIF_ID_COMENTAR, builder.build());
    }
}
