/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Battistuzzo
 */
public class JavaFXBreeding extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private static Stage firstStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        firstStage = stage;
        firstStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLBreeding.fxml"));
        firstStage.setTitle("Tela de login");
        Scene scene = new Scene(root);
        firstStage.setScene(scene);
        firstStage.show();
    }
    
    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        String a = "/view/FXMLProfileScreen.fxml";
        String b = "/application/FXMLBreeding.fxml";
        if(fxml.equals(a)){
            firstStage.setTitle("Tela de cadastro de usuários");
            firstStage.getScene().setRoot(pane);
        }else if(fxml.equals(b)){
            firstStage.setTitle("Tela de login");
            firstStage.getScene().setRoot(pane);
        }
    }
}