package com.gestionspersona.entrenamiento.comandos;

import com.gestionspersona.entrenamiento.dtos.PersonaDTO;
import com.gestionspersona.entrenamiento.modelo.Persona;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ComandoActualizarPersona {
    public ComandoActualizarPersona() {}

    public Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    public String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    public String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 150, message = "El email no puede exceder 150 caracteres")
    public String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento no puede ser futura")
    public LocalDate fechaNacimiento;

    public ComandoActualizarPersona(Long id, String nombre, String apellido, String email, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public ComandoActualizarPersona(String nombre, String apellido, String email, LocalDate fechaNacimiento) {
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
