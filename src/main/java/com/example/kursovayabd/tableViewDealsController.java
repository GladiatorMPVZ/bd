package com.example.kursovayabd;

import db.DbConnect;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import people.Deals;

import java.net.URL;
import java.sql.*;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tableViewDealsController implements Initializable {

    @FXML
    private MFXButton deliteDealsBtn;

    @FXML
    private MFXTableView<Deals> mfxTableView;

    @FXML
    private MFXButton refreshDealsBtn;

    String queryS = null;
    CallableStatement callableStatement = null;
    Connection connectionS = null;
    PreparedStatement preparedStatementS = null;
    ResultSet resultSetS = null;
    Deals deals = null;
    Map<Integer, Deals> map = null;
    ObservableList<Deals> SearchList = FXCollections.observableArrayList();

    @FXML
    void refreshDealsBtnClick() {
        try {
            SearchList.clear();
            queryS = "SELECT * FROM `deals`";
            preparedStatementS = connectionS.prepareStatement(queryS);
            resultSetS = preparedStatementS.executeQuery();

            while (resultSetS.next()) {
                SearchList.add(new Deals(
                        resultSetS.getString("employersId"),
                        resultSetS.getString("applicantId"),
                        resultSetS.getString("post"),
                        resultSetS.getInt("commission")));
                mfxTableView.setItems(SearchList);
            }
        } catch (SQLException e) {
            Logger.getLogger(tableViewDealsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        try {
            setupDealsTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setupDealsTable() throws SQLException {
        connectionS = DbConnect.getConnect();
        callableStatement = connectionS.prepareCall("{call fillDeals}");
        callableStatement.execute();
        refreshDealsBtnClick();

        MFXTableColumn<Deals> employersIdColumn = new MFXTableColumn<>("Работодатель", true, Comparator.comparing(Deals::getEmployersId));
        MFXTableColumn<Deals> applicantIdColumn = new MFXTableColumn<>("Соискатель", true, Comparator.comparing(Deals::getApplicantId));
        MFXTableColumn<Deals> postColumn = new MFXTableColumn<>("Должность", true, Comparator.comparing(Deals::getPost));
        MFXTableColumn<Deals> commissionColumn = new MFXTableColumn<>("Комиссия", true, Comparator.comparing(Deals::getCommission));

        employersIdColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Deals::getEmployersId));
        applicantIdColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Deals::getApplicantId));
        postColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Deals::getPost));
        commissionColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Deals::getCommission));
        mfxTableView.setItems(SearchList);
        mfxTableView.getTableColumns().addAll(employersIdColumn, applicantIdColumn, postColumn, commissionColumn);
        mfxTableView.setItems(SearchList);
    }

    @FXML
    void initialize() throws SQLException {
        setupDealsTable();
        mfxTableView.autosizeColumnsOnInitialization();
    }
}
