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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Battistuzzo
 */
public class FXMLMainScreenController implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabStart;
    @FXML
    private Hyperlink hyperlinkUserManagement;
    @FXML
    private Button buttonUserManagement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleHyperlinkUserManagement(ActionEvent event) {
        openUserManagement();
    }
    
    private void openUserManagement() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLUserManagementScreen.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Tab tab = new Tab();
            tab.setText("Gerenciamento de usuários");
            tab.setContent(root);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a tela de cadastro de usuários.\nErro: " + e);
        }
    }
}