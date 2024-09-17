package com.example.apihorarios.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.example.apihorarios.Clases.HorasSemana;
import com.example.apihorarios.Clases.Materias;
import com.example.apihorarios.Clases.OpcionesMateria;

import java.sql.SQLException;

public class BaseDatos {

    private static final String URL = "jdbc:mysql://localhost:3306/materias-horarios";
    private String username;
    private String password;

    public BaseDatos(String username_actu, String password_actu) {
        this.username = username_actu;
        this.password = password_actu;
    }

    public ArrayList<Materias> getDatos() {
        ArrayList<Materias> listaMaterias = new ArrayList<>();
        try (
            Connection connection = DriverManager.getConnection(URL, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM materias")) {


            while (resultSet.next()) {
                String nombre = resultSet.getString("materia");
                String codigo = resultSet.getString("codigo");
                HorasSemana horas = desincript(resultSet.getString("horario"));
                String profesor = resultSet.getString("profesor");

                int indice = verificarExistencia(nombre, listaMaterias);

                Materias materia;
                OpcionesMateria opc = new OpcionesMateria(codigo.toLowerCase(), profesor.toLowerCase(), horas);
                if(indice < 0){
                    int nomate = listaMaterias.size() + 1 ;
                    materia = new Materias(nombre.toLowerCase(), nomate, opc);
                    listaMaterias.add(materia);
                }else{
                    listaMaterias.get(indice).agregarOpcionesMateria(opc);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMaterias;
    }

    private ArrayList<String> RangoHoras(int inicio, int fin){
        ArrayList<String> listaRangos = new ArrayList<>();
        String texto;
        for(int i = inicio; i < fin; i++){
            texto = i+":00";
            listaRangos.add(texto);
        }
        return listaRangos;
    }

    private int verificarExistencia(String materia, ArrayList<Materias> listaMaterias) {
        if(!listaMaterias.isEmpty()){
            for(int i = 0; i<listaMaterias.size(); i++){
                Materias materia1 = listaMaterias.get(i);
                if (materia.equalsIgnoreCase(materia1.getNombreMateria())) {
                    return i;
                }
            }
            return -1;
        }else{
            return -1;
        }
    }

    private HorasSemana desincript(String incript){
        StringTokenizer tokens = new StringTokenizer(incript, ";");
        HorasSemana horario = new HorasSemana();
        String texto;
        int maxiloop = tokens.countTokens();

        for(int i = 0; i < maxiloop; i++){
            texto = tokens.nextToken();
            StringTokenizer tokens2 = new StringTokenizer(texto, "|");
            String dia = tokens2.nextToken();
            String horas = tokens2.nextToken();
            StringTokenizer tokens3 = new StringTokenizer(horas, "-");
            int Inicio = Integer.parseInt(tokens3.nextToken());
            int Fin = Integer.parseInt(tokens3.nextToken());
            ArrayList<String> listaDia = RangoHoras(Inicio, Fin);
            horario.setHorasSemana(dia.toLowerCase(),listaDia);
        }
        return horario;
    }
}
