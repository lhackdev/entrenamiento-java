package com.gestionspersona.entrenamiento.servicio;

import com.gestionspersona.entrenamiento.modelo.Persona;
import org.springframework.stereotype.Service;

@Service
public class SanitizacionService {

    public Persona sanitizar(Persona persona) {
        persona.nombre = sanitizarTexto(persona.nombre);
        persona.apellido = sanitizarTexto(persona.apellido);
        persona.email = sanitizarEmail(persona.email);
        return persona;
    }

    public String sanitizarTexto(String valor) {
        if (valor == null) {
            return null;
        }
        String limpio = valor.replaceAll("<[^>]*>", "");
        limpio = limpio.replaceAll("[\\p{Cntrl}&&[^\t\n\r]]", "");
        limpio = limpio.replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
        return limpio.trim();
    }

    public String sanitizarEmail(String email) {
        if (email == null) {
            return null;
        }
        String limpio = email.replaceAll("<[^>]*>", "");
        limpio = limpio.replaceAll("[\\p{Cntrl}&&[^\t\n\r]]", "");
        return limpio.trim().toLowerCase();
    }
}