package view;

import controller.CRUDController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginView
{
    private Button confirm;
    private Button cancel;
    private Button loginAsMember;
    VBox mainPane;
    
    
    public LoginView(CRUDController controller)
    {
        Stage stage = new Stage();
        stage.setX(300);
        stage.setY(300);
        stage.setMinWidth(350);
        mainPane = new VBox();
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
        start(controller);
    }
    
    private void start(CRUDController controller)
    {
       
        
        // Create elements
       
        Text mainText = new Text("Login");
        
        
        HBox userBox = new HBox();
        Text userText = new Text("User Name:");
        TextField userField = new TextField();
        
        HBox passwordBox = new HBox();
        Text passwordText = new Text("Password:   ");
        PasswordField passwordField = new PasswordField();
        
        HBox confirmButtonsBox = new HBox();
        confirm = new Button("Login");
        cancel = new Button("Cancel");
        loginAsMember = new Button("Login as Member");
        
        // adding elements to view
        mainPane.getChildren().add(mainText);
        



        
        userBox.getChildren().add(userText);
        userBox.getChildren().add(userField);
        mainPane.getChildren().add(userBox);
        
        passwordBox.getChildren().add(passwordText);
        passwordBox.getChildren().add(passwordField);
        mainPane.getChildren().add(passwordBox);
        
        confirmButtonsBox.getChildren().add(confirm);
        confirmButtonsBox.getChildren().add(cancel);
        confirmButtonsBox.getChildren().add(loginAsMember);
        mainPane.getChildren().add(confirmButtonsBox);
        

        
        
        // Setting sizes
       mainPane.setPrefSize( 400, 100);
        mainText.setFont(new Font(40));
        
        
        // Alignments
        
        mainPane.setPadding(new Insets(10,25,10,25));
        //mainPane.setSpacing(10);
       
        userBox.setPadding(new Insets(5,0,5,5));
        passwordBox.setPadding(new Insets(5,0,5,5));
       
        userBox.setSpacing(10);
        passwordBox.setSpacing(7);
        
        confirmButtonsBox.setPadding(new Insets(10,10,10,10));
        confirmButtonsBox.setSpacing(20);
        
        UserFactory userFactory = new UserFactory();
        
        
        // colors
        mainPane.setStyle("-fx-background-color: rgb(165,195,225);");
        mainText.setFill(Color.WHITE);
        
        // Actions
        confirm.setOnAction(event ->
            {
               if(userField.getText().toLowerCase().equals("admin"))        // Hardcoded user/password
                {
                    if(passwordField.getText().equals("admin"))
                    {
                        controller.showMainWindow(userFactory.getAdminUser(controller));
                        Stage closeStage = (Stage) confirm.getScene().getWindow();
                        closeStage.close();
                    }
                
                }
            }
    );
    
    
        cancel.setOnAction(event ->
                {
                    Stage closeStage = (Stage) confirm.getScene().getWindow();
                    closeStage.close();
                }
        );
    
        loginAsMember.setOnAction(event ->
                {
                    controller.showMainWindow(userFactory.getMemberUser(controller));
                    
                    Stage closeStage = (Stage) confirm.getScene().getWindow();
                    closeStage.close();
                }
        );
    }
}
