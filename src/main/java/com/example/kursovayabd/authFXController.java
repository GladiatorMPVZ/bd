package com.example.kursovayabd;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class authFXController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXTextField loginField;

    @FXML
    private MFXPasswordField passField;

    @FXML
    private JFXButton singInBtn;

    @FXML
    void singInBtnClick(ActionEvent event) throws IOException {
       // if (loginField.getText().equals("root") && passField.getText().equals("root")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("who.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
   //     }

    }

    @FXML
    void initialize() {
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'authFX.fxml'.";
        assert passField != null : "fx:id=\"passField\" was not injected: check your FXML file 'authFX.fxml'.";
        assert singInBtn != null : "fx:id=\"singInBtn\" was not injected: check your FXML file 'authFX.fxml'.";

    }

}
