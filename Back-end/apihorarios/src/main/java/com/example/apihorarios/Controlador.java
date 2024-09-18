package com.example.apihorarios;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apihorarios.Clases.BodyObject;
import com.example.apihorarios.Clases.CrearArrays;
import com.example.apihorarios.Clases.HorasSemana;
import com.example.apihorarios.Clases.Materias;
import com.example.apihorarios.Clases.ObjetoAuxiliar;
import com.example.apihorarios.Clases.Optimizar;
import com.example.apihorarios.Clases.Preferencias;
import com.example.apihorarios.Clases.Traductor;
import com.example.apihorarios.MySQL.BaseDatos;

@RestController
public class Controlador {
    
    @PostMapping("/horarios")
    public Optimizar obtenerHorarios(@RequestBody BodyObject bodyObject){
        try{
            System.out.println("Obteniendo horarios");
        
            BaseDatos basedatos = new BaseDatos( "root", "Maotenis2005!","jdbc:mysql://localhost:3306/materias-horarios");
            ArrayList<Materias> listaMaterias = basedatos.getDatos();

            ArrayList<Preferencias> listaPreferencias = bodyObject.getLista();

            HorasSemana horasLibres = bodyObject.getLibres();

            CrearArrays creador = new CrearArrays(listaMaterias,listaPreferencias,horasLibres);
            ObjetoAuxiliar obj = creador.Generar();

            if(obj == null){
                System.out.println("No pudimos Generar ningun horario con las anteriores restricciones reviselas e inetente de nuevo");
            }else{
                if(!obj.getPosiblesHorarios().isEmpty()) {

                    Traductor traduc = new Traductor(listaMaterias);

                    //Optimizar
                    Optimizar opti = new Optimizar(obj);
                    opti.setTraductor(traduc);

                    opti.Optimos();

                    return opti;
                }
            }
            return new Optimizar();

        }catch(Error e){
            return new Optimizar();
        }
    }

    @GetMapping("/saludo")
    public String obtenerSaludo(){
        return "Hola, bienvenido a la API de horarios";
    }
    @GetMapping("/saludo/adios")
    public String obtenerSaludoAdios(){
        return "Adios, gracias por usar la API de horarios";
    }
    @PostMapping("/json")
    public ArrayList<Preferencias> pruba(@RequestBody BodyObject objPrueba){
        return objPrueba.getLista();
    }
}
