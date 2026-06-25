package com.gestionspersona.entrenamiento.controller;

import com.gestionspersona.entrenamiento.comandos.ComandoGuardarPersona;
import com.gestionspersona.entrenamiento.dtos.PersonaDTO;
import com.gestionspersona.entrenamiento.manejadores.GuardarPersonaManejador;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final GuardarPersonaManejador guardarPersonaManejador;

    public PersonaController(GuardarPersonaManejador guardarPersonaManejador) {
        this.guardarPersonaManejador = guardarPersonaManejador;
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> registrarPersona(@Valid @RequestBody ComandoGuardarPersona comandoGuardarPersona) {
        PersonaDTO personaCreada = guardarPersonaManejador.guardar(comandoGuardarPersona);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaCreada);
    }
}