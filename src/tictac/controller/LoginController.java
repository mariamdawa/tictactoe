/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author M
 */
public class LoginController {
    private static  String name;
    @FXML
    TextField userName;
    @FXML
    Label label;

  
  
    
    
            @FXML
    public void login(javafx.event.ActionEvent e) throws Exception{
        
        if (userName.getText().isEmpty())
{
label.setText("You must enter your name");
}
else {  setUserName(userName.getText());
           
            System.out.println("Name insise login"+ name);
        //label.setText("");    
        Parent root= FXMLLoader.load(getClass().getResource("../view/startGUI.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
        }   
    }
        public void setUserName(String s){
            name=s;
    }
    public String getUserName(){
        return name;
    }
}
