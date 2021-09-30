/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import model.ConnectionWithBreeding;
import model.Farm;

/**
 * FXML Controller class
 *
 * @author Battistuzzo
 */
public class FXMLFarmManagementScreenController implements Initializable {
    
    int flag = 0;
    Farm farm = new Farm();
    ConnectionWithBreeding connection = new ConnectionWithBreeding();

    @FXML
    private Label labelMessage;
    @FXML
    private Label labelId;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldStateRegistry;
    @FXML
    private Button buttonNew;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonGeneratePdf;
    @FXML
    private TableView<Farm> tableViewFarms;
    @FXML
    private TableColumn<Farm, Integer> tableColumnId;
    @FXML
    private TableColumn<Farm, String> tableColumnName;
    @FXML
    private TableColumn<Farm, String> tableColumnStateRegistry;
    @FXML
    private TableColumn<Farm, String> tableColumnOwner;
    @FXML
    private ChoiceBox<String> choiceBoxType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
        pickType();
    }
    
    @FXML
    private void handleButtonNew(ActionEvent event) {
        flag = 1;
        labelId.setText("");
        textFieldName.setEditable(true);
        textFieldName.setText("");
        textFieldStateRegistry.setEditable(true);
        textFieldStateRegistry.setText("");
        buttonSave.setDisable(false);
        buttonCancel.setDisable(false);
    }
    
    @FXML
    private void handleButtonSave(ActionEvent event) {
        if (flag == 1) {
            farm.setName(textFieldName.getText());
            farm.setStateRegistry(textFieldStateRegistry.getText());
            farm.setPersonId(1);//corrigir aqui..........................
            farm.saveFarm(farm);
            labelMessage.setText("Propriedade salva com sucesso!");
            textFieldName.setText("");
            textFieldStateRegistry.setText("");
            textFieldName.setEditable(false);
            textFieldStateRegistry.setEditable(false);
            buttonSave.setDisable(true);
            buttonNew.setDisable(false);
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
            buttonCancel.setDisable(true);
            labelId.setText("");
            textFieldSearch.setText("");
        } else {
            farm.setId(Integer.parseInt(labelId.getText()));
            farm.setName(textFieldName.getText());
            farm.setStateRegistry(textFieldStateRegistry.getText());
            farm.editFarm(farm);
            labelMessage.setText("Propriedade editada com sucesso!");
            textFieldName.setText("");
            textFieldStateRegistry.setText("");
            textFieldName.setEditable(false);
            textFieldStateRegistry.setEditable(false);
            buttonSave.setDisable(true);
            buttonNew.setDisable(false);
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
            buttonCancel.setDisable(true);
            labelId.setText("");
            textFieldSearch.setText("");
        }
        fillTable();
    }
    
    public void fillTable() {
        ObservableList<Farm> farms = FXCollections.observableArrayList();
        connection.connect();
        connection.executeQuery("SELECT id, name, stateregistry, fk_person_id FROM farm");
        try {
            connection.resultSet.first();
            do {
                farms.add(new Farm(connection.resultSet.getInt("id"), connection.resultSet.getString("name"), connection.resultSet.getString("stateregistry"), connection.resultSet.getInt("fk_person_id")));
            } while (connection.resultSet.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList de propriedades" + ex);
        }
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnStateRegistry.setCellValueFactory(new PropertyValueFactory<>("stateRegistry"));
        tableColumnOwner.setCellValueFactory(new PropertyValueFactory<>("personId"));
        tableViewFarms.setItems(farms);
        connection.disconnect();
    }
    
    @FXML
    public void pickType(){
        ObservableList<String> types = FXCollections.observableArrayList("Aeroporto", "Alameda", "Área", "Avenida", "Chácara", "Colônia", "Condomínio", "Conjunto", "Distrito", "Esplanada", "Estação", "Estrada", "Favela", "Fazenda", "Feira", "Jardim", "Ladeira", "Lago", "Lagoa", "Largo", "Loteamento", "Morro", "Núcleo", "Parque", "Passarela", "Pátio", "Praça", "Quadra", "Recanto", "Residencial", "Rodovia", "Rua", "Setor", "Sítio", "Travessa", "Trecho", "Trevo", "Via", "Viaduto", "Viela", "Vila");
        choiceBoxType.setItems(types);
        choiceBoxType.setTooltip(new Tooltip("Clique para escolher um tipo"));
        //ObservableList<String> availableChoices = choiceBox.getItems();to get items
        //String selectedChoice = choiceBox.getSelectionModel().getSelectedItem();to get selected item
    }
}
