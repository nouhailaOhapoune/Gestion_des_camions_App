package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class SignIn {
    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Label label;

    @FXML
    void Access(ActionEvent event)  {
        if (!this.Username.getText().isBlank() && !this.Password.getText().isBlank()) {
            this.label.setText(" ");
            DBConnection connectNow = new DBConnection();
            Connection connectDB = connectNow.getConnection();
            String Username1 = this.Username.getText();
            String Password1 = this.Password.getText();
            String query = "SELECT tab.* FROM tab WHERE Username = '" + Username1 + "'AND Password = '" + Password1 + "'";
            try {
                PreparedStatement statement = connectDB.prepareStatement(query);
                ResultSet result = statement.executeQuery();
               if(result.next()) {
                   Parent root = FXMLLoader.load(getClass().getResource("Gestion_des_camions.fxml"));
                   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   Scene scene = new Scene(root);
                   stage.setScene(scene);
                   stage.show();
                    } else {
                        this.label.setText("Invalid login, please try again!");
                        Username.clear();
                        Password.clear();
                    }

            } catch (Exception var) {
                var.printStackTrace();
                var.getCause();
            }
        }
        else {
            this.label.setText("Please enter username and password!");}
    }
    @FXML
    void SignUp(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fermer(MouseEvent event) {
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

}
