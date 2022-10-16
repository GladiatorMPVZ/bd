package com.example.kursovayabd;

import com.jfoenix.controls.JFXButton;
import db.DbConnect;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class regController {

    @FXML
    private MFXTextField loginRegField;

    @FXML
    private JFXButton okBtn;

    @FXML
    private MFXPasswordField passRegField;

    Connection connection = null;
    PreparedStatement preparedStatement;
    String query = null;

    @FXML
    void okBtnClick(ActionEvent event) throws SQLException, IOException {
        String login = loginRegField.getText();
        String password = passRegField.getText();
        if (login.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            query = "INSERT users (login, password) VALUE " + "('" + loginRegField.getText() + "', '" + passRegField.getText() + "');";
            connection = DbConnect.getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            Stage stage = (Stage) okBtn.getScene().getWindow();
            stage.close();
        }
    }
}
