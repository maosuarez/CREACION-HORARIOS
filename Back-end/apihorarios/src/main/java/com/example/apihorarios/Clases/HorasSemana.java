package com.example.apihorarios.Clases;

import java.util.ArrayList;

//Objeto que se cero para ordenar las horas de la semana
public class HorasSemana {

    //Atributos
    //----------------------------------
    private ArrayList<String> Lunes;
    private ArrayList<String> Martes;
    private ArrayList<String> Miercoles;
    private ArrayList<String> Jueves;
    private ArrayList<String> Viernes;
    private ArrayList<String> Sabados;
    //----------------------------------

    //Constructor
    public HorasSemana() {
        Lunes = new ArrayList<>();
        Martes = new ArrayList<>();
        Miercoles = new ArrayList<>();
        Jueves = new ArrayList<>();
        Viernes = new ArrayList<>();
        Sabados = new ArrayList<>();
    }

    //metodo ajustar el valor de cada lista de variables
    public void setHorasSemana(String dia, ArrayList<String> ListaHoras){
        switch (dia){
            case "lunes":
                Lunes.addAll(ListaHoras);
                break;
                case "martes":
                    Martes.addAll(ListaHoras);
                    break;
                    case "miercoles":
                        Miercoles.addAll(ListaHoras);
                        break;
                        case "jueves":
                            Jueves.addAll(ListaHoras);
                            break;
                            case "viernes":
                                Viernes.addAll(ListaHoras);
                                break;
                                case "sabados":
                                    Sabados.addAll(ListaHoras);
                                    break;
        }
    }

    //Return la lista del dia que se pida
    public ArrayList<String> getDiaHoras(int dia){
        return switch (dia) {
            case 1 -> Lunes;
            case 2 -> Martes;
            case 3 -> Miercoles;
            case 4 -> Jueves;
            case 5 -> Viernes;
            case 6 -> Sabados;
            default -> null;
        };
    }

    //Pasar de una hora a un indice
    private int traductor(String hora){
        return switch(hora){
            case "7:00" -> 0;
            case "8:00" -> 1;
            case "9:00" -> 2;
            case "10:00" -> 3;
            case "11:00" -> 4;
            case "12:00" -> 5;
            case "13:00" -> 6;
            case "14:00" -> 7;
            case "15:00" -> 8;
            case "16:00" -> 9;
            case "17:00" -> 10;
            case "18:00" -> 11;
            default -> -1;
        };
    }

    //Ver como se comparte el horario
    public void revisarHorario(){
        for(int i = 1 ; i < 7; i++){
            ArrayList<String> dia = this.getDiaHoras(i);
            if(!dia.isEmpty()){
                for(String hora : dia){
                    System.out.println(i+"--"+hora);
                }
            }
        }
    }

    public ArrayList<String> DevolverIndices(){
        ArrayList<String> indices = new ArrayList<>();
        int x = 0;
        for(int i = 1 ; i < 7; i++){
            ArrayList<String> dia = this.getDiaHoras(i);
            if(!dia.isEmpty()){
                for(String hora : dia){
                    indices.add((x)+"|"+traductor(hora));
                }
            }
            x++;
        }
        return indices;
    }

    //Getters
    public Boolean verDispo(String hora, int dia){
        return getDiaHoras(dia).contains(hora);
    }

    public ArrayList<String> getLunes(){
        return Lunes;
    }

    public ArrayList<String> getMartes(){
        return Martes;
    }

    public ArrayList<String> getMiercoles(){
        return Miercoles;
    }

    public ArrayList<String> getJueves(){
        return Jueves;
    }

    public ArrayList<String> getViernes(){
        return Viernes;
    }

    public ArrayList<String> getSabados() {
        return Sabados;
    }
}
