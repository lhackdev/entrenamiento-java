package com.gestionspersona.entrenamiento.persistencia;


import com.gestionspersona.entrenamiento.modelo.Persona;
import com.gestionspersona.entrenamiento.puerto.IPersonaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonaRepository implements IPersonaRepository
{
    private final PersonaJpaRepository jpaRepository;

    public PersonaRepository(PersonaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity entity = PersonaEntity.fromDomain(persona);
        PersonaEntity saved = jpaRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return jpaRepository.findById(id).map(PersonaEntity::toDomain);
    }

    @Override
    public List<Persona> findAllByOrderByApellidoAsc() {
        return jpaRepository.findAllByOrderByApellidoAsc()
                .stream()
                .map(PersonaEntity::toDomain)
                .toList();
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Long id) {
        return jpaRepository.existsByEmailAndIdNot(email, id);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
