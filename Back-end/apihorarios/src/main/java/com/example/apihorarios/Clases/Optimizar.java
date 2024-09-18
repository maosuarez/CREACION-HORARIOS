package com.example.apihorarios.Clases;
import java.util.ArrayList;
import java.util.Collections;

//Objeto Diseñado para mejorar la calidad de los horarios
public class Optimizar {

    //Atributos
    //------------------------------------------------------
    private ArrayList<int [][]> mejores;
    private ArrayList<String [][]> mejoresTexto;
    private ArrayList<ArrayList<OpcionesMateria>> mejoresCodi;

    private ArrayList<int[][]> inter;
    private ArrayList<String[][]> interTexto;
    private ArrayList<ArrayList<OpcionesMateria>> interCodi;

    private ArrayList<int[][]> malos;
    private ArrayList<String[][]> malosTexto;
    private ArrayList<ArrayList<OpcionesMateria>> malosCodi;

    private ArrayList<int[][]> origen;
    private ArrayList<String[][]> origenTexto;
    private ArrayList<ArrayList<OpcionesMateria>> origenCodi;

    private ArrayList<ArrayList<Integer>> listaHuecos;
    private ArrayList<ArrayList<Integer>> listaBloques;

    private ArrayList<Integer> adecuados;
    private ArrayList<Integer> faciles;
    private Traductor traductor;
    //---------------------------------------------------------

    //Constructor
    public Optimizar(){}

    public Optimizar(ObjetoAuxiliar obj){
        origen = obj.getPosiblesHorarios();
        origenCodi = obj.getListMates();
        listaBloques = new ArrayList<>();
        listaHuecos = new ArrayList<>();
        adecuados = new ArrayList<>();
        faciles = new ArrayList<>();
    }

    public Optimizar(ObjetoAuxiliar obj,ObjetoAuxiliar obj1,ObjetoAuxiliar obj2,ObjetoAuxiliar obj3){
        origenTexto = obj.getHorariosTexto();
        origenCodi = obj.getListMates();

        mejoresTexto = obj1.getHorariosTexto();
        mejoresCodi = obj1.getListMates();

        interTexto = obj2.getHorariosTexto();
        interCodi = obj2.getListMates();

        malosTexto = obj3.getHorariosTexto();
        malosCodi = obj3.getListMates();
    }

    //metodos para mejorar calidad de horarios
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

        origenTexto = traductor.transformarTexto(origen);

        for( int i = 0 ; i < origen.size() ; i++ ){

            if(promedio(listaHuecos.get(i)) < valorE && promedio(listaBloques.get(i)) < pesadoE){
                mejores.add(origen.get(i));
                mejoresCodi.add(origenCodi.get(i));
                mejoresTexto.add(origenTexto.get(i));
            } else if (promedio(listaHuecos.get(i)) < valorE || promedio(listaBloques.get(i)) < pesadoE) {
                inter.add(origen.get(i));
                interCodi.add(origenCodi.get(i));
                interTexto.add(origenTexto.get(i));
            } else if (promedio(listaHuecos.get(i)) < valorM || promedio(listaBloques.get(i)) < pesadoM) {
                malos.add(origen.get(i));
                malosCodi.add(origenCodi.get(i));
                malosTexto.add(origenTexto.get(i));
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

    //Setter
    public void setTraductor(Traductor traduc){
        traductor = traduc;
    }

    //Getters
    public ObjetoAuxiliar getMejoresTextos(){
        return new ObjetoAuxiliar(mejoresTexto,mejoresCodi,"true");
    }
    public ObjetoAuxiliar getInterTextos(){
        return new ObjetoAuxiliar(interTexto,interCodi,"true");
    }
    public ObjetoAuxiliar getMalosTextos(){
        return new ObjetoAuxiliar(malosTexto,malosCodi,"true");
    }
    public ObjetoAuxiliar getOrigenTexto(){
        return new ObjetoAuxiliar(origenTexto,origenCodi,"true");
    }
}
