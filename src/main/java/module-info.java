module com.example.registropaciente {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens com.example.registropaciente to javafx.fxml;
    exports com.example.registropaciente;
    exports com.example.registropaciente.modelos;
    opens com.example.registropaciente.modelos to javafx.fxml;
    exports com.example.registropaciente.controladores;
    opens com.example.registropaciente.controladores to javafx.fxml;
}