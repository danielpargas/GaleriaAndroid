package com.tesis.galeria.galeria.modelos;

/**
 * Created by danie on 18/5/2016.
 */
public class DatosRegistroUsuario {

    public DatosRegistroUsuario() {

    }

    public DatosRegistroUsuario(String email, String clave, String confirmClave, String fNacimiento) {
        this.email = email;
        this.clave = clave;
        this.confirmClave = confirmClave;
        this.fNacimiento = fNacimiento;
    }

    public String email;
    public String clave;
    public String confirmClave;
    public String fNacimiento;
}
