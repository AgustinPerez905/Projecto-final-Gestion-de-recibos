module com.gestion_de_recibos.projectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires jbcrypt;

    opens com.gestion_de_recibos.projectofinal to javafx.fxml;
    exports com.gestion_de_recibos.projectofinal;
}