module lk.ac.iit.genericfxproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens lk.ac.iit.genericfxproj to javafx.fxml;
    exports lk.ac.iit.genericfxproj;
}