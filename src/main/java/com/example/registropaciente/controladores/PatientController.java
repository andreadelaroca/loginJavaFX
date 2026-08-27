package com.example.registropaciente.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class PatientController {

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
    private RadioButton rbMasculino;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private ToggleGroup grupoSexo;

    @FXML
    private Label lblContador;

    // TABLA

    @FXML
    private TableView<PacienteTabla> tablaPacientes;

    @FXML
    private TableColumn<PacienteTabla, String> colNombres;

    @FXML
    private TableColumn<PacienteTabla, String> colApellidos;

    @FXML
    private TableColumn<PacienteTabla, String> colEmail;

    @FXML
    private TableColumn<PacienteTabla, String> colSexo;

    @FXML
    private TableColumn<PacienteTabla, LocalDate> colFecha;

    private final ObservableList<PacienteTabla> listaPacientes =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colNombres.setCellValueFactory(
                new PropertyValueFactory<>("nombres")
        );

        colApellidos.setCellValueFactory(
                new PropertyValueFactory<>("apellidos")
        );

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        colSexo.setCellValueFactory(
                new PropertyValueFactory<>("sexo")
        );

        colFecha.setCellValueFactory(
                new PropertyValueFactory<>("fechaNacimiento")
        );

        tablaPacientes.setItems(listaPacientes);
    }

    @FXML
    protected void agregarOnClick() {

        if (!validarCampos()) {
            return;
        }

        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String email = txtEmail.getText().trim();
        String direccion = txtDireccion.getText().trim();

        LocalDate fechaNacimiento =
                dpFechaNac.getValue();

        String sexo;

        if (rbMasculino.isSelected()) {
            sexo = "Masculino";
        } else {
            sexo = "Femenino";
        }

        PacienteTabla paciente =
                new PacienteTabla(
                        nombres,
                        apellidos,
                        email,
                        direccion,
                        sexo,
                        fechaNacimiento
                );

        // AQUÍ SE AGREGA A LA TABLA
        listaPacientes.add(paciente);

        lblContador.setText(
                "Registros almacenados: "
                        + listaPacientes.size()
        );

        limpiarCampos();
    }

    private boolean validarCampos() {

        if (txtNombres.getText().isBlank()
                || txtApellidos.getText().isBlank()
                || txtEmail.getText().isBlank()
                || txtDireccion.getText().isBlank()
                || dpFechaNac.getValue() == null
                || grupoSexo.getSelectedToggle() == null) {

            mostrarAlerta(
                    "Campos incompletos",
                    "Complete todos los campos."
            );

            return false;
        }

        if (!cbTerminosServicios.isSelected()) {

            mostrarAlerta(
                    "Términos",
                    "Debe aceptar los términos y servicios."
            );

            return false;
        }

        return true;
    }

    private void limpiarCampos() {

        txtNombres.clear();
        txtApellidos.clear();
        txtEmail.clear();
        txtDireccion.clear();

        dpFechaNac.setValue(null);

        grupoSexo.selectToggle(null);

        cbTerminosServicios.setSelected(false);

        txtNombres.requestFocus();
    }

    private void mostrarAlerta(
            String titulo,
            String mensaje
    ) {

        Alert alerta =
                new Alert(Alert.AlertType.WARNING);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }


    // CLASE PARA LAS FILAS DE LA TABLA

    public static class PacienteTabla {

        private final String nombres;
        private final String apellidos;
        private final String email;
        private final String direccion;
        private final String sexo;
        private final LocalDate fechaNacimiento;

        public PacienteTabla(
                String nombres,
                String apellidos,
                String email,
                String direccion,
                String sexo,
                LocalDate fechaNacimiento
        ) {

            this.nombres = nombres;
            this.apellidos = apellidos;
            this.email = email;
            this.direccion = direccion;
            this.sexo = sexo;
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getNombres() {
            return nombres;
        }

        public String getApellidos() {
            return apellidos;
        }

        public String getEmail() {
            return email;
        }

        public String getDireccion() {
            return direccion;
        }

        public String getSexo() {
            return sexo;
        }

        public LocalDate getFechaNacimiento() {
            return fechaNacimiento;
        }
    }
}
