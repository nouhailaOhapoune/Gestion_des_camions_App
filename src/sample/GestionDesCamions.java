package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionDesCamions implements Initializable {

    @FXML
    private TableView<Camion> tableview;

    @FXML
    private TableColumn<Camion, Integer> IDColumn;


    @FXML
    private TableColumn<Camion,String> ImmatriculationColumn;

    @FXML
    private TableColumn<Camion,String> ModelColumn;

    @FXML
    private TableColumn<Camion,String> DateColumn;

    @FXML
    private TableColumn<Camion,Integer> TARColumn;

    @FXML
    private TableColumn<Camion,String> WorkplaceColumn;
    DBConnection connectNow =null;
    Connection connectDB=null;
    Statement statement = null;
    ResultSet rs = null;
    ObservableList<Camion> Camions = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDColumn.setCellValueFactory(new PropertyValueFactory<Camion, Integer>("IdCamion"));
        ImmatriculationColumn.setCellValueFactory(new PropertyValueFactory<Camion, String>("Immatriculation"));
        ModelColumn.setCellValueFactory(new PropertyValueFactory<Camion, String>("Model"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Camion, String>("Date_vidange"));
        TARColumn.setCellValueFactory(new PropertyValueFactory<Camion, Integer>("Tar"));
        WorkplaceColumn.setCellValueFactory(new PropertyValueFactory<Camion, String>("Workplace"));
        tableview.setItems(Camions);
        tableview.setEditable(true);

    }
    @FXML


    void AddBtn(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddPage.fxml"));
            Scene scene = new Scene(parent);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex){
            Logger.getLogger(GestionDesCamions.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    void DeleteBtn(ActionEvent event) throws SQLException {
        Camion truckSelected = tableview.getSelectionModel().getSelectedItem();
        if (truckSelected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No line is selected !!");
            alert.setContentText(null);

            alert.showAndWait();
        } else {
            String query = "delete from camion where Registration = '" + truckSelected.getImmatriculation() + "' ;";
            try {
                connectDB = connectNow.getConnection();
                statement = connectDB.createStatement();
                statement.executeUpdate(query);
                Select();
            }
        catch(Exception var){
            var.printStackTrace();
            var.getCause();

        }
    }
    }



    @FXML
    void RefreshBtn(ActionEvent event) {
        Select();
    }


    @FXML
    void EditBtn(ActionEvent event) {
        Camion truckSelected =  tableview.getSelectionModel().getSelectedItem();
        if (truckSelected==null){Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No line is selected !!");
            alert.setContentText(null);

            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddPage.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(GestionDesCamions.class.getName()).log(Level.SEVERE, null, ex);
            }
            AddPage add = loader.getController();
            add.setTextField(truckSelected.getIdCamion(), truckSelected.getImmatriculation(), truckSelected.getModel(),
                    truckSelected.getDate_vidange(), truckSelected.getTar(), truckSelected.getWorkplace());
            add.setUpdate(true);
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }


    }


    @FXML
    void Retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Fermer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        alert.setContentText("Etes-vous sur de vouloir sortir?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        } else {
        }

    }
    private  void Select(){
        try {
            Camions.clear();
            String query = "SELECT * from camion";
            connectNow = new DBConnection();
             connectDB = connectNow.getConnection();
             statement = connectDB.createStatement();
              rs = statement.executeQuery(query);
            while(rs.next()) {
                Camions.add(new Camion(
                        rs.getInt("idCamion"),
                        rs.getString("Registration"),
                        rs.getString("Model"),
                        rs.getDate("Dateoil").toLocalDate(),
                        rs.getInt("Tar"),
                        rs.getString("Workplace")));
                tableview.setItems(Camions);
            }
        } catch (SQLException ex){
            Logger.getLogger(GestionDesCamions.class.getName()).log(Level.SEVERE,null,ex);
        }
    }


}
