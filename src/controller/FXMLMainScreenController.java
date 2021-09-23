/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
    @FXML
    private Hyperlink hyperlinkFarmManagement;
    @FXML
    private Button buttonFarmManagement;
    @FXML
    private Button buttonTeste;

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
            tab.setClosable(true);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a tela de cadastro de usuários.\nErro: " + ex);
        }
    }

    @FXML
    private void handleHyperlinkFarmManagement(ActionEvent event) {
        openFarmManagement();
    }
        
    private void openFarmManagement() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLFarmManagementScreen.fxml"));
            Parent root;
            root = (Parent) fxmlLoader.load();
            Tab tab = new Tab();
            tab.setText("Gerenciamento de fazendas");
            tab.setContent(root);
            tab.setClosable(true);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a tela de cadastro de fazendas.\nErro: " + ex);
        }
    }

    @FXML
    private void hundleButtonTeste(ActionEvent event) {
        openTeste();
    }
    
    private void openTeste(){
        try{
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLTeste.fxml"));
          Parent root;
          root = (Parent) fxmlLoader.load();
          Tab tab = new Tab();
          tab.setText("Teste");
          tab.setContent(root);
          tab.setClosable(true);
          tabPane.getTabs().add(tab);
          tabPane.getSelectionModel().select(tab);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Erro ao carregar teste.\nErro: " + ex);
        }
    }
}