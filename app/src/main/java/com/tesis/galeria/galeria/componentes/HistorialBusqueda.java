package com.tesis.galeria.galeria.componentes;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by SaleVenta5 on 29-04-2016.
 */
public class HistorialBusqueda extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.tesis.galeria.galeria.componentes.HistorialBusqueda";
    public final static int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;

    public HistorialBusqueda() {
        setupSuggestions(AUTHORITY, MODE);
    }
}