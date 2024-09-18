package com.example.apihorarios;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apihorarios.Clases.BodyObject;
import com.example.apihorarios.Clases.CrearArrays;
import com.example.apihorarios.Clases.HorasSemana;
import com.example.apihorarios.Clases.Materias;
import com.example.apihorarios.Clases.ObjetoAuxiliar;
import com.example.apihorarios.Clases.Optimizar;
import com.example.apihorarios.Clases.Preferencias;
import com.example.apihorarios.Clases.ResponseArray;
import com.example.apihorarios.Clases.Traductor;
import com.example.apihorarios.MySQL.BaseDatos;

@RestController
public class Controlador {
    
    @CrossOrigin(origins="*")
    @PostMapping("/horarios")
    public ResponseEntity<Optimizar> obtenerHorarios(@RequestBody BodyObject bodyObject) {
        try {
            System.out.println("Obteniendo horarios");

            // Validar que el bodyObject no sea nulo
            if (bodyObject == null || bodyObject.getLista() == null || bodyObject.getLibres() == null) {
                System.out.println("Error: Cuerpo de la solicitud o datos inválidos");
                return ResponseEntity.badRequest().body(new Optimizar());
            }

            BaseDatos basedatos = null;
            try {
                basedatos = conectarBaseDatos();
            } catch (Error e) {
                System.out.println("Error: No se pudo conectar a la base de datos");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Optimizar());
            }

            ArrayList<Materias> listaMaterias;
            try {
                listaMaterias = basedatos.getDatos();
            } catch (Error e) {
                System.out.println("Error: No se pudieron obtener los datos de la base de datos");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Optimizar());
            }

            ArrayList<Preferencias> listaPreferencias = bodyObject.getLista();
            HorasSemana horasLibres = bodyObject.getLibres();

            // Validar que las listas no estén vacías
            if (listaPreferencias.isEmpty() || listaMaterias.isEmpty()) {
                System.out.println("Error: La lista de materias o preferencias está vacía");
                return ResponseEntity.badRequest().body(new Optimizar());
            }

            CrearArrays creador = new CrearArrays(listaMaterias, listaPreferencias, horasLibres);
            ObjetoAuxiliar obj = creador.Generar();

            if (obj == null || obj.getPosiblesHorarios().isEmpty()) {
                System.out.println("No pudimos generar ningún horario con las restricciones dadas. Revísalas e intenta de nuevo.");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Optimizar());
            }

            Traductor traduc = new Traductor(listaMaterias);
            
            // Optimización
            Optimizar opti = new Optimizar(obj);
            opti.setTraductor(traduc);

            try {
                opti.Optimos();
            } catch (Exception e) {
                System.out.println("Error: Ocurrió un problema durante la optimización");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Optimizar());
            }

            return ResponseEntity.ok(opti);

        } catch (Exception e) {
            System.out.println("Error no controlado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Optimizar());
        }
    }


    @CrossOrigin(origins="*")
    @GetMapping("/opciones/codigo")
public ResponseEntity<ResponseArray> obtenerCodigos(@RequestParam(value = "name", defaultValue = "World") String name) {
    try {
        // Validar que el nombre no esté vacío
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: El parámetro 'name' no puede estar vacío");
            return ResponseEntity.badRequest().body(new ResponseArray());
        }

        BaseDatos basedatos = conectarBaseDatos();
        ArrayList<String> listaMaterias = basedatos.getOpciones("codigo", name);

        if (listaMaterias.isEmpty()) {
            System.out.println("No se encontraron materias con el nombre proporcionado");
        }

        return ResponseEntity.ok(new ResponseArray(listaMaterias));

    } catch (SQLException e) {
        System.out.println("Error: Problema con la base de datos - " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseArray());
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseArray());
    }
}

@CrossOrigin(origins="*")
@GetMapping("/opciones/profesor")
public ResponseEntity<ResponseArray> obtenerProfesores(@RequestParam(value = "name", defaultValue = "World") String name) {
    try {
        // Validar que el nombre no esté vacío
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: El parámetro 'name' no puede estar vacío");
            return ResponseEntity.badRequest().body(new ResponseArray());
        }

        BaseDatos basedatos = conectarBaseDatos();
        ArrayList<String> listaMaterias = basedatos.getOpciones("profesor", name);

        if (listaMaterias.isEmpty()) {
            System.out.println("No se encontraron profesores con el nombre proporcionado");
        }

        return ResponseEntity.ok(new ResponseArray(listaMaterias));

    } catch (SQLException e) {
        System.out.println("Error: Problema con la base de datos - " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseArray());
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseArray());
    }
}

@CrossOrigin(origins="*")
@GetMapping("/opciones/materia")
public ResponseEntity<ResponseArray> obtenerMaterias(@RequestParam(value = "name", defaultValue = "World") String name){
    try {
        // Validar que el nombre no esté vacío
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: El parámetro 'name' no puede estar vacío");
            return ResponseEntity.badRequest().body(new ResponseArray());
        }

        BaseDatos basedatos = conectarBaseDatos();
        ArrayList<String> listaMaterias = basedatos.sugerencias(name);

        if (listaMaterias.isEmpty()) {
            System.out.println("No se encontraron materias con el nombre proporcionado");
        }

        return ResponseEntity.ok( new ResponseArray(listaMaterias));

    } catch (SQLException e) {
        System.out.println("Error: Problema con la base de datos - " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseArray());
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseArray());
    }
}

// Método auxiliar para la conexión a la base de datos
private BaseDatos conectarBaseDatos() throws SQLException {
    return new BaseDatos("root", "Maotenis2005!", "jdbc:mysql://localhost:3306/materias-horarios");
}

    
}
