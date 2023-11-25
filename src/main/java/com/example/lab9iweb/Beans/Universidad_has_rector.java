package com.example.lab9iweb.Beans;

public class Universidad_has_rector {
    //atributos
    private int idUniversidad;
    private int idRector;
    private Universidad universidad;
    private Usuario rector;
    //GETTERS AND SETTERS;


    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public int getIdRector() {
        return idRector;
    }

    public void setIdRector(int idRector) {
        this.idRector = idRector;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public Usuario getRector() {
        return rector;
    }

    public void setRector(Usuario rector) {
        this.rector = rector;
    }
}
