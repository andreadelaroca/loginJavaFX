package com.example.registropaciente.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

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

        // CAMPOS VACÍOS
        if (usuario.isEmpty() || password.isEmpty()) {

            mostrarAlertaError(
                    "Campos incompletos",
                    "Ingrese usuario y contraseña."
            );

            return;
        }

        // LOGIN CORRECTO
        if (usuario.equals("admin") && password.equals("1234")) {

            intentos = 0;

            mostrarAlertaExito(
                    "Inicio de sesión",
                    "Inicio de sesión completado."
            );

            abrirRegistro();

        } else {

            // LOGIN INCORRECTO
            intentos++;

            // TERCER INTENTO
            if (intentos >= 3) {

                mostrarAlertaError(
                        "Exceso de intentos",
                        "Ha intentado iniciar sesión 3 veces. Cerrando aplicación."
                );

                Stage stage =
                        (Stage) btnLogin.getScene().getWindow();

                stage.close();

            } else {

                mostrarAlertaError(
                        "Contraseña incorrecta",
                        "Contraseña incorrecta. Intente de nuevo."
                );

                pswPass.clear();
                pswPass.requestFocus();
            }
        }
    }

    // ABRIR VENTANA DE REGISTRO
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

            mostrarAlertaError(
                    "Error",
                    "No se pudo abrir la pantalla de registro."
            );

            e.printStackTrace();
        }
    }

    // ALERTA DE ERROR CON CANDADO CERRADO
    private void mostrarAlertaError(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        Image imagenCandado = new Image(
                Objects.requireNonNull(
                        getClass().getResource(
                                "/com/example/registropaciente/candado.png"
                        )
                ).toExternalForm()
        );

        ImageView candado = new ImageView(imagenCandado);

        candado.setFitWidth(45);
        candado.setFitHeight(45);
        candado.setPreserveRatio(true);

        alerta.setGraphic(candado);

        alerta.showAndWait();
    }

    // ALERTA DE ÉXITO CON CANDADO ABIERTO
    private void mostrarAlertaExito(String titulo, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        Image imagenCandadoAbierto = new Image(
                Objects.requireNonNull(
                        getClass().getResource(
                                "/com/example/registropaciente/candado_abierto.png"
                        )
                ).toExternalForm()
        );

        ImageView candadoAbierto =
                new ImageView(imagenCandadoAbierto);

        candadoAbierto.setFitWidth(45);
        candadoAbierto.setFitHeight(45);
        candadoAbierto.setPreserveRatio(true);

        alerta.setGraphic(candadoAbierto);

        alerta.showAndWait();
    }
}
