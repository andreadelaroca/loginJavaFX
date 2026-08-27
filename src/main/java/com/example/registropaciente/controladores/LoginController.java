package com.example.registropaciente.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pswPass;

    @FXML
    protected void loginOnClick() {

        String usuario = txtUsuario.getText().trim();
        String password = pswPass.getText();

        if (usuario.isEmpty() || password.isEmpty()) {

            mostrarAlerta(
                    "Campos incompletos",
                    "Ingrese usuario y contraseña."
            );

            return;
        }

        if (usuario.equals("admin") && password.equals("1234")) {

            abrirRegistro();

        } else {

            mostrarAlerta(
                    "Error de inicio de sesión",
                    "Usuario o contraseña incorrectos."
            );
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

            Stage stage =
                    (Stage) txtUsuario.getScene().getWindow();

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
}
