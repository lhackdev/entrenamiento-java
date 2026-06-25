package com.gestionspersona.entrenamiento.puerto;

import com.gestionspersona.entrenamiento.modelo.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaRepository {
    Persona save(Persona persona);

    Optional<Persona> findById(Long id);

    List<Persona> findAllByOrderByApellidoAsc();

    boolean existsByEmailAndIdNot(String email, Long id);

    void deleteById(Long id);
}
