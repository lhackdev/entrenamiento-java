package com.gestionspersona.entrenamiento.servicio;

import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.puerto.IPersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class ActualizarService {

    private final IPersonaRepository personaRepository;
    private final BuscarService buscarService;
    private final ValidarEmailService validarEmailService;
    private final SanitizacionService sanitizacionService;

    public ActualizarService(IPersonaRepository personaRepository,
                             BuscarService buscarService,
                             ValidarEmailService validarEmailService,
                             SanitizacionService sanitizacionService) {
        this.personaRepository = personaRepository;
        this.buscarService = buscarService;
        this.validarEmailService = validarEmailService;
        this.sanitizacionService = sanitizacionService;
    }

    public Persona actualizar(Long id, Persona persona) {
        sanitizacionService.sanitizar(persona);
        Persona personaExistente = buscarService.buscarPersonaOFallar(id);
        validarEmailService.validarEmailUnico(persona.email, id);
        personaExistente.actualizar(persona.nombre, persona.apellido, persona.email, persona.fechaNacimiento);
        return personaRepository.save(personaExistente);
    }
}

