package com.example.apihorarios.Clases;
import java.util.ArrayList;

//Objeto para almacenar la informacion de cada materia
public class Materias {

    //Atributos
    //-------------------------------------
    private String nombreMateria;

    private Integer NoMate;

    private ArrayList<OpcionesMateria> ListaOpciones = new ArrayList<>();
    //-------------------------------------

    //Constructor
    public Materias() {}

    public Materias(String nombre){
        nombreMateria = nombre;
    }

    public Materias(String nombre, int nomate) {
        this.nombreMateria = nombre;
        NoMate = nomate;
    }

    public Materias(String nombre, int nomate, OpcionesMateria opc){
        this.nombreMateria = nombre;
        NoMate = nomate;
        opc.setMateria(nombreMateria);
        ListaOpciones.add(opc);
    }

    //Getters
    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Integer getNoMate() {
        return NoMate;
    }

    public void setNoMate(Integer noMate) {
        NoMate = noMate;
    }

    public ArrayList<OpcionesMateria> getListaOpciones() {
        return ListaOpciones;
    }

    public void agregarOpcionesMateria(OpcionesMateria opcionesMateria) {
        ListaOpciones.add(opcionesMateria);
    }
}
