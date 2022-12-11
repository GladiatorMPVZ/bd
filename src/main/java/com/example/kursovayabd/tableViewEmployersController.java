package com.example.kursovayabd;

import db.DbConnect;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import people.Applicant;
import people.Emploers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tableViewEmployersController implements Initializable {
    @FXML
    private MFXButton addEBtn;

    @FXML
    private MFXButton deliteEBtn;

    @FXML
    private MFXTableView<Emploers> mfxTableView;

    @FXML
    private MFXButton refreshEBtn;

    String queryS = null;
    Connection connectionS = null;
    PreparedStatement preparedStatementS = null;
    ResultSet resultSetS = null;
    Emploers emploers = null;
    Map<Integer, Emploers> map = null;
    ObservableList<Emploers> SearchList = FXCollections.observableArrayList();

    @FXML
    void addEBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addE.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void deliteEBtnClick(ActionEvent event) throws SQLException {
        queryS = "DELETE FROM `employers` WHERE id =" + keys();
        System.out.println(queryS);
        connectionS  = DbConnect.getConnect();
        preparedStatementS = connectionS.prepareStatement(queryS);
        preparedStatementS.execute();
        refreshEBtnClick();
    }

    private Integer keys() {
        map = mfxTableView.getSelectionModel().getSelection();
        for (Map.Entry entry : map.entrySet()) {
            emploers = (Emploers) entry.getValue();
            System.out.println(emploers.getId());
            return emploers.getId();
        }
        return null;
    }

    @FXML
    void refreshEBtnClick() {
        try {
            SearchList.clear();

            queryS = "SELECT * FROM `employers`";
            preparedStatementS = connectionS.prepareStatement(queryS);
            resultSetS = preparedStatementS.executeQuery();

            while (resultSetS.next()) {
                SearchList.add(new Emploers(
                        resultSetS.getInt("id"),
                        resultSetS.getString("title"),
                        resultSetS.getString("kindEmp"),
                        resultSetS.getString("adress"),
                        resultSetS.getString("number")));
                mfxTableView.setItems(SearchList);
            }
        } catch (SQLException e) {
            Logger.getLogger(tableViewEmployersController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO;
        setupEmpTable();
    }

    public void setupEmpTable() {
        connectionS = DbConnect.getConnect();
        refreshEBtnClick();

        MFXTableColumn<Emploers> idColumn = new MFXTableColumn<>("id", true, Comparator.comparing(Emploers::getId));
        MFXTableColumn<Emploers> titleColumn = new MFXTableColumn<>("Название", true, Comparator.comparing(Emploers::getTitle));
        MFXTableColumn<Emploers> kindOfActivityColumn = new MFXTableColumn<>("Вид деятельности", true, Comparator.comparing(Emploers::getKindEmp));
        MFXTableColumn<Emploers> adressColumn = new MFXTableColumn<>("Адрес", true, Comparator.comparing(Emploers::getAdress));
        MFXTableColumn<Emploers> numberColumn = new MFXTableColumn<>("Номер", true, Comparator.comparing(Emploers::getNumber));

        idColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Emploers::getId));
        titleColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Emploers::getTitle));
        kindOfActivityColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Emploers::getKindEmp));
        adressColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Emploers::getAdress));
        numberColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Emploers::getNumber));
        mfxTableView.setItems(SearchList);
        mfxTableView.getTableColumns().addAll(idColumn, titleColumn, kindOfActivityColumn, adressColumn, numberColumn);
        mfxTableView.setItems(SearchList);
        mfxTableView.getFilters().addAll(
                new IntegerFilter<>("id", Emploers::getId),
                new StringFilter<>("Название", Emploers::getTitle),
                new StringFilter<>("Вид деятельности", Emploers::getKindEmp),
                new StringFilter<>("Адрес", Emploers::getAdress),
                new StringFilter<>("Номер", Emploers::getNumber)
        );
    }

    @FXML
    void initialize() {
        setupEmpTable();
        mfxTableView.autosizeColumnsOnInitialization();
    }
}
