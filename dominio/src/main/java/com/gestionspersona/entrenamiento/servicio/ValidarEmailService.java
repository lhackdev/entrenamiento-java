package com.gestionspersona.entrenamiento.servicio;

import com.gestionspersona.entrenamiento.exception.EmailDuplicadoException;
import com.gestionspersona.entrenamiento.puerto.IPersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidarEmailService {
    private final IPersonaRepository personaRepository;

    public ValidarEmailService(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void validarEmailUnico(String email, Long idExcluido) {
        if (personaRepository.existsByEmailAndIdNot(email, idExcluido)) {
            throw new EmailDuplicadoException(email);
        }
    }
}
