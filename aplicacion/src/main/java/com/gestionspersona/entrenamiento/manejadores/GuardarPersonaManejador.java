package com.gestionspersona.entrenamiento.manejadores;

import com.gestionspersona.entrenamiento.comandos.ComandoGuardarPersona;
import com.gestionspersona.entrenamiento.dtos.PersonaDTO;
import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.servicio.GuardarService;
import org.springframework.stereotype.Service;

@Service
public class GuardarPersonaManejador {
    private final GuardarService guardarService;

    public GuardarPersonaManejador(GuardarService guardarService) {
        this.guardarService = guardarService;
    }

    public PersonaDTO guardar(ComandoGuardarPersona comandoGuardarPersona) {
        Persona personaModel = comandoGuardarPersona.aModelo(comandoGuardarPersona);
        Persona personaGuardada = guardarService.registrar(personaModel);
        return PersonaDTO.desdeModelo(personaGuardada);
    }
}
