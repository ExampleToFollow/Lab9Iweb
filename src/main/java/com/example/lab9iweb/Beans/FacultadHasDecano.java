package com.example.lab9iweb.Beans;

public class FacultadHasDecano {
    private int idFacultad;
    private int idDecano;
    //usamos superbeans;

    private Facultad facultad;
    private Usuario decano;
    //Decano


    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public int getIdDecano() {
        return idDecano;
    }

    public void setIdDecano(int idDecano) {
        this.idDecano = idDecano;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Usuario getDecano() {
        return decano;
    }

    public void setDecano(Usuario decano) {
        this.decano = decano;
    }
}
