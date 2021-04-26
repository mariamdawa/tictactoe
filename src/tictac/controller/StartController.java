/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tictac.services.GameService;

/**
 *
 * @author M
 */
public class StartController {
    public static String FriendName;
    @FXML
    Button b1;
    @FXML
    Button b2; 
    @FXML
    TextField friendName;
    @FXML
    Label friendLabel;
    
        @FXML
    public void playGame(javafx.event.ActionEvent e) throws Exception{
                        Parent root= FXMLLoader.load(getClass().getResource("../view/gamesApproach.fxml"));
                            Button btn = (Button) e.getSource();
                            String id = btn.getId();
                            Stage window= (Stage)btn.getScene().getWindow();
                            window.setScene(new Scene(root, 530, 400));

                        
             
                }
    
     @FXML
        public void loadGame(javafx.event.ActionEvent e) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("../view/loadgame.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
    }

        @FXML
       public void singleChoosed(javafx.event.ActionEvent e) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("../view/computerApproach.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
    }
        @FXML
       public void multiChoosed(javafx.event.ActionEvent e) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("../view/playersApproach.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
    }
       @FXML
       public void easyChoosed(javafx.event.ActionEvent e) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("../view/computer.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
    }
        @FXML
       public void hardChoosed(javafx.event.ActionEvent e) throws Exception{
       
           
                    Parent root;
                 
                        root = FXMLLoader.load(getClass().getResource("../view/AIComputer.fxml"));
                        Button btn = (Button) e.getSource();
                        String id = btn.getId();
                        Stage window= (Stage)btn.getScene().getWindow();
                        window.setScene(new Scene(root, 530, 400));
                   
                    
             
      
         
    }
        @FXML
       public void friendChoosed(javafx.event.ActionEvent e) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("../view/friendName.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
    }
      
        @FXML
       public void Continue(javafx.event.ActionEvent e) throws Exception{
           if(friendName.getText().isEmpty())
          {
             friendLabel.setText("You must enter friend name");
            }
         else {
               FriendName=friendName.getText();
        Parent root= FXMLLoader.load(getClass().getResource("../view/GUI.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
    } }
       
        @FXML
       public void onlineChoosed(javafx.event.ActionEvent e) throws Exception{
       /* Parent root= FXMLLoader.load(getClass().getResource("../view/online.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
        */
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/online.fxml"));
        Parent root = loader.load(); 
        Online c=loader.<Online>getController();
           Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window = (Stage) btn.getScene().getWindow();
        c.setGameService(new GameService(window));
        c.start();
        
    
        window.setScene(new Scene(root, 530, 400));
         
        
        
         
    }
                @FXML
        public void back(javafx.scene.input.MouseEvent e) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
         ImageView im = (ImageView) e.getSource();
        String id = im.getId();
        Stage window= (Stage)im.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
    }
}
