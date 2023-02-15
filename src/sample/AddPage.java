package sample;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AddPage {
    @FXML
    private TextField RegistrationFld;

    @FXML
    private TextField ModelFld;

    @FXML
    private DatePicker DateFld;

    @FXML
    private TextField TARFld;

    @FXML
    private TextField WorkplaceFld;
    private boolean update;
    int idCamion;
    String query;



    @FXML
    void clean(ActionEvent event) {
        RegistrationFld.clear();
        ModelFld.clear();
        TARFld.clear();
        DateFld.setValue(null);
        WorkplaceFld.clear();

    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        String registration = RegistrationFld.getText();
        String Model = ModelFld.getText();
        String Date = String.valueOf(DateFld.getValue());
        String TAR = TARFld.getText();
        String Workplace = WorkplaceFld.getText();

        if (registration.isEmpty() || Model.isEmpty() || Date.isEmpty() || Workplace.isEmpty()|| TAR.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.showAndWait();
        } else {
            try {

                if(update==true){
                    String query  = "UPDATE `camion` SET "
                            + "`Registration`='"+ registration +"',"
                            + "`Model`= '"+ Model + "',"
                            + "`Dateoil`='"+ Date + "',"
                            + "`Tar`="+Integer.parseInt(TARFld.getText())+" ,"
                            + "`Workplace`= '"+ Workplace +"' WHERE idCamion = "+idCamion+";";
                    DBConnection connectNow = new DBConnection();
                    Connection connectDB = connectNow.getConnection();
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(query);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("successfully updated !!");
                    alert.showAndWait();

                } else {
                    String query = "insert into camion (Registration, Model, Dateoil, Tar, Workplace) " +
                            "VALUES('" + registration + "','" + Model + "','" + Date + "','" +
                            Integer.parseInt(TARFld.getText()) + "','" + Workplace + "')";
                    DBConnection connectNow = new DBConnection();
                    Connection connectDB = connectNow.getConnection();
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(query);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("successfully registered !!");
                    alert.showAndWait();
                }

            } catch (Exception var) {
                var.printStackTrace();
                var.getCause();
            }
        }

    }

    void setTextField(int id, String Registartion,  String Model,LocalDate Date,int Tar, String Workplace) {

        idCamion = id;
        RegistrationFld.setText(Registartion);
        ModelFld.setText(Model);
        DateFld.setValue(Date);
        TARFld.setText(String.valueOf(Tar));
        WorkplaceFld.setText(Model);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }






}
