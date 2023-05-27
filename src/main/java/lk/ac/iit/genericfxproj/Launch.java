package lk.ac.iit.genericfxproj;

import lk.ac.iit.genericfxproj.app.App;
import lk.ac.iit.genericfxproj.db.DB;

public class Launch extends App {


    public static void main(String[] args) {
        // Initialize DB
        DB.getInstance();

        launch();

    }

}
