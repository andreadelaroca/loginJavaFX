package com.example.registropaciente.interfaces;

import java.util.List;

public interface CRUD <T> {
    public void agregar(T entidad);
    public List<T> obtenerRegistros();
}
