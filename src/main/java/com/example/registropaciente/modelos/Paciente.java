package com.example.registropaciente.modelos;

import lombok.*;

import java.time.LocalDate;

@Data
public class Paciente {
    private String nombres;
    private String apellidos;
    private String email;
    private String direccion;
    private LocalDate fechaNac;
    private Boolean terminosServicios;
    private Boolean sexo;
}
