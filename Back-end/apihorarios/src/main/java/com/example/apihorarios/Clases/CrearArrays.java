package com.example.apihorarios.Clases;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CrearArrays {
    private ArrayList<Materias> listaPosibles;

    private ArrayList<Preferencias> listaPreferencias;

    private HorasSemana horasLibres;

    private int[][] ArrayActual = new int[12][6];

    private int contador;
    public Boolean listo;
    public Boolean listo1;
    public  Boolean notFound;

    public CrearArrays(){
    }

    public CrearArrays(ArrayList<Materias> materias, ArrayList<Preferencias> prefere, HorasSemana horas){
        listaPosibles = materias;
        listaPreferencias = prefere;
        horasLibres = horas;
    }

    public ObjetoAuxiliar Generar(){

        //Se crean dos listas vacias
        ArrayList<int[][]> posiblesHorarios = new ArrayList<>();
        ArrayList<ArrayList<OpcionesMateria>> listMates = new ArrayList<>();

        //Variables de control en valores iniciales
        contador = 0;
        listo1 = true;
        notFound = false;

        //Se crea el horario 0, es nulo, y se agrega a la lista
        int[][] matrizini = matrizCero();
        posiblesHorarios.add(matrizini);
        listMates.add(new ArrayList<>());

        //Se crea un objeto para enviar las listas
        ObjetoAuxiliar obj = new ObjetoAuxiliar(listMates,posiblesHorarios);

        //Se llama a la funcion auxiliar
        obj = GenerarAux1(obj, 0);

        //En caso de error
        if(listo1){
            return obj;
        }

        /* //Definir variables para random
        int x;
        ArrayList<Integer> indicesRandom = new ArrayList<>();
        ObjetoAuxiliar objetoRandom;
        ArrayList<int[][]> randomHorarios = new ArrayList<>();
        ArrayList<ArrayList<OpcionesMateria>> randomCodigos = new ArrayList<>();*/

        

        //Abrir el ciclo de 3

        /*
        for(int i = 0; i<3; i++){
            x = (int)( Math.random() * posiblesHorarios.size() );
            System.out.println(posiblesHorarios.size());
            if(!indicesRandom.contains(x)){
                indicesRandom.add(x);
                randomCodigos.add(listMates.get(x));
                randomHorarios.add(posiblesHorarios.get(x));
            }else{
                i--;
            }

        }

         */
        //objetoRandom = new ObjetoAuxiliar(randomCodigos,randomHorarios);

        

        return obj;
    }

    private ObjetoAuxiliar GenerarAux1(ObjetoAuxiliar obj, int i){
        // Crear Dos listas vacias para actualizar
        ArrayList<int[][]> Actualizada = new ArrayList<>();
        ArrayList<ArrayList<OpcionesMateria>> ActuaMates = new ArrayList<>();

        //Extraer las dos listas enviadas con el objetoAuxiliar
        ArrayList<int[][]> posibilidades = obj.getPosiblesHorarios();
        ArrayList<ArrayList<OpcionesMateria>> listMates = obj.getListMates();

        //Ver si la lista es vacia
        if(!posibilidades.isEmpty()){

            //Comenzar a iterar en las opciones de esa lista, que son arreglos
            for( int x=0; x < posibilidades.size();x++){

                //Inicio una variable de listo en falso
                listo = false;

                //Crear un nuevo objeto auxiliar y asignarle el arreglo actual junto con un ArrayList<OpcionesMateria>
                ObjetoAuxiliar obj1 = new ObjetoAuxiliar(listMates.get(x), posibilidades.get(x));

                //Se espera recibir una lista de las mismad dimensiones pero con horarios ya ajustados dado el indice
                obj1 = GenerarAux2(obj1, i);

                //En caso en que devuelva un nulo se pierde la info y se sigue con el siguiente horario
                if(!listo) {

                    listo1 = false;

                    //Son listas temporales para poder traer los datos que se mandaron en el objeto auxiliar
                    assert obj1 != null;
                    ArrayList<int[][]> actuHora = obj1.getPosiblesHorarios();
                    ArrayList<ArrayList<OpcionesMateria>> actuMates = obj1.getListMates();

                    //En las listas actualizadas se van añadiendo los horarios actualizados
                    Actualizada.addAll(actuHora);
                    ActuaMates.addAll(actuMates);

                }
            }
        }
        //En caso de que una materia que se esperaba añadir fallo se retorna un error inmediatamente
        if(listo1){
            //System.out.println("Una clase "+listaPosibles.get(i).getNombreMateria()+" no se agrego correctamente");
            return null;
        }

        //Se suma 1 a la variable i que indica cual es la materia que se esta evaluando
        i++;

        //Se crea un Objeto auxiliar para tener los horarios actualizados
        ObjetoAuxiliar obj2 = new ObjetoAuxiliar(ActuaMates, Actualizada);

        //Se verifica:
        //1. Si el contador es menor que lo esperado; es decir falta agregar materias
        //2. si la variable i sigue en el rango de la lista
        if(contador < listaPreferencias.size() && i<listaPosibles.size()){

            //Nueva variable para ver si hay un error
            listo1 = true;

            //Se entra otra vez en el metodo pero esta vez cambia la materia que se va a evaluar
            obj2 = GenerarAux1(obj2, i);

        }else if(i>listaPosibles.size()){
            notFound = true;
        }

        //En caso de que se hayan probado todas las materias pero no se haya conseguido el objetivo
        if(notFound){
            System.out.println("Error de Busqueda");
            return null;
        }

        //Se devuelve un objeto auxiliar que se supone cuenta con todos los horarios
        return obj2;
    }

    private ObjetoAuxiliar GenerarAux2(ObjetoAuxiliar obj, int i){
        //Recuperar los datos que vienen del objeto auxiliar
        int[][] posi = obj.getPosi();
        ArrayList<OpcionesMateria> listMates = obj.getMates();

        //Crear dos listas vacias para ir añadiendo los resultados
        ArrayList<int[][]> Actu = new ArrayList<>();
        ArrayList<ArrayList<OpcionesMateria>> actuMates = new ArrayList<>();

        //Con la variable i se ve que materia tenemos que evaluar
        Materias materia = listaPosibles.get(i);

        //Verificamos que:
        //1. las lista de opciones de la materia no sea vacia
        //2. Que la clase coincida con una de las clases en preferencias
        if(!materia.getListaOpciones().isEmpty() && ClaseEvaluar(materia.getNombreMateria())) {

            //Si entro significa que la clase si tiene que estar y se seala la variable listo como verdadera
            listo = true;

            //En un ciclo se empiezan a ver los diferentes horarios de la clase
            for (int j = 0; j < materia.getListaOpciones().size(); j++) {

                //Instancia del horario a evaluar
                OpcionesMateria opc = materia.getListaOpciones().get(j);

                //Se verifica:
                //1. que la clase cumpla con los profesores asignados
                //2. que no sea una clase en un espacio libre
                if (ClaseNecesaria(materia.getNombreMateria(), opc) && ClaseDispo(opc)) {

                    // para las opciones que pasan se evalua el horario
                    HorasSemana horas = opc.getHoras();

                    //metodo para ver si el horario se choca con otra clase
                    if (llenarArrays(horas, posi.clone(), materia)) {

                        //Ver que materias si sirven
                        //System.out.println(materia.getNombreMateria()+"----");

                        //De ser posible se añade el array al array disponible
                        Actu.add(ArrayActual.clone());

                        //Se crea una lista en blanco para almacenar posibles codigos
                        //Se le adicionan todos los codigos que se traian por defecto
                        ArrayList<OpcionesMateria> lista = new ArrayList<>(listMates);

                        opc.setMateria(materia.getNombreMateria());

                        //Se le adiciona el nuevo codigo
                        lista.add(opc);

                        //El contador incrementa señalando que hay una clase mas en el horario
                        contador = lista.size();

                        //La variable listo es falsa porque la clase si se añadio correctamente
                        listo = false;

                        //a una lista de listas se añade la lista de los posibles codigos
                        actuMates.add(lista);

                    }else{
                        ArrayActual = matrizCero();
                    }
                }
            }
        }

        //En caso de que una clase que se debia agregar no se hizo se devuelve nulo
        if(listo){
            return null;
        }

        //En caso de que el horario de actualizadas sea vacio se debe devolver el objeto que se recibio pero enlistado
        if(Actu.isEmpty()){
            Actu.add(posi);
            actuMates.add(listMates);
        }

        return new ObjetoAuxiliar(actuMates, Actu);
    }

    private int[][] matrizCero(){
        int[][] matriz = new int[12][6];
        for(int i=0; i < 12; i++){
            for(int j=0; j < 6; j++){
                matriz[i][j] = 0;
            }
        }
        return matriz;
    }

    private Boolean ClaseNecesaria(String materia, OpcionesMateria opc){
        for (Preferencias prefe : listaPreferencias) {
            if (prefe.getMateria().equalsIgnoreCase(materia)) {
                if (!prefe.getProfesores().isEmpty() ||
                        !prefe.getCodigos().isEmpty()) {
                    return prefe.getProfesores().contains(opc.getProfesor())
                            || prefe.getCodigos().contains(opc.getCodigo());
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean ClaseEvaluar(String materia){
        for (Preferencias prefe : listaPreferencias) {
            if (prefe.getMateria().equalsIgnoreCase(materia)) {
                return true;
            }
        }
        return false;
    }

    private Boolean ClaseDispo(OpcionesMateria opc){
        HorasSemana HorasClase = opc.getHoras();
        for(int i=1; i<=6; i++){
            ArrayList<String> Diario = HorasClase.getDiaHoras(i);
            if (!Diario.isEmpty()){
                for(String hora : Diario){
                    if(horasLibres.verDispo(hora,i)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Se reciben las horas de una opcion, una matriz que previamente se le agregaron unos valores, y la materia
    private Boolean llenarArrays(HorasSemana horas, int[][] subArray, Materias materia){

        //Crear una nueva matriz wue es la que se va a devolver
        int[][] NuevaMatriz = ajustarMatriz(matrizCero(), subArray);

        //Devuelve los indices en los que se ubicaria la materia
        ArrayList<String> listaIndices = horas.DevolverIndices();

        //Inicia un ciclo por cada uno de los indices
        for( String indices : listaIndices){

            //Divide el indice en 2
            StringTokenizer tokens = new StringTokenizer(indices,"|");

            //La primera parte del indice es la columna
            int colu = Integer.parseInt(tokens.nextToken());

            //La segunda parte es la fila
            int fila = Integer.parseInt(tokens.nextToken());

            //Se verifica que la matriz tenga un 0 en la posicion en la que se quiere poner la materia
            if(NuevaMatriz[fila][colu] == 0){

                //Se escribe el numero de la materia
                NuevaMatriz[fila][colu] = materia.getNoMate();

            }else{

                //Se devuelve falso porque la matriz no puede crearse con estos datos
                return false;
            }
        }
        //En caso de si haber puesto todos los indices
        //Se crea un clone de la matriz que recien se modifico
        ArrayActual = matrizCero();
        ArrayActual = NuevaMatriz.clone();

        //Devuelve un valor verdadero para entrar a la condicion
        return true;
    }

    private int[][] ajustarMatriz(int[][] matriznueva,int[][] matrizvieja){
        for(int i = 0; i<12; i++) {
            for (int j = 0; j < 6; j++) {
                if (matrizvieja[i][j] != 0){
                    matriznueva[i][j] = matrizvieja[i][j];
                }
            }
        }
        return matriznueva;
    }
}
