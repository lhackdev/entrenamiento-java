package com.gestionspersona.entrenamiento.controller;

import com.gestionspersona.entrenamiento.comandos.ComandoGuardarPersona;
import com.gestionspersona.entrenamiento.dtos.PersonaDTO;
import com.gestionspersona.entrenamiento.dtos.PersonaPageDTO;
import com.gestionspersona.entrenamiento.manejadores.BuscarAvanzadoPersonaManejador;
import com.gestionspersona.entrenamiento.manejadores.GuardarPersonaManejador;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final GuardarPersonaManejador guardarPersonaManejador;
    private final BuscarAvanzadoPersonaManejador buscarAvanzadoPersonaManejador;

    public PersonaController(GuardarPersonaManejador guardarPersonaManejador, BuscarAvanzadoPersonaManejador buscarAvanzadoPersonaManejador) {
        this.guardarPersonaManejador = guardarPersonaManejador;
        this.buscarAvanzadoPersonaManejador = buscarAvanzadoPersonaManejador;
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> registrarPersona(@Valid @RequestBody ComandoGuardarPersona comandoGuardarPersona) {
        PersonaDTO personaCreada = guardarPersonaManejador.guardar(comandoGuardarPersona);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaCreada);
    }

    @GetMapping("/search")
    public ResponseEntity<PersonaPageDTO> buscarPersonas(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellido", required = false) String apellido,
            @RequestParam(name = "edadMin", required = false) Integer edadMin,
            @RequestParam(name = "edadMax", required = false) Integer edadMax,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "apellido,asc") String sort) {
        PersonaPageDTO resultado = buscarAvanzadoPersonaManejador.buscar(nombre, apellido, edadMin, edadMax, page, size, sort);
        return ResponseEntity.ok(resultado);
    }
}