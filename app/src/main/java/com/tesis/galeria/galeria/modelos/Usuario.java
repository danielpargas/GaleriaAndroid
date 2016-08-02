package com.tesis.galeria.galeria.modelos;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by danie on 17/5/2016.
 */
public class Usuario {

    public String id;
    public String imagen;
    public String email;
    public boolean hasRegistered;
    public String loginProvider;
    public Set<String> roles;
}
