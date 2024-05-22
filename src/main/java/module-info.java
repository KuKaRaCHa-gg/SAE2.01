module com.example.sae_201 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sae_201 to javafx.fxml;
    exports com.example.sae_201;
}