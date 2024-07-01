package com.dicelink.dicelinkapp.ui.view;

public class SolicitudOBJ {
    private String username;
    private String texto;

    public SolicitudOBJ(String username, String texto) {
        this.username = username;
        this.texto = texto;
    }

    public String getUsername() {
        return username;
    }

    public String getTexto() {
        return texto;
    }
}