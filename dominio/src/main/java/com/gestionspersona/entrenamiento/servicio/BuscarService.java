package com.gestionspersona.entrenamiento.servicio;

import com.gestionspersona.entrenamiento.exception.PersonaNoEncontradaException;
import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.puerto.IPersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarService {

    private final IPersonaRepository personaRepository;

    public BuscarService(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona buscarPorId(Long id) {
        return buscarPersonaOFallar(id);
    }

    public Persona buscarPersonaOFallar(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException(id));
    }
}

