package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 1250, 700));
        primaryStage.show();
    }

    public static Connector connector;

    public static void main(String[] args) {
        connector = new Connector();
        //connector.insertUser("tomek", "testowy", "ttest", MD5.createMD5("123"));
        /*
        for (Medication c : medication)
            System.out.println(c.getName());*/
        launch(args);
        connector.closeConnection();
    }
}
