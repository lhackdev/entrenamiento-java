package com.gestionspersona.entrenamiento.manejadores;

import com.gestionspersona.entrenamiento.dtos.PersonaDTO;
import com.gestionspersona.entrenamiento.dtos.PersonaPageDTO;
import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.modelo.PersonaPage;
import com.gestionspersona.entrenamiento.puertos.IBuscarPersonaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarAvanzadoPersonaManejador {

    private final IBuscarPersonaRepository buscarPersonaRepository;

    public BuscarAvanzadoPersonaManejador(IBuscarPersonaRepository buscarPersonaRepository) {
        this.buscarPersonaRepository = buscarPersonaRepository;
    }

    public PersonaPageDTO buscar(String nombre, String apellido, Integer edadMin, Integer edadMax, int page, int size, String sort) {
        Pageable pageable = crearPageable(page, size, sort);
        Page<Persona> resultado = buscarPersonaRepository.search(nombre, apellido, edadMin, edadMax, pageable);

        PersonaPage personas = new PersonaPage(
                resultado.getContent(),
                resultado.getTotalElements(),
                resultado.getTotalPages(),
                page,
                size
        );
        return convertirADto(personas);
    }

    private Pageable crearPageable(int page, int size, String sort) {
        if (sort == null || sort.isBlank()) {
            return PageRequest.of(page, size, Sort.by("apellido").ascending());
        }

        String[] parts = sort.split(",");
        String property = parts[0].trim();
        Sort.Direction direction = (parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim()))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(direction, property));
    }

    private PersonaPageDTO convertirADto(PersonaPage personaPage) {
        List<PersonaDTO> dtos = personaPage.content.stream()
                .map(PersonaDTO::desdeModelo)
                .toList();
        return new PersonaPageDTO(
                dtos,
                personaPage.totalElements,
                personaPage.totalPages,
                personaPage.currentPage,
                personaPage.size
        );
    }
}
