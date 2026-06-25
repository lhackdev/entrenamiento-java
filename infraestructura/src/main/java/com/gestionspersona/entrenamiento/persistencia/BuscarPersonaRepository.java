package com.gestionspersona.entrenamiento.persistencia;

import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.puertos.IBuscarPersonaRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
public class BuscarPersonaRepository implements IBuscarPersonaRepository {

    private final PersonaJpaRepository jpaRepository;

    public BuscarPersonaRepository(PersonaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Page<Persona> search(String nombre, String apellido, Integer edadMin, Integer edadMax, Pageable pageable) {
        LocalDate fechaMin = (edadMax == null) ? null : LocalDate.now().minusYears(edadMax);
        LocalDate fechaMax = (edadMin == null) ? null : LocalDate.now().minusYears(edadMin);

        Specification<PersonaEntity> spec = buildSearchSpecification(nombre, apellido, fechaMin, fechaMax);
        return jpaRepository.findAll(spec, pageable).map(PersonaEntity::toDomain);
    }

    private Specification<PersonaEntity> buildSearchSpecification(String nombre, String apellido, LocalDate fechaMin, LocalDate fechaMax) {
        return (root, query, cb) -> {
            List<Predicate> predicates = Stream.of(
                            Objects.nonNull(nombre) && !nombre.isEmpty()
                                    ? cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%") : null,
                            Objects.nonNull(apellido) && !apellido.isEmpty()
                                    ? cb.like(cb.lower(root.get("apellido")), "%" + apellido.toLowerCase() + "%") : null,
                            Objects.nonNull(fechaMin)
                                    ? cb.greaterThanOrEqualTo(root.get("fechaNacimiento"), fechaMin) : null,
                            Objects.nonNull(fechaMax)
                                    ? cb.lessThanOrEqualTo(root.get("fechaNacimiento"), fechaMax) : null
                    )
                    .filter(Objects::nonNull)
                    .toList();
            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
