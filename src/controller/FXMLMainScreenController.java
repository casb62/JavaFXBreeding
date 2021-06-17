/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Battistuzzo
 */
public class FXMLMainScreenController implements Initializable {

    @FXML
    private Hyperlink hyperlinkUserRegistration;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabStart;
    @FXML
    private Button buttonUserRegistration;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleHyperlinkUserRegistration(ActionEvent event) {
        openUserRegistration();
    }
    
    private void openUserRegistration(){
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLProfileScreen.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                //Stage stage = new Stage();
                //stage.initStyle(StageStyle.DECORATED);
                //stage.setTitle("Tela de cadastro de usuários");
                //stage.setScene(new Scene(root));
                //stage.show();
            
                Tab tab = new Tab();
                tab.setText("Gerenciamento de usuários");
                //tab.setContent(new Rectangle(500,50, Color.LIGHTSTEELBLUE));
                tab.setContent(root);
                //tab
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
                
                
            } catch (Exception e) {
                System.out.println("Não foi possível carregar a tela de cadastro de usuários. " + e);
            }
    }
}