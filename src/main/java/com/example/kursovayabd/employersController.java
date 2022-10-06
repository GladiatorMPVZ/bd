package com.example.kursovayabd;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class employersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adressField;

    @FXML
    private TextField kindField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    private JFXButton sendBtn;

    @FXML
    void sendBtnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert adressField != null : "fx:id=\"adressField\" was not injected: check your FXML file 'employers.fxml'.";
        assert kindField != null : "fx:id=\"kindField\" was not injected: check your FXML file 'employers.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'employers.fxml'.";
        assert numberField != null : "fx:id=\"numberField\" was not injected: check your FXML file 'employers.fxml'.";
        assert sendBtn != null : "fx:id=\"sendBtn\" was not injected: check your FXML file 'employers.fxml'.";

    }

}
