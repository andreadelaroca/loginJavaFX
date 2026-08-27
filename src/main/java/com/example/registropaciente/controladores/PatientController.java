package com.example.registropaciente.controladores;

import com.example.registropaciente.dao.PacienteDAO;
import com.example.registropaciente.modelos.Paciente;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.Locale;

public class PatientController {
    PacienteDAO pacientes = new PacienteDAO();

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDireccion;

    @FXML
    private DatePicker dpFechaNac;

    @FXML
    private CheckBox cbTerminosServicios;

    @FXML
    private RadioButton rbSexo;

    @FXML
    private Label lblContador;

    @FXML
    protected void agregarOnClick() {
        leerDatos();
        cantidadPaciente();
        limpiarCampos();
    }

    @FXML
    private void leerDatos() {
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String direccion = txtDireccion.getText();
        LocalDate fechaNac = dpFechaNac.getValue();
        Boolean estadoCivil = cbTerminosServicios.isSelected();
        Boolean sexo = rbSexo.isSelected();

        agregarPaciente(new Paciente());
    }

    @FXML
    private void agregarPaciente(Paciente paciente) {
        pacientes.agregar(paciente);
    }

    private void cantidadPaciente() {
        lblContador.setText("Registros almacenados: " + pacientes.obtenerRegistros().size());
    }

    private void limpiarCampos() {
        txtNombres.clear();
        txtApellidos.clear();
        txtNombres.requestFocus();
    }
}
