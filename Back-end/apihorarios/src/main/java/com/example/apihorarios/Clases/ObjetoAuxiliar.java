package com.example.apihorarios.Clases;
import java.util.ArrayList;

public class ObjetoAuxiliar {
    private ArrayList<ArrayList<OpcionesMateria>> listMates;

    private ArrayList<int[][]> posiblesHorarios;

    private ArrayList<OpcionesMateria> mates;

    private int[][] posi;

    private ArrayList<Integer> huecos;

    private ArrayList<Integer> bloques;

    public ObjetoAuxiliar(ArrayList<ArrayList<OpcionesMateria>> lista1,ArrayList<int[][]> lista2){
        listMates = lista1;
        posiblesHorarios = lista2;
    }

    public ObjetoAuxiliar(ArrayList<OpcionesMateria> lista1,int[][] lista2){
        mates = lista1;
        posi = lista2;
    }

    public ObjetoAuxiliar(ArrayList<Integer> lista1,ArrayList<Integer> lista2, boolean verdadero){
        huecos = lista1;
        bloques = lista2;
    }

    public ArrayList<ArrayList<OpcionesMateria>> getListMates() {
        return listMates;
    }

    public ArrayList<int[][]> getPosiblesHorarios() {
        return posiblesHorarios;
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
