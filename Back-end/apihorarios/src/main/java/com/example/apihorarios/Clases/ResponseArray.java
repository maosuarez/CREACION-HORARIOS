package com.example.apihorarios.Clases;

import java.util.ArrayList;

public class ResponseArray {
    private ArrayList<String> ArrayDevolver;

    public ResponseArray(){}

    public ResponseArray(ArrayList<String> lista){
        ArrayDevolver = lista;
    }

    public ArrayList<String> getSugerencias(){
        return this.ArrayDevolver;
    }
}
