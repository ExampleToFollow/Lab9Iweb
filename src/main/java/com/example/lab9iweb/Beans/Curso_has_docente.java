package com.example.lab9iweb.Beans;

public class Curso_has_docente {
    //Atributos
    private int idCurso;
    private int idDocente;
    //CON SUPERBEANS;
    private Curso curso;
    private Usuario docente;


    //GETTERS AND SETTERS;


    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Usuario getDocente() {
        return docente;
    }

    public void setDocente(Usuario docente) {
        this.docente = docente;
    }
}
