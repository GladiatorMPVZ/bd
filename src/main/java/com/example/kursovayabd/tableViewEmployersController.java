package com.example.kursovayabd;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DbConnect;
import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import people.Emploers;

public class tableViewEmployersController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton addEmpBtn;

    @FXML
    private TableColumn<Emploers, String> adrEmpCol;

    @FXML
    private JFXButton deliteEmpBtn;

    @FXML
    private TableColumn<Emploers, String> idEmpCol;

    @FXML
    private TableColumn<Emploers, String> kindEmpCol;

    @FXML
    private TableColumn<Emploers, String> numberEmpCol;

    @FXML
    private JFXButton refreshEmpBtn;

    @FXML
    private TableView<Emploers> tableView;

    @FXML
    private TableColumn<Emploers, String> titleEmpCol;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Emploers emploers = null;

    ObservableList<Emploers> EmploersList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }

    @FXML
    void addEmpBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void deliteEmpBtnClick(ActionEvent event) {

    }

    @FXML
    void refreshEmpBtnClick() {
        try {
            EmploersList.clear();

            query = "SELECT * FROM `employers`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmploersList.add(new Emploers(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("kindEmp"),
                        resultSet.getString("adress"),
                        resultSet.getString("number")));
                tableView.setItems(EmploersList);

            }
        } catch (SQLException e) {
            Logger.getLogger(tableViewEmployersController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadDate() {
        connection = DbConnect.getConnect();
        refreshEmpBtnClick();

        idEmpCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleEmpCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        kindEmpCol.setCellValueFactory(new PropertyValueFactory<>("kindEmp"));
        adrEmpCol.setCellValueFactory(new PropertyValueFactory<>("adress"));
        numberEmpCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        tableView.setItems(EmploersList);
    }

}
