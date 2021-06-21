/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import model.ConnectionWithBreeding;
import model.User;

/**
 * FXML Controller class
 *
 * @author Battistuzzo
 */
public class FXMLUserManagementScreenController implements Initializable {

    int flag = 0;
    User user = new User();
    ConnectionWithBreeding connection = new ConnectionWithBreeding();

    @FXML
    private TextField textFieldCpf;
    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    private Label labelMessage;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonNew;
    @FXML
    private TextField textFieldName;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<User> tableViewUsers;
    @FXML
    private Button buttonCancel;
    @FXML
    private Label labelId;
    @FXML
    private TableColumn<User, Integer> tableColumnId;
    @FXML
    private TableColumn<User, String> tableColumnName;
    @FXML
    private TableColumn<User, String> tableColumnCpf;
    @FXML
    private Button buttonGeneratePdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
    }

    @FXML
    private void handleButtonSave(ActionEvent event) {
        if (flag == 1) {
            user.setName(textFieldName.getText());
            user.setFone(null);
            user.setFax(null);
            user.setEmail(null);
            user.setPassword(passwordFieldPassword.getText());
            user.setLogged(Boolean.FALSE);
            user.setCpf(textFieldCpf.getText());
            user.saveUser(user);
            labelMessage.setText("Usuário salvo com sucesso!");
            textFieldName.setText("");
            textFieldCpf.setText("");
            passwordFieldPassword.setText("");
            textFieldName.setEditable(false);
            textFieldCpf.setEditable(false);
            passwordFieldPassword.setEditable(false);
            buttonSave.setDisable(true);
            buttonNew.setDisable(false);
            buttonCancel.setDisable(true);
            labelId.setText("");
            textFieldSearch.setText("");
        } else {
            user.setId(Integer.parseInt(labelId.getText()));
            user.setName(textFieldName.getText());
            user.setFone(null);
            user.setFax(null);
            user.setEmail(null);
            user.setPassword(passwordFieldPassword.getText());
            user.setLogged(Boolean.FALSE);
            user.setCpf(textFieldCpf.getText());
            user.editUser(user);
            labelMessage.setText("Usuário editado com sucesso!");
            textFieldName.setText("");
            textFieldCpf.setText("");
            passwordFieldPassword.setText("");
            textFieldName.setEditable(false);
            textFieldCpf.setEditable(false);
            passwordFieldPassword.setEditable(false);
            buttonSave.setDisable(true);
            buttonNew.setDisable(false);
            buttonCancel.setDisable(true);
            labelId.setText("");
            textFieldSearch.setText("");
        }
        fillTable();
    }

    @FXML
    private void handleButtonNew(ActionEvent event) {
        flag = 1;
        labelId.setText("");
        textFieldName.setEditable(true);
        textFieldName.setText("");
        textFieldCpf.setEditable(true);
        textFieldCpf.setText("");
        passwordFieldPassword.setEditable(true);
        passwordFieldPassword.setText("");
        buttonSave.setDisable(false);
        buttonCancel.setDisable(false);
    }

    @FXML
    private void handleButtonEdit(ActionEvent event) {
        flag = 2;
        textFieldName.setEditable(true);
        textFieldCpf.setEditable(true);
        passwordFieldPassword.setEditable(true);
        buttonSave.setDisable(false);
        buttonCancel.setDisable(false);
        buttonEdit.setDisable(true);
        buttonNew.setDisable(true);
        buttonDelete.setDisable(true);
    }

    @FXML
    private void handleButtonDelete(ActionEvent event) {
        int answer;
        answer = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este usuário?");
        if (answer == JOptionPane.YES_OPTION) {
            user.setId(Integer.parseInt(labelId.getText()));
            user.deleteUser(user);
            labelMessage.setText("Usuário excluído com sucesso!");
            textFieldName.setText("");
            textFieldCpf.setText("");
            passwordFieldPassword.setText("");
            textFieldName.setEditable(false);
            textFieldCpf.setEditable(false);
            passwordFieldPassword.setEditable(false);
            buttonSave.setDisable(true);
            buttonNew.setDisable(false);
            buttonCancel.setDisable(true);
            labelId.setText("");
            textFieldSearch.setText("");
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
            fillTable();
        }
    }

    @FXML
    private void handleButtonSearch(ActionEvent event) {
        user.setName(textFieldSearch.getText());
        user.searchUser(user);
        labelId.setText(String.valueOf(user.getId()));
        textFieldName.setText(user.getName());
        textFieldCpf.setText(user.getCpf());
        passwordFieldPassword.setText(user.getPassword());
        buttonEdit.setDisable(false);
        buttonDelete.setDisable(false);
    }

    @FXML
    private void handleButtonCancel(ActionEvent event) {
        textFieldName.setEditable(false);
        textFieldCpf.setEditable(false);
        passwordFieldPassword.setEditable(false);
        buttonSave.setDisable(true);
        buttonCancel.setDisable(true);
        buttonNew.setDisable(false);
        buttonEdit.setDisable(true);
        buttonDelete.setDisable(true);
    }

    public void fillTable() {
        ObservableList<User> users = FXCollections.observableArrayList();
        connection.connect();
        connection.executeQuery("SELECT id, name, cpf, password FROM person");
        try {
            connection.resultSet.first();
            do {
                users.add(new User(connection.resultSet.getInt("id"), connection.resultSet.getString("name"), null, null, null, connection.resultSet.getString("password"), false, connection.resultSet.getString("cpf")));
            } while (connection.resultSet.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList de usuários" + ex);
        }
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableViewUsers.setItems(users);
        connection.disconnect();
    }
    
    @FXML
    public void userClickOnTable(){
        User user = tableViewUsers.getSelectionModel().getSelectedItem();
        labelId.setText(String.valueOf(user.getId()));
        textFieldName.setText(user.getName());
        textFieldCpf.setText(user.getCpf());
        passwordFieldPassword.setText(user.getPassword());
        buttonNew.setDisable(false);
        buttonEdit.setDisable(false);
        buttonDelete.setDisable(false);
    }

    @FXML
    private void handleButtonGeneratePdf(ActionEvent event) {
        try {
            user.generatePdf();
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever em arquivo:\n" + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo:\n" + ex.getMessage());
        }
    }
}