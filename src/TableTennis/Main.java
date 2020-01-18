package TableTennis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//heliefektid



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        new Controller();
        new PasswordController();
        new Player("",0,0,0,0,0,0,0,0,0);
        Parent root = FXMLLoader.load(getClass().getResource("table_tennis.fxml"));
        primaryStage.setTitle("TABLE TENNIS");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
