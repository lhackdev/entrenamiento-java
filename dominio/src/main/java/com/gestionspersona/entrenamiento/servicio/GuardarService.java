package com.gestionspersona.entrenamiento.servicio;

import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.puerto.IPersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class GuardarService {

    private final IPersonaRepository personaRepository;
    private final ValidarEmailService validarEmailService;
    private final SanitizacionService sanitizacionService;

    public GuardarService(IPersonaRepository personaRepository,
                          ValidarEmailService validarEmailService,
                          SanitizacionService sanitizacionService) {
        this.personaRepository = personaRepository;
        this.validarEmailService = validarEmailService;
        this.sanitizacionService = sanitizacionService;
    }

    public Persona registrar(Persona persona) {
        validarEmailService.validarEmailUnico(persona.email, 0L);
        sanitizacionService.sanitizar(persona);
        return personaRepository.save(persona);
    }
}
