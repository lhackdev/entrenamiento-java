package com.gestionspersona.entrenamiento.puertos;

import com.gestionspersona.entrenamiento.modelo.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBuscarPersonaRepository {
    Page<Persona> search(String nombre, String apellido, Integer edadMin, Integer edadMax, Pageable pageable);
}
