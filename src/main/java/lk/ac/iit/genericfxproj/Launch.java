package lk.ac.iit.genericfxproj;

public class Launch extends App {


    public static void main(String[] args) {
        // Initialize DB
        DB.getInstance();

        launch();

    }

}



//    public void start(Stage primaryStage) throws Exception {
//
//        primaryStage.setHeight(400);
//        primaryStage.setWidth(600);
//        Button bt1 = new Button("Click Me"); //control
////        bt1.setAlignment(Pos.CENTER);
//
//        HBox h = new HBox(); //layout
//        h.setAlignment(Pos.CENTER);
//
//        h.getChildren().add(bt1); //adding control to layout
//
//        Scene sc = new Scene(h); //creating scene and adding layout to scene
//
//        primaryStage.setScene(sc);
//
//        primaryStage.show();
//
//    }
//
//}