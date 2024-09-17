package com.example.apihorarios;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apihorarios.Clases.Materias;
import com.example.apihorarios.MySQL.BaseDatos;

@RestController
public class Controlador {
    
    @GetMapping("/horarios")
    public ArrayList<Materias> obtenerHorarios(){
        System.out.println("Obteniendo horarios");
        
        BaseDatos basedatos = new BaseDatos( "root", "Maotenis2005!");
        ArrayList<Materias> listaMaterias = basedatos.getDatos();

        return listaMaterias;
        
    }

    @GetMapping("/saludo")
    public String obtenerSaludo(){
        return "Hola, bienvenido a la API de horarios";
    }
}
