package com.example.kursovayabd;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class searchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField kindField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea otherField;

    @FXML
    private TextField patrField;

    @FXML
    private TextField qualFiald;

    @FXML
    private TextField salaryFeild;

    @FXML
    private JFXButton sendBtn;

    @FXML
    void sendBtnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert kindField != null : "fx:id=\"kindField\" was not injected: check your FXML file 'search.fxml'.";
        assert lastnameField != null : "fx:id=\"lastnameField\" was not injected: check your FXML file 'search.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'search.fxml'.";
        assert otherField != null : "fx:id=\"otherField\" was not injected: check your FXML file 'search.fxml'.";
        assert patrField != null : "fx:id=\"patrField\" was not injected: check your FXML file 'search.fxml'.";
        assert qualFiald != null : "fx:id=\"qualFiald\" was not injected: check your FXML file 'search.fxml'.";
        assert salaryFeild != null : "fx:id=\"salaryFeild\" was not injected: check your FXML file 'search.fxml'.";
        assert sendBtn != null : "fx:id=\"sendBtn\" was not injected: check your FXML file 'search.fxml'.";

    }

}
