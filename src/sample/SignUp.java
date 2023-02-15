package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class SignUp{
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TextField Name;

    @FXML
    private TextField Surname;

    @FXML
    private TextField Age;

    @FXML
    private TextField Password;

    @FXML
    private TextField Username;

    @FXML
    private Label label;
    int i = 0;

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
   public void check(){
       Age.textProperty().addListener(new ChangeListener<String>() {
           public void changed(ObservableValue<? extends String> observable, String oldValue,
                               String newValue) {
               if (!newValue.matches("\\d*")) {
                   Age.setText(newValue.replaceAll("[^\\d]", ""));

               }
           }
       });
   }
    @FXML
    void SignUp(ActionEvent event) {
        this.label.setText("  ");
        String Name = this.Name.getText();
        String Surname = this.Surname.getText();
        String Age1 = this.Age.getText();
        String Username = this.Username.getText();
        String Password = this.Password.getText();
        String query = " insert into tab (Name, Surname, Age, Username, Password) VALUES('";
        String insertValues = Name + "','" + Surname + "','" + Integer.parseInt(this.Age.getText()) + "','" + Username + "','" + Password + "')";
        String insertToRegister = query + insertValues;

        if((!Name.isEmpty() )&& (!Surname.isEmpty()) && (!Age1.isEmpty())&& (!Username.isEmpty() )&& (!Password.isEmpty())) {
            check();
            try {
                DBConnection connectNow = new DBConnection();
                Connection connectDB = connectNow.getConnection();
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                this.label.setText("successfully registered");
            } catch (Exception var) {
                var.printStackTrace();
                var.getCause();
                this.label.setText("Already used!");
                this.Name.clear();
                this.Username.clear();
                this.Age.clear();
                this.Username.clear();
                this.Password.clear();
            }
        }
        else  if( Name.isEmpty()  && Surname.isEmpty() && Age1.isEmpty() && Username.isEmpty()  && Password.isEmpty()  ){
            Alert alert6 = new Alert(Alert.AlertType.ERROR);
            alert6.setTitle("Error ");
            alert6.setHeaderText("Please fill all data!!");
            alert6.setContentText(null);
            alert6.showAndWait();
        }
        else {
            if (Name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("Enter your name!!");
                alert.setContentText(null);
                alert.showAndWait();
            }
            if (Surname.isEmpty()) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error ");
                alert2.setHeaderText("Enter your surname!!");
                alert2.setContentText(null);
                alert2.showAndWait();
            }
            if (Age.getText().isEmpty()) {
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Error ");
                alert3.setHeaderText("Enter your age!!");
                alert3.setContentText(null);
                alert3.showAndWait();
            }
            if (Username.isEmpty()) {
                Alert alert4 = new Alert(Alert.AlertType.ERROR);
                alert4.setTitle("Error ");
                alert4.setHeaderText("Enter your username!!");
                alert4.setContentText(null);
                alert4.showAndWait();
            }
            if (Password.isEmpty()) {
                Alert alert5 = new Alert(Alert.AlertType.ERROR);
                alert5.setTitle("Error ");
                alert5.setHeaderText("Enter your password!!");
                alert5.setContentText(null);
                alert5.showAndWait();
            }
        }
    }
    @FXML
    void Recherche(ActionEvent event) {
        this.label.setText("  ");
        i = 2;
        String recherche = "Select * from tab where idtab = 1";
        try {
            DBConnection connectNow = new DBConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(recherche);
            rs.next();
            String Name1 = rs.getString("Name");
            String Surname1 = rs.getString("Surname");
            int Age1 =rs.getInt("Age");
            String Username1 = rs.getString("Username");
            String Password1 = rs.getString("Password");

            Name.setText(Name1);
            Surname.setText(Surname1);
            Age.setText(String.valueOf(Age1));
            Username.setText(Username1);
            Password.setText(Password1);

        } catch (Exception var) {
            var.printStackTrace();
            var.getCause();
        }
    }
    @FXML
    void delete(ActionEvent event) {
        this.label.setText("  ");
        String query = "DELETE FROM tab WHERE idtab="+i;
        try {
            DBConnection connectNow = new DBConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            this.label.setText("successfully registered");
        } catch (Exception var) {
            var.printStackTrace();
            var.getCause();
        }
    }

    @FXML
    void next(ActionEvent event) {
        this.label.setText("  ");
         i=i+1;
        String rech1 = "SELECT * FROM tab WHERE idtab >="+i+" ORDER BY idtab LIMIT 1;";
        try {
            DBConnection connectNow = new DBConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(rech1);
            rs.next();
            String Name1 = rs.getString("Name");
            String Surname1 = rs.getString("Surname");
            int Age1 = rs.getInt("Age");
            String Username1 = rs.getString("Username");
            String Password1 = rs.getString("Password");

            Name.setText(Name1);
            Surname.setText(Surname1);
            Age.setText(String.valueOf(Age1));
            Username.setText(Username1);
            Password.setText(Password1);
            rs.close();
        } catch (Exception var) {
            var.printStackTrace();
            var.getCause();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Nothing after this!!");
            alert.setContentText(null);

            alert.showAndWait();

        }


    }

    @FXML
    void previous(ActionEvent event) {
        this.label.setText("  ");
        if (i > 1) {
            i=i-1;
            String rech1 = "SELECT * FROM tab WHERE idtab <= "+i+" ORDER BY idtab  DESC LIMIT 1;";
            try {
                DBConnection connectNow = new DBConnection();
                Connection connectDB = connectNow.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet rs = statement.executeQuery(rech1);
                rs.next();
                String Name1 = rs.getString("Name");
                String Surname1 = rs.getString("Surname");
                int Age1 = rs.getInt("Age");
                String Username1 = rs.getString("Username");
                String Password1 = rs.getString("Password");

                Name.setText(Name1);
                Surname.setText(Surname1);
                Age.setText(String.valueOf(Age1));
                Username.setText(Username1);
                Password.setText(Password1);

            } catch (Exception var) {
                var.printStackTrace();
                var.getCause();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Nothing before this!!");
            alert.setContentText(null);

            alert.showAndWait();

        }
    }

    @FXML
    void update(ActionEvent event) {
        this.label.setText("  ");
        String query = "UPDATE tab SET Name = '" + Name.getText() + "', Surname = '" + Surname.getText() + "', Age = '" + Age.getText() + "', Username = '"
                + Username.getText() + "', Password = '" + Password.getText() + "' Where idtab="+i;

        try {
            DBConnection connectNow = new DBConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            this.label.setText("successfully registered");
        } catch (Exception var) {
            var.printStackTrace();
            var.getCause();

        }
    }

    @FXML
    void SignIn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}