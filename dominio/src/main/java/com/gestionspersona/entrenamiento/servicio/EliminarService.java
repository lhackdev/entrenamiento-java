package com.gestionspersona.entrenamiento.servicio;

import com.gestionspersona.entrenamiento.puerto.IPersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class EliminarService {

    private final BuscarService buscarService;
    private final IPersonaRepository personaRepository;

    public EliminarService(IPersonaRepository personaRepository, BuscarService buscarService) {
        this.personaRepository = personaRepository;
        this.buscarService = buscarService;
    }

    public void eliminar(Long id) {
        buscarService.buscarPersonaOFallar(id);
        personaRepository.deleteById(id);
    }
}
