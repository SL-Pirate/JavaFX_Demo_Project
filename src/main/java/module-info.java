module lk.ac.iit.genericfxproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.apache.commons.codec;


    opens lk.ac.iit.genericfxproj to javafx.fxml;
    exports lk.ac.iit.genericfxproj;
    exports lk.ac.iit.genericfxproj.app;
    opens lk.ac.iit.genericfxproj.app to javafx.fxml;
    exports lk.ac.iit.genericfxproj.controllers;
    opens lk.ac.iit.genericfxproj.controllers to javafx.fxml;
    exports lk.ac.iit.genericfxproj.data;
    opens lk.ac.iit.genericfxproj.data to javafx.fxml;
    exports lk.ac.iit.genericfxproj.db;
    opens lk.ac.iit.genericfxproj.db to javafx.fxml;
}