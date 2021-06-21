/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.ConnectionWithBreeding;
import model.User;

/**
 *
 * @author Battistuzzo
 */
public class FXMLBreedingController implements Initializable {
    
    ConnectionWithBreeding cwb = new ConnectionWithBreeding();
    
    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField passwordFieldLogin;
    @FXML
    private Label labelMessageLogin;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonRegisterLogin;
    
    @FXML
    private void handleButtonLogin(ActionEvent event) throws IOException, Exception {
        checkLogin();
    }
    
    private void checkLogin() {
        cwb.connect();
        List<User> users = null;
        try {
            users = cwb.selectAllUsers();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao percorrer resultSet:\n" + ex.getMessage());
        }
        for (User user : users) {
            if ((textFieldLogin.getText().equals(user.getCpf()) && (passwordFieldLogin.getText().equals(user.getPassword())))) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLMainScreen.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Tela principal");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    System.out.println("Não foi possível carregar a tela principal. " + e);
                }
            } else if (textFieldLogin.getText().isEmpty() && passwordFieldLogin.getText().isEmpty()) {
                labelMessageLogin.setText("Por favor, digite seu CPF e sua senha.");
            } else {
                labelMessageLogin.setText("CPF ou senha incorretos.");
            }
        }
    }
    
    @FXML
    private void handleButtonRegister(ActionEvent event) throws IOException {
        JavaFXBreeding breeding = new JavaFXBreeding();
        breeding.changeScene("/view/FXMLProfileScreen.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}