package com.gestionspersona.entrenamiento.manejadores;

import com.gestionspersona.entrenamiento.dtos.PersonaDTO;
import com.gestionspersona.entrenamiento.servicio.EliminarService;

public class EliminarPersonaManejador {
    private final EliminarService eliminarService;

    public EliminarPersonaManejador(EliminarService eliminarService) {
        this.eliminarService = eliminarService;
    }

    public PersonaDTO eliminar(Long id) {
        eliminarService.eliminar(id);
        return null;
    }
}
