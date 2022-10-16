package com.example.kursovayabd;

import db.DbConnect;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import people.Applicant;

public class addEController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXTextField adressField;

    @FXML
    private MFXTextField kindEField;

    @FXML
    private MFXTextField numberField;

    @FXML
    private MFXButton resetEBtn;

    @FXML
    private MFXButton saveEBtn;

    @FXML
    private MFXTextField titleField;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement;


    @FXML
    void resetEBtnClick() {
        titleField.setText("");
        kindEField.setText("");
        adressField.setText("");
        numberField.setText("");
    }

    @FXML
    void saveEBtnClick(ActionEvent event) throws SQLException {
        connection = DbConnect.getConnect();
        String title = titleField.getText();
        String kindEmp = kindEField.getText();
        String adress = adressField.getText();
        String number = numberField.getText();
        if (title.isEmpty() || kindEmp.isEmpty() || adress.isEmpty() || number.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else  {

            insert();
            resetEBtnClick();
        }
    }

    private void insert() throws SQLException {
        query = "INSERT employers (title, kindEmp, adress, number) VALUE " + "('" + titleField.getText() + "', '" + kindEField.getText() +
                "', '" + adressField.getText() + "', '" + numberField.getText() + "');";
        System.out.println(query);
        connection = DbConnect.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
