package com.example.registropaciente.controladores;

import com.example.registropaciente.dao.PacienteDAO;
import com.example.registropaciente.modelos.Paciente;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class PatientController {

    private final PacienteDAO pacientes = new PacienteDAO();

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

        if (!validarCampos()) {
            return;
        }

        leerDatos();
        cantidadPaciente();
        limpiarCampos();
    }

    private void leerDatos() {

        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String email = txtEmail.getText().trim();
        String direccion = txtDireccion.getText().trim();
        LocalDate fechaNac = dpFechaNac.getValue();

        boolean terminosServicios = cbTerminosServicios.isSelected();
        boolean sexo = rbSexo.isSelected();

        Paciente paciente = new Paciente();

        agregarPaciente(paciente);
    }

    private void agregarPaciente(Paciente paciente) {
        pacientes.agregar(paciente);
    }

    private void cantidadPaciente() {
        lblContador.setText(
                "Registros almacenados: " +
                        pacientes.obtenerRegistros().size()
        );
    }

    private void limpiarCampos() {

        txtNombres.clear();
        txtApellidos.clear();
        txtEmail.clear();
        txtDireccion.clear();

        dpFechaNac.setValue(null);

        cbTerminosServicios.setSelected(false);
        rbSexo.setSelected(false);

        txtNombres.requestFocus();
    }

    private boolean validarCampos() {

        if (txtNombres.getText().trim().isEmpty()
                || txtApellidos.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()
                || txtDireccion.getText().trim().isEmpty()
                || dpFechaNac.getValue() == null) {

            mostrarAlerta(
                    "Campos incompletos",
                    "Debe completar todos los campos."
            );

            return false;
        }

        if (!cbTerminosServicios.isSelected()) {

            mostrarAlerta(
                    "Términos y servicios",
                    "Debe aceptar los términos y servicios."
            );

            return false;
        }

        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
