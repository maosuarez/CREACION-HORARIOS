package com.example.apihorarios.Clases;
import java.util.ArrayList;

//Objeto flexible util para pasar informacion junta en mas de una forma
public class ObjetoAuxiliar {

    //Atributos
    //--------------------------------------------------
    private ArrayList<ArrayList<OpcionesMateria>> listMates;

    private ArrayList<int[][]> posiblesHorarios;

    private ArrayList<String[][]> textoHorarios;

    private ArrayList<OpcionesMateria> mates;

    private int[][] posi;

    private ArrayList<Integer> huecos;

    private ArrayList<Integer> bloques;
    //---------------------------------------------------

    //Constructor
    public ObjetoAuxiliar(){}

    public ObjetoAuxiliar(ArrayList<ArrayList<OpcionesMateria>> lista1,ArrayList<int[][]> lista2){
        listMates = lista1;
        posiblesHorarios = lista2;
    }

    public ObjetoAuxiliar(ArrayList<String[][]> lista2 , ArrayList<ArrayList<OpcionesMateria>> lista1,String verdadero){
        listMates = lista1;
        textoHorarios = lista2;
    }

    public ObjetoAuxiliar(ArrayList<OpcionesMateria> lista1,int[][] lista2){
        mates = lista1;
        posi = lista2;
    }

    public ObjetoAuxiliar(ArrayList<Integer> lista1,ArrayList<Integer> lista2, boolean verdadero){
        huecos = lista1;
        bloques = lista2;
    }

    //Getters
    public ArrayList<ArrayList<OpcionesMateria>> getListMates() {
        return listMates;
    }

    public ArrayList<int[][]> getPosiblesHorarios() {
        return posiblesHorarios;
    }

    public ArrayList<String[][]> getHorariosTexto(){
        return textoHorarios;
    }

    public ArrayList<OpcionesMateria> getMates() {
        return mates;
    }

    public int[][] getPosi() {
        return posi;
    }

    public ArrayList<Integer> getHuecos() {
        return huecos;
    }

    public ArrayList<Integer> getBloques() {
        return bloques;
    }
}
