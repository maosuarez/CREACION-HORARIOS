package com.example.apihorarios.Clases;
import java.util.ArrayList;

//Objeto para pasar de numeros a texto
public class Traductor {

    //Atributo
    private ArrayList<Materias> traduc;

    //Constructor
    public Traductor(ArrayList<Materias> materias){
        traduc = materias;
    }

    //funcion que traduce de numero a texto
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

    //Pasa por todos los arrays
    public ArrayList<String[][]> transformarTexto(ArrayList<int[][]> posiblesHorarios){
        ArrayList<String[][]> textoHorarios =new ArrayList<>();
        for(int i = 0; i < posiblesHorarios.size(); i++){
            String[][] semanario = new String[12][6];
            for(int j = 0; j < posiblesHorarios.get(i).length; j++){
                for(int k = 0; k < posiblesHorarios.get(i)[j].length; k++){
                    semanario[j][k] = trans(posiblesHorarios.get(i)[j][k]);
                }
            }
            textoHorarios.add(semanario);
        }
        return textoHorarios;
    }

    //Muestra la traduccion
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
