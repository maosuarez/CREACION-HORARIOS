package com.example.apihorarios.Clases;

import java.util.ArrayList;

//Este Objeto se creo para poder recopilar la informacion del Json que se envia.
public class BodyObject {

    //Atributos
    //-----------------------------------------------
    private ArrayList<Preferencias> listaPreferencias;

    private HorasSemana horasLibres;
    //-----------------------------------------------

    //Constructores
    public BodyObject(ArrayList<Preferencias> lista, HorasSemana horas){
        listaPreferencias = lista;
        horasLibres = horas; 
    }

    //Getters
    public ArrayList<Preferencias> getLista(){
        return listaPreferencias;
    }

    public HorasSemana getLibres(){
        return horasLibres;
    }
}
