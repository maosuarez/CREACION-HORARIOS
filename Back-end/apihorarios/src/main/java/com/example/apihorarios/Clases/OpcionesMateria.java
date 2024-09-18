package com.example.apihorarios.Clases;

//Es un Objeto auxiliar dentro de los objetos materias, que almacenan informacion adicional
public class OpcionesMateria {

    //Atributos
    //------------------------------
    private String Codigo;

    private String Profesor;

    private HorasSemana Horas;

    private String materia;
    //------------------------------

    //Constructor
    public OpcionesMateria(String codigo, String profesor,HorasSemana horas) {
        Codigo = codigo;
        Profesor = profesor;
        Horas = horas;
    }

    //Setter y Getter
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
