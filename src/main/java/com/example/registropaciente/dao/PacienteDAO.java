package com.example.registropaciente.dao;

import com.example.registropaciente.interfaces.CRUD;
import com.example.registropaciente.modelos.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements CRUD<Paciente> {
    List<Paciente> pacientes;

    public PacienteDAO() {
        pacientes = new ArrayList<>();
    }

    @Override
    public void agregar(Paciente entidad) {
        pacientes.add(entidad);
    }

    @Override
    public List<Paciente> obtenerRegistros() {
        return pacientes;
    }
}

