package com.example.registropaciente.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Paciente {
    private String nombres;
    private String apellidos;
    private String email;
    private String direccion;
    private LocalDate fechaNac;
    private Boolean terminosServicios;
    private Boolean sexo;
}
