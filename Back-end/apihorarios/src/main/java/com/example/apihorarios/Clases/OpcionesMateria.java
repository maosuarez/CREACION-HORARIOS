package com.example.apihorarios.Clases;

public class OpcionesMateria {
    private String Codigo;

    private String Profesor;

    private HorasSemana Horas;

    private String materia;

    public OpcionesMateria(String codigo, String profesor,HorasSemana horas) {
        Codigo = codigo;
        Profesor = profesor;
        Horas = horas;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getProfesor() {
        return Profesor;
    }

    public void setProfesor(String profesor) {
        Profesor = profesor;
    }

    public HorasSemana getHoras() {
        return Horas;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
