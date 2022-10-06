package com.example.kursovayabd;

import db.DbConnect;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;

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

import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
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

public class tableViewSearchController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXButton addBtn;

    @FXML
    private MFXButton deliteBtn;

    @FXML
    private MFXTableView<Applicant> mfxTableView;

    @FXML
    private MFXButton refreshBtn;

    String queryS = null;
    Connection connectionS = null;
    PreparedStatement preparedStatementS = null;
    ResultSet resultSetS = null;
    Applicant applicant = null;
    Map<Integer, Applicant> map = null;
    ObservableList<Applicant> SearchList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        setupTable();
    }

    @FXML
    void addBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void deliteBtnClick(ActionEvent event) throws SQLException {
        queryS = "DELETE FROM `applicant` WHERE id =" + String.valueOf(keys());
        System.out.println(queryS);
        connectionS  = DbConnect.getConnect();
        preparedStatementS = connectionS.prepareStatement(queryS);
        preparedStatementS.execute();
        refreshBtnClick();
    }

    private Object keys() {
        map = mfxTableView.getSelectionModel().getSelection();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals("people.**")) {
                System.out.println(entry);
                return entry.getKey();
            }
        }
        return null;
    }

    @FXML
    void refreshBtnClick() {
        try {
            SearchList.clear();

            queryS = "SELECT * FROM `applicant`";
            preparedStatementS = connectionS.prepareStatement(queryS);
            resultSetS = preparedStatementS.executeQuery();

            while (resultSetS.next()) {
                SearchList.add(new Applicant(
                        resultSetS.getInt("id"),
                        resultSetS.getString("lastname"),
                        resultSetS.getString("firstname"),
                        resultSetS.getString("patronymic"),
                        resultSetS.getString("qualification"),
                        resultSetS.getString("kindOfActivity"),
                        resultSetS.getString("other"),
                        resultSetS.getInt("salary")));
                mfxTableView.setItems(SearchList);
            }
        } catch (SQLException e) {
            Logger.getLogger(tableViewSearchController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void initialize() {
        setupTable();
        mfxTableView.autosizeColumnsOnInitialization();
    }

    public void setupTable() {
        connectionS = DbConnect.getConnect();
        refreshBtnClick();

        MFXTableColumn<Applicant> idColumn = new MFXTableColumn<>("id", true, Comparator.comparing(Applicant::getId));
        MFXTableColumn<Applicant> lastnameColumn = new MFXTableColumn<>("lastname", true, Comparator.comparing(Applicant::getLastname));
        MFXTableColumn<Applicant> firstnameColumn = new MFXTableColumn<>("firstname", true, Comparator.comparing(Applicant::getFirstname));
        MFXTableColumn<Applicant> patronymicColumn = new MFXTableColumn<>("patronymic", true, Comparator.comparing(Applicant::getPatronymic));
        MFXTableColumn<Applicant> qualificationColumn = new MFXTableColumn<>("qualification", true, Comparator.comparing(Applicant::getQualification));
        MFXTableColumn<Applicant> kindOfActivityColumn = new MFXTableColumn<>("kindOfActivity", true, Comparator.comparing(Applicant::getKindOfActivity));
        MFXTableColumn<Applicant> otherColumn = new MFXTableColumn<>("other", true, Comparator.comparing(Applicant::getOther));
        MFXTableColumn<Applicant> salaryColumn = new MFXTableColumn<>("salary", true, Comparator.comparing(Applicant::getSalary));

        idColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Applicant::getId));
        lastnameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Applicant::getLastname));
        firstnameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Applicant::getFirstname));
        patronymicColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Applicant::getPatronymic));
        qualificationColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Applicant::getQualification));
        kindOfActivityColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Applicant::getKindOfActivity));
        otherColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Applicant::getOther));
        salaryColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Applicant::getSalary));
        mfxTableView.setItems(SearchList);
        mfxTableView.getTableColumns().addAll(idColumn, lastnameColumn, firstnameColumn, patronymicColumn, qualificationColumn, kindOfActivityColumn,
                otherColumn, salaryColumn);

        mfxTableView.setItems(SearchList);
        // mfxTableView.getFilters().addAll(
                //         new IntegerFilter<>("id", Applicant::getId),
        //         new StringFilter<>("lastname", Applicant::getLastname),
        //         new StringFilter<>("firstname", Applicant::getFirstname),
        //         new StringFilter<>("patronymic", Applicant::getPatronymic),
        //         new StringFilter<>("qualification", Applicant::getQualification),
        //         new StringFilter<>("kindOfActivity", Applicant::getKindOfActivity),
        //         new StringFilter<>("other", Applicant::getOther),
        //         new IntegerFilter<>("salary", Applicant::getSalary)
                // );

    }



}
