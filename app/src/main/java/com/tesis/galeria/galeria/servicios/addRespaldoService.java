package com.tesis.galeria.galeria.servicios;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Respaldo;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class AddRespaldoService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_RESPALDO = "com.tesis.galeria.galeria.servicios.action.RESPALDO";
    private static final String ACTION_BAZ = "com.tesis.galeria.galeria.servicios.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.tesis.galeria.galeria.servicios.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.tesis.galeria.galeria.servicios.extra.PARAM2";

    public AddRespaldoService() {
        super("AddRespaldoService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionRespaldo(Context context) {
        String msg = "Creando respaldo...";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.upload)
                .setContentText(msg)
                .setProgress(0, 0, true)
                .setAutoCancel(false);

        NotificationManager notifMngr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifMngr.notify(1, builder.build());

        Intent intent = new Intent(context, AddRespaldoService.class);
        intent.setAction(ACTION_RESPALDO);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, AddRespaldoService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_RESPALDO.equals(action)) {
                handleActionRespaldo();
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionRespaldo() {

        Respaldo respaldo = ModelosDB.addRepaldo();

        Intent localIntent = new Intent();

        if (respaldo != null) {
            Log.d("RESPALDO", "Respaldo Agregado");

            localIntent.setAction(Constantes.ACTION_RESPALDO_READY);
            localIntent.putExtra(Constantes.PARAM_DATOS_RESPALDO, respaldo);
        } else {
            Log.d("RESPALDO", "Respaldo null");
            localIntent.setAction(Constantes.ACTION_RESPALDO_FAIL);
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
