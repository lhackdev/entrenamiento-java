package com.gestionspersona.entrenamiento.dtos;

import com.gestionspersona.entrenamiento.modelo.Persona;

import java.time.LocalDate;

public class PersonaDTO {
    public PersonaDTO() {}

    public Long id;

    public String nombre;

    public String apellido;

    public String email;

    public LocalDate fechaNacimiento;

    public PersonaDTO(Long id, String nombre, String apellido, String email, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public PersonaDTO(String nombre, String apellido, String email, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona aModelo() {
        return new Persona(
                this.nombre,
                this.apellido,
                this.email,
                this.fechaNacimiento
        );
    }

    public static PersonaDTO desdeModelo(Persona persona) {
        return new PersonaDTO(
                persona.id,
                persona.nombre,
                persona.apellido,
                persona.email,
                persona.fechaNacimiento
        );
    }
}
