package com.example.kursovayabd;

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
import java.sql.ResultSet;
import java.sql.SQLException;

public class authFXController {

    @FXML
    private MFXTextField loginField;

    @FXML
    private MFXPasswordField passField;

    Connection connection = null;
    boolean check = false;

    @FXML
    void singInBtnClick(ActionEvent event) throws IOException, SQLException {
        if (checkUser() == true) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("who.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Check LOGIN or PASS");
            alert.showAndWait();
        }
    }

    public boolean checkUser() throws SQLException {
        connection = DbConnect.getConnect();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM users where login=? and password=?")) {
            preparedStatement.setString(1, loginField.getText());
            preparedStatement.setString(2, passField.getText());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    check = true;
                } else {
                    check = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    @FXML
    void regBtnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reg.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
