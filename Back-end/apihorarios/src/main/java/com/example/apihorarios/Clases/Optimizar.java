package com.example.apihorarios.Clases;
import java.util.ArrayList;
import java.util.Collections;

public class Optimizar {
    private ArrayList<int [][]> mejores;
    private ArrayList<ArrayList<OpcionesMateria>> mejoresCodi;

    private ArrayList<int[][]> inter;
    private ArrayList<ArrayList<OpcionesMateria>> interCodi;

    private ArrayList<int[][]> malos;
    private ArrayList<ArrayList<OpcionesMateria>> malosCodi;

    private ArrayList<int[][]> origen;
    private ArrayList<ArrayList<OpcionesMateria>> origenCodi;

    private ArrayList<ArrayList<Integer>> listaHuecos;
    private ArrayList<ArrayList<Integer>> listaBloques;

    private ArrayList<Integer> adecuados;
    private ArrayList<Integer> faciles;

    public Optimizar(ObjetoAuxiliar obj){
        origen = obj.getPosiblesHorarios();
        origenCodi = obj.getListMates();
        listaBloques = new ArrayList<>();
        listaHuecos = new ArrayList<>();
        adecuados = new ArrayList<>();
        faciles = new ArrayList<>();
    }

    public void Optimos(){

        mejores = new ArrayList<>();
        mejoresCodi = new ArrayList<>();
        inter = new ArrayList<>();
        interCodi = new ArrayList<>();
        malos = new ArrayList<>();
        malosCodi = new ArrayList<>();

        //Bucle de todos los horarios
        for (int[][] ints : origen) {

            //devuelve las listas de valores de huecos y de clases
            ObjetoAuxiliar obj = evaluacion(ints);
            listaHuecos.add(obj.getHuecos());
            listaBloques.add(obj.getBloques());

        }

        //Con los huecos, devuelve los valores esperados
        agreProm1();
        int valorE = cuartil1(adecuados);
        int valorM = cuartil2(adecuados);

        //System.out.println("ValorE "+valorE);
        //System.out.println("ValorM "+valorM);

        //Con los bloques, devuelve los valores esperados
        agreProm2();
        int pesadoE = cuartil1(faciles);
        int pesadoM = cuartil2(faciles);

        //System.out.println("PesadoE "+pesadoE);
        //System.out.println("PesadoM "+pesadoM);

        for( int i = 0 ; i < origen.size() ; i++ ){

            System.out.println("promedio " + promedio(listaHuecos.get(i)) + " otro promedio " + promedio(listaBloques.get(i)));


            if(promedio(listaHuecos.get(i)) < valorE && promedio(listaBloques.get(i)) < pesadoE){
                mejores.add(origen.get(i));
                mejoresCodi.add(origenCodi.get(i));
            } else if (promedio(listaHuecos.get(i)) < valorE || promedio(listaBloques.get(i)) < pesadoE) {
                inter.add(origen.get(i));
                interCodi.add(origenCodi.get(i));
            } else if (promedio(listaHuecos.get(i)) < valorM || promedio(listaBloques.get(i)) < pesadoM) {
                malos.add(origen.get(i));
                malosCodi.add(origenCodi.get(i));
            }
        }

    }

    private int promedio(ArrayList<Integer> huecos){
        int prom = 0;
        for(int numero : huecos){
            prom += numero;
        }
        return prom;
    }

    private void agreProm1(){
        for(ArrayList<Integer> huecos : listaHuecos){
            int prom = promedio(huecos);
            adecuados.add(prom);
        }
    }

    private void agreProm2(){
        for(ArrayList<Integer> block : listaBloques){
            int prom = promedio(block);
            faciles.add(prom);
        }
    }

    private int cuartil1(ArrayList<Integer> lista){
        Collections.sort(lista);
        int pos = (int) (lista.size() * 0.30);
        return lista.get(pos);
    }

    private int cuartil2(ArrayList<Integer> lista){
        Collections.sort(lista);
        int pos = (int) (lista.size() * 0.60);
        return lista.get(pos);
    }

    private ObjetoAuxiliar evaluacion(int[][] horario ){

        //Arreglos que van a llevar la info del horario
        ArrayList<Integer> huecos = new ArrayList<>();
        ArrayList<Integer> bloques = new ArrayList<>();

        //Empezar el bucle para los deias de la semana
        for(int c = 0 ; c < 6 ; c++){

            //Variables importantes para saber el orden de los horarios
            int hueco = -1;
            int block = 0;
            int maxHueco = 0;
            int maxBlock = 0;

            //Empezar el bucle para las horas del dia
            for(int f = 0 ; f < 12 ; f++ ){

                //Comprobar que se este en una clase o en un hueco
                if( horario[f][c] != 0){

                    //si el hueco es vacio significa que esta en clase desde antes
                    if(hueco == 0){

                        //Se suma 1 a la variable de clases
                        block += 1;

                        //verificar que ahora este en la primera clase
                    } else if (hueco == -1) {

                        //fijar los huecos a 0
                        hueco = 0;

                        //Verificar que sea la primera hora de clase despues de un hueco
                    } else if (hueco > 0) {

                        //Ver si el bloque anterior es el mayor
                        if(maxBlock < block){
                            maxBlock = block;
                        }
                        block = 1;

                        //Ver que el hueco anterior sea el mayor
                        if(maxHueco < hueco){
                            maxHueco = hueco;
                        }
                        hueco = 0;
                    }
                } else {
                    if (hueco != -1){
                        hueco += 1;
                    }
                }
            }
            huecos.add(maxHueco);
            bloques.add(maxBlock);
        }
        return new ObjetoAuxiliar(huecos, bloques, true);
    }

    public ObjetoAuxiliar getMejores() {
        return new ObjetoAuxiliar(mejoresCodi, mejores);
    }

    public ObjetoAuxiliar getInter() {
        return new ObjetoAuxiliar(interCodi, inter);
    }

    public ObjetoAuxiliar getMalos() {
        return new ObjetoAuxiliar(malosCodi, malos);
    }
}
