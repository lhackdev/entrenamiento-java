package com.gestionspersona.entrenamiento.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Long>, JpaSpecificationExecutor<PersonaEntity> {

    List<PersonaEntity> findAllByOrderByApellidoAsc();

    boolean existsByEmailAndIdNot(String email, Long id);
}
