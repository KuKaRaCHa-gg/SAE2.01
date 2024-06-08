module com.example.sae_201 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires javafx.web;

    opens com.example.sae_201 to javafx.fxml;
    opens result to com.fasterxml.jackson.databind;  // Assurez-vous que ceci est inclus
    exports com.example.sae_201;
    exports gameModel to com.fasterxml.jackson.databind;
    opens gameModel;
}