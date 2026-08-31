package com.example.registropaciente.controladores;

import com.example.registropaciente.PatientApplication;
import com.example.registropaciente.modelos.Paciente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends PatientApplication {

    private int intentos = 0;
    
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pswPass;

    @FXML
    private Button btnLogin;

    @FXML
    protected void loginOnClick() {
        String usuario = txtUsuario.getText().trim();
        String password = pswPass.getText().trim();
        while (intentos < 3) {
            if (usuario.isEmpty() || password.isEmpty()) {
                mostrarAlerta(
                        "Campos incompletos",
                        "Ingrese usuario y contraseña."
                );
                intentos++;
                return;
            }

            if (usuario.equals("admin") && password.equals("1234")) {
                abrirRegistro();

            } else {

                mostrarAlerta(
                        "Error de inicio de sesión",
                        "Usuario o contraseña incorrectos."
                );
                intentos++;
            }
        }
        if (intentos == 3) {
            mostrarAlerta("Exceso de intentos", "Ha intentado iniciar sesión 3 veces. Cerrando aplicación.");
            Stage stage = (Stage) btnLogin.getScene().getWindow(); // se invoca el cierre de sesión a través del botón login
            stage.close();
        }
    }

    private void abrirRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/registropaciente/patient-view.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Registro de Pacientes");
            stage.centerOnScreen();

        } catch (IOException e) {
            mostrarAlerta(
                    "Error",
                    "No se pudo abrir la pantalla de registro."
            );
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
