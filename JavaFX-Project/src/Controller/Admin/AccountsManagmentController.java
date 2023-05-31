/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Accounts;
import Model.AccountsJpaController;
import Model.exceptions.NonexistentEntityException;
import View.ViewManager;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AccountsManagmentController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private TableView<Accounts> AccountsTable;
    @FXML
    private TableColumn<Accounts,Integer> idcolumn;
    @FXML
    private TableColumn<Accounts,Integer> Accountnumbercolumn;
    @FXML
    private TableColumn<Accounts,String> UserNamecolumn;
    @FXML
    private TableColumn<Accounts,String> columncolumn;
    @FXML
    private TableColumn<Accounts,Double> Balance;
    @FXML
    private TableColumn<Accounts,Date> CreationDatecolumn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private Button searchAccountBtn;
    private EntityManagerFactory emf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    idcolumn.setCellValueFactory(new PropertyValueFactory("id"));
    Accountnumbercolumn.setCellValueFactory(new PropertyValueFactory("account_number"));
    UserNamecolumn.setCellValueFactory(new PropertyValueFactory("username"));
    columncolumn.setCellValueFactory(new PropertyValueFactory("currency"));
    Balance.setCellValueFactory(new PropertyValueFactory("balance"));
    CreationDatecolumn.setCellValueFactory(new PropertyValueFactory("CreationDatecolumn"));
    this.emf = Persistence.createEntityManagerFactory("JavaFX-ProjectPU");
    
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
    ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML // ما الها لازم
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML // ما الها لازم
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML// ما الها لازم
    private void showAccountCreationPage(ActionEvent event) {
    }

    @FXML
    private void showAllAccounts(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Accounts> accountses = em.createNamedQuery("Accounts.findAll").getResultList();
        AccountsTable.getItems().addAll(accountses);
        
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        // ما عرفت اعملها للاسف 

    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) throws NonexistentEntityException {
        AccountsJpaController ajc = new AccountsJpaController(emf);
        int selectedid = idcolumn.getCellData(AccountsTable.getSelectionModel().getSelectedItem());
        ajc.destroy(selectedid);
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) throws NonexistentEntityException {
        EntityManager em = emf.createEntityManager();
        List<Accounts> resultAccount = (List<Accounts>) em.createNamedQuery("Accounts.findByAccountNumber")
        .setParameter("accountNumber",Integer.parseInt(accontSearchTF.getText()));
        AccountsTable.getItems().addAll(resultAccount);
    }
    
}
