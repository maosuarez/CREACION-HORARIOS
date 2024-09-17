package com.example.apihorarios.Clases;
import java.util.ArrayList;

public class Traductor {
    private ArrayList<Materias> traduc;

    public Traductor(ArrayList<Materias> materias){
        traduc = materias;
    }

    public String trans(int nomate){
        for(Materias materia : traduc){
            if(nomate == 0){
                return "Libre";
            }
            if(materia.getNoMate() == nomate){
                return materia.getNombreMateria();
            }
        }
        return "No se encontro";
    }

    static void mostrar(int[][] arreglo){
        System.out.println("Matriz que se quiere Mostrar");
        for (int o = 0; o < 12; o++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(arreglo[o][j]);
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }

}
