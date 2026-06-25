package com.gestionspersona.entrenamiento.manejadores;

import com.gestionspersona.entrenamiento.dtos.PersonaDTO;
import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.servicio.ActualizarService;

public class ActualizarPersonaManejador {
    private final ActualizarService actualizarService;

    public ActualizarPersonaManejador(ActualizarService actualizarService) {
        this.actualizarService = actualizarService;
    }

    public PersonaDTO actualizar(Long id, PersonaDTO personaDTO) {
        Persona personaModel = personaDTO.aModelo();
        Persona personaActualizada = actualizarService.actualizar(id, personaModel);
        return PersonaDTO.desdeModelo(personaActualizada);
    }
}
