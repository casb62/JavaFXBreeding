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
import model.Location;
import model.Person;

public class FXMLFarmManagementScreenController implements Initializable {
    
    int flag = 0;
    Farm farm = new Farm();
    ConnectionWithBreeding connection = new ConnectionWithBreeding();
    ObservableList<String> locationTypes = FXCollections.observableArrayList("Aeroporto", 
    "Alameda", "Área", "Avenida", "Chácara", "Colônia", "Condomínio", "Conjunto", 
    "Distrito", "Esplanada", "Estação", "Estrada", "Favela", "Fazenda", "Feira", 
    "Jardim", "Ladeira", "Lago", "Lagoa", "Largo", "Loteamento", "Morro", "Núcleo", 
    "Parque", "Passarela", "Pátio", "Praça", "Quadra", "Recanto", "Residencial", 
    "Rodovia", "Rua", "Setor", "Sítio", "Travessa", "Trecho", "Trevo", "Via", 
    "Viaduto", "Viela", "Vila");
    ObservableList<String> personTypes = FXCollections.observableArrayList("CPF", "CNPJ");

    @FXML
    private Label labelMessage;
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
    private TableColumn<Farm, Integer> tableColumnOwner;
    @FXML
    private Label labelFarmId;
    @FXML
    private TextField textFieldFarmName;
    @FXML
    private TextField textFieldFarmStateRegistry;
    @FXML
    private Label labelOwnerId;
    @FXML
    private TextField textFieldOwnerName;
    @FXML
    private TextField textFieldOwnerFone;
    @FXML
    private TextField textFieldOwnerFax;
    @FXML
    private TextField textFieldOwnerEmail;
    @FXML
    private ChoiceBox<String> choiceBoxCpfCnpj;
    @FXML
    private TextField textFieldOwnerCpf;
    @FXML
    private Label labelLocationId1;
    @FXML
    private ChoiceBox<String> choiceBoxLocationType1;
    @FXML
    private TextField textFieldLocationPublicPlace1;
    @FXML
    private TextField textFieldLocationNumber1;
    @FXML
    private TextField textFieldLocationComplement1;
    @FXML
    private TextField textFieldLocationMailbox1;
    @FXML
    private TextField textFieldLocationNeighborhood1;
    @FXML
    private TextField textFieldLocationCity1;
    @FXML
    private TextField textFieldLocationState1;
    @FXML
    private TextField textFieldLocationCountry1;
    @FXML
    private TextField textFieldLocationZipcode1;
    @FXML
    private TextField textFieldLocationLatitude1;
    @FXML
    private TextField textFieldLocationLongitude1;
    @FXML
    private TextField textFieldLocationAltitude1;
    @FXML
    private Label labelLocationId2;
    @FXML
    private ChoiceBox<String> choiceBoxLocationType2;
    @FXML
    private TextField textFieldLocationPublicPlace2;
    @FXML
    private TextField textFieldLocationNumber2;
    @FXML
    private TextField textFieldLocationComplement2;
    @FXML
    private TextField textFieldLocationMailbox2;
    @FXML
    private TextField textFieldLocationNeighborhood2;
    @FXML
    private TextField textFieldLocationCity2;
    @FXML
    private TextField textFieldLocationState2;
    @FXML
    private TextField textFieldLocationCountry2;
    @FXML
    private TextField textFieldLocationZipcode2;
    @FXML
    private TextField textFieldLocationLatitude2;
    @FXML
    private TextField textFieldLocationLongitude2;
    @FXML
    private TextField textFieldLocationAltitude2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBoxLocationType1.setTooltip(new Tooltip("Clique para escolher um tipo"));
        choiceBoxLocationType1.setValue("Estrada");
        choiceBoxLocationType1.setItems(locationTypes);
        choiceBoxCpfCnpj.setTooltip(new Tooltip("Clique para escolher cpf ou cnpj"));
        choiceBoxCpfCnpj.setValue("CPF");
        choiceBoxCpfCnpj.setItems(personTypes);
        choiceBoxLocationType2.setTooltip(new Tooltip("Clique para escolher um tipo"));
        choiceBoxLocationType2.setValue("Rua");
        choiceBoxLocationType2.setItems(locationTypes);
        fillTable();
        buttonEdit.setDisable(true);
        buttonDelete.setDisable(true);
        buttonSave.setDisable(true);
        buttonCancel.setDisable(true);
    }
    
    @FXML
    private void handleButtonNew(ActionEvent event) {
        flag = 1;
        labelFarmId.setText("");
        textFieldFarmName.setEditable(true);
        textFieldFarmName.setText("");
        textFieldFarmStateRegistry.setEditable(true);
        textFieldFarmStateRegistry.setText("");
        buttonSave.setDisable(false);
        buttonCancel.setDisable(false);
    }
    
    @FXML
    private void handleButtonSave(ActionEvent event) {
        if (flag == 1) {
            farm.setName(textFieldFarmName.getText());
            farm.setStateRegistry(textFieldFarmStateRegistry.getText());
            farm.setPersonId(1);//corrigir aqui..........................
            farm.saveFarm(farm);
            labelMessage.setText("Propriedade salva com sucesso!");
            textFieldFarmName.setText("");
            textFieldFarmStateRegistry.setText("");
            textFieldFarmName.setEditable(false);
            textFieldFarmStateRegistry.setEditable(false);
            buttonSave.setDisable(true);
            buttonNew.setDisable(false);
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
            buttonCancel.setDisable(true);
            labelFarmId.setText("");
            textFieldSearch.setText("");
        } else {
            farm.setId(Integer.parseInt(labelFarmId.getText()));
            farm.setName(textFieldFarmName.getText());
            farm.setStateRegistry(textFieldFarmStateRegistry.getText());
            farm.editFarm(farm);
            labelMessage.setText("Propriedade editada com sucesso!");
            textFieldFarmName.setText("");
            textFieldFarmStateRegistry.setText("");
            textFieldFarmName.setEditable(false);
            textFieldFarmStateRegistry.setEditable(false);
            buttonSave.setDisable(true);
            buttonNew.setDisable(false);
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
            buttonCancel.setDisable(true);
            labelFarmId.setText("");
            textFieldSearch.setText("");
        }
        fillTable();
    }
    
    @FXML
    private void handleButtonSearch(ActionEvent event){
        farm.setName(textFieldSearch.getText());
        farm.searchFarm(farm);
        labelFarmId.setText(String.valueOf(farm.getId()));
        textFieldFarmName.setText(farm.getName());
        textFieldFarmStateRegistry.setText(farm.getStateRegistry());
        int farmId = farm.getId();
        int personId = farm.getPersonId();
        Location location1 = new Location();
        location1 = location1.searchLocationOfFarm(farmId);
        labelLocationId1.setText(String.valueOf(location1.getId()));
        choiceBoxLocationType1.setValue(location1.getType());
        textFieldLocationPublicPlace1.setText(location1.getPublicPlace());
        textFieldLocationNumber1.setText(location1.getNumber());
        textFieldLocationComplement1.setText(location1.getComplement());
        textFieldLocationMailbox1.setText(location1.getMailbox());
        textFieldLocationNeighborhood1.setText(location1.getNeighborhood());
        textFieldLocationCity1.setText(location1.getCity());
        textFieldLocationState1.setText(location1.getState());
        textFieldLocationCountry1.setText(location1.getCountry());
        textFieldLocationZipcode1.setText(location1.getZipcode());
        textFieldLocationLatitude1.setText(location1.getLatitude());
        textFieldLocationLongitude1.setText(location1.getLongitude());
        textFieldLocationAltitude1.setText(location1.getAltitude());
        Person person = new Person();
        person = person.searchPerson(personId);
        labelOwnerId.setText(String.valueOf(person.getId()));
        textFieldOwnerName.setText(person.getName());
        textFieldOwnerFone.setText(person.getFone());
        textFieldOwnerFax.setText(person.getFax());
        textFieldOwnerEmail.setText(person.getEmail());
        choiceBoxCpfCnpj.setValue(person.getCpf());
        textFieldOwnerCpf.setText(person.getCpf());
        Location location2 = new Location();
        location2 = location2.searchLocationOfPerson(personId);
        labelLocationId2.setText(String.valueOf(location2.getId()));
        choiceBoxLocationType2.setValue(location2.getType());
        textFieldLocationPublicPlace2.setText(location2.getPublicPlace());
        textFieldLocationNumber2.setText(location2.getNumber());
        textFieldLocationComplement2.setText(location2.getComplement());
        textFieldLocationMailbox2.setText(location2.getMailbox());
        textFieldLocationNeighborhood2.setText(location2.getNeighborhood());
        textFieldLocationCity2.setText(location2.getCity());
        textFieldLocationState2.setText(location2.getState());
        textFieldLocationCountry2.setText(location2.getCountry());
        textFieldLocationZipcode2.setText(location2.getZipcode());
        textFieldLocationLatitude2.setText(location2.getLatitude());
        textFieldLocationLongitude2.setText(location2.getLongitude());
        textFieldLocationAltitude2.setText(location2.getAltitude());
    }
    
    @FXML
    private void handleButtonCancel(ActionEvent event) {
        labelFarmId.setText("");
        textFieldFarmName.setEditable(false);
        textFieldFarmStateRegistry.setEditable(false);
        buttonSave.setDisable(true);
        buttonCancel.setDisable(true);
        buttonNew.setDisable(false);
    }
    
    public void fillTable() {
        ObservableList<Farm> farms = FXCollections.observableArrayList();
        //ObservableList<Person> persons = FXCollections.observableArrayList();
        connection.connect();
        connection.executeQuery("SELECT farm.id, farm.name, farm.stateregistry, farm.fk_person_id, person.id, person.name, person.fone, person.fax, person.email, person.password, person.uservalidated, person.cpf, person.cnpj FROM farm INNER JOIN person ON person.id = fk_person_id;");
        //connection.executeQuery("SELECT * FROM farm");
        try {
            connection.resultSet.first();
            do {
                farms.add(new Farm(connection.resultSet.getInt("id"), connection.resultSet.getString("name"), connection.resultSet.getString("stateregistry"), connection.resultSet.getInt("fk_person_id")));
                //persons.add(new Person(connection.resultSet.getInt("id"),connection.resultSet.getString("name"), connection.resultSet.getString("fone"), connection.resultSet.getString("fax"), connection.resultSet.getString("email"), connection.resultSet.getString("password"), connection.resultSet.getBoolean("uservalidated")));
            } while (connection.resultSet.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList de propriedades ou de pessoas" + ex);
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
        //ObservableList<String> availableChoices = choiceBox.getItems();to get items
        //String selectedChoice = choiceBox.getSelectionModel().getSelectedItem();to get selected item
    }
}
