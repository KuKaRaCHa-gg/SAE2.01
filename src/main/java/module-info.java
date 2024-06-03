module com.example.sae_201 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;


    opens com.example.sae_201 to javafx.fxml;
    exports com.example.sae_201;
}