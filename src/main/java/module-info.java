module org.example.empresafct {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.empresafct to javafx.fxml;
    exports org.example.empresafct;
}