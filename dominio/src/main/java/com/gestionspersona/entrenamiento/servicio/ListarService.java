package com.gestionspersona.entrenamiento.servicio;

import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.puerto.IPersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarService {

    private final IPersonaRepository personaRepository;

    public ListarService(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> listarPersonas() {
        return personaRepository.findAllByOrderByApellidoAsc();
    }
}
