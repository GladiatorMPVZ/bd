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

public class addSController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXTextField kindSField;

    @FXML
    private MFXTextField nameSField;

    @FXML
    private MFXTextField otherSField;

    @FXML
    private MFXTextField patSField;

    @FXML
    private MFXTextField qualSField;

    @FXML
    private MFXButton resetBtn;

    @FXML
    private MFXTextField salarySField;

    @FXML
    private MFXButton saveBtn;

    @FXML
    private MFXTextField sernameSField;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement;

    @FXML
    void resetBtnClick() {
        nameSField.setText("");
        sernameSField.setText("");
        patSField.setText("");
        qualSField.setText("");
        kindSField.setText("");
        otherSField.setText("");
        salarySField.setText("");
    }

    @FXML
    void saveBtnClick(ActionEvent event) throws SQLException {
        connection = DbConnect.getConnect();
        String name = nameSField.getText();
        String lastname = sernameSField.getText();
        String patr = patSField.getText();
        String qual = qualSField.getText();
        String kind = kindSField.getText();
        String other = otherSField.getText();
        String salary = salarySField.getText();
        if (name.isEmpty() || lastname.isEmpty() || patr.isEmpty() || qual.isEmpty() || kind.isEmpty() || other.isEmpty() || salary.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else  {

            insert();
            resetBtnClick();
        }
    }

    private void insert() throws SQLException {
        query = "INSERT applicant (lastname, firstname, patronymic, qualification, kindOfActivity, other, salary) VALUE " +
                "('" + nameSField.getText() + "', '" + sernameSField.getText() + "', '" + patSField.getText() + "', '" + qualSField.getText()
                + "', '" + kindSField.getText() + "', '" + otherSField.getText() + "', " + Integer.parseInt(salarySField.getText()) + ");";
        System.out.println(query);
        connection = DbConnect.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
