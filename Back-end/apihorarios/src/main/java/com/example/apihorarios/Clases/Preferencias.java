package com.example.apihorarios.Clases;
import java.util.ArrayList;

public class Preferencias {

    private String materia;

    private ArrayList<String> codigos;

    private ArrayList<String> profesores;

    public Preferencias(String materia, ArrayList<String> codigos, ArrayList<String> profesores) {
        this.materia = materia;
        this.codigos = codigos;
        this.profesores = profesores;
    }
    public Preferencias(String materia){
        this.materia = materia;
        this.codigos = new ArrayList<>();
        this.profesores = new ArrayList<>();
    }

    public void agregarCodigo(String codigo){
        this.codigos.add(codigo);
    }
    public void agregarProfe(String profe){
        this.profesores.add(profe);
    }

    public String getMateria() {
        return materia;
    }
    public void setMateria(String materia) {
        this.materia = materia;
    }

    public ArrayList<String> getCodigos() {
        return codigos;
    }
    public void setCodigos(ArrayList<String> codigos) {
        this.codigos = codigos;
    }

    public ArrayList<String> getProfesores() {
        return profesores;
    }
    public void setProfesores(ArrayList<String> profesores) {
        this.profesores = profesores;
    }

}
