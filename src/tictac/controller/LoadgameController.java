/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tictac.Database;
import tictac.model.GameModel;

/**
 * FXML Controller class
 *
 * @author bloodymary
 */
public class LoadgameController implements Initializable{
    
   
    @FXML
    private Tab all;
    
    @FXML
    private TableView<GameModel> allTable;
    
    @FXML
    private TableColumn<GameModel, String> idAll;

    @FXML
    private TableColumn<GameModel, String> playersAll;

    //==== 
    
    @FXML
    private Tab computer;
    
    @FXML
    private TableView<GameModel> computerTable;

    @FXML
    private TableColumn<GameModel, String> idComp;

    @FXML
    private TableColumn<GameModel, String> playersComp;

    @FXML
    private Tab friend;
    
    @FXML
    private TableView<GameModel> friendTable;

    @FXML
    private TableColumn<GameModel, String> idFr;

    @FXML
    private TableColumn<GameModel, String> playersFr;

    @FXML
    private Tab online;
    
    @FXML
    private TableView<GameModel> onlineTable;

    @FXML
    private TableColumn<GameModel, String> idOnline;

    @FXML
    private TableColumn<GameModel, String> playersOnline;
    
    @FXML
    private ImageView back;

    @FXML
    void back(MouseEvent e) throws Exception{
        
        Parent root= FXMLLoader.load(getClass().getResource("../view/startGUI.fxml"));
         ImageView im = (ImageView) e.getSource();
        String id = im.getId();
        Stage window= (Stage)im.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
         
    }

    
    
    
    private ObservableList<GameModel> listAll=FXCollections.observableArrayList();
    private ObservableList<GameModel> listComputer=FXCollections.observableArrayList();
    private ObservableList<GameModel> listFriend=FXCollections.observableArrayList();
    private ObservableList<GameModel> listOnline=FXCollections.observableArrayList();

    public void initialize(URL location,ResourceBundle recourses) {
        Database d1 = new Database();
        
        String allGamesId = d1.selectAllGamesId();//retrieve all gamesid
        String allGamesIdArr[] = allGamesId.split(",");
        

        //retrieve all gamesid according to specific type (computer,online,friend)
        
        //retrieve all games players, the out put will be x-player1,o-player2 /x-player1,0-player2/0-player1,x-player2
        // String allGamesPlayers=d1.selectAllGamesPlayers();
        String allPlayersArr[] = d1.selectAllGamesPlayers();
//        for (int i = 0; i < arr.length; i++) {
//            String playerStr = arr[i];
//            String players[] = playerStr.split("[-,]");
//            for (int j = 0; j < players.length; j++) {
//           System.out.println(players[j]);
//
//            }
//
//        }
for(int i=0;i<allGamesIdArr.length;i++){
    if (i == 0) {
        allGamesIdArr[i] = String.valueOf(allGamesIdArr[i].charAt(1));
    } else if (i == allGamesIdArr.length - 1) {
        allGamesIdArr[i] = String.valueOf(allGamesIdArr[i].substring(0, allGamesIdArr[i].length() - 1));
    }
            listAll.add(new GameModel(allGamesIdArr[i],allPlayersArr[i]));
            
            
        }
        
        
        idAll.setCellValueFactory(new PropertyValueFactory<GameModel,String>("id"));
        playersAll.setCellValueFactory(new PropertyValueFactory<GameModel,String>("players"));
        
        allTable.setItems(listAll);
        allTable.setOnMouseClicked(ev->events(allTable,ev));

        //retrieve all games players according to specific type(computer,online,friend)
         //momkn tekon online,friend
         

        String gamesCompId = d1.selectGamesId("computer");
        String gamesCompIdArr[] = gamesCompId.split(",");

        String compPlayersArr[] = d1.selectGamesPlayers("computer");
        listComputer.clear();
        
        for(int i=0;i<gamesCompIdArr.length;i++){
            if(i==0){
                gamesCompIdArr[i]=String.valueOf(gamesCompIdArr[i].charAt(1));
            }else if(i==gamesCompIdArr.length-1){
                gamesCompIdArr[i]=String.valueOf(gamesCompIdArr[i].substring(0, gamesCompIdArr[i].length()-1));
            }
            listComputer.add(new GameModel(gamesCompIdArr[i],compPlayersArr[i]));
            
        }
        idComp.setCellValueFactory(new PropertyValueFactory<GameModel,String>("id"));
        playersComp.setCellValueFactory(new PropertyValueFactory<GameModel,String>("players"));
        computerTable.setItems(listComputer);
        computerTable.setOnMouseClicked(ev->events(computerTable,ev));
        
        
        
//        for (int i = 0; i < arr.length; i++) {
//            String playerStr = arr[i];
//            String Players[] = playerStr.split("[-,]");
//            for (int j = 0; j < Players.length; j++) {
//           System.out.println(Players[j]);
//            }
//
//        }
        String gamesFriendId = d1.selectGamesId("friend");
        System.out.println(gamesFriendId);
        String gamesFriendIdArr[] = gamesFriendId.split(",");

        String friendPlayersArr[] = d1.selectGamesPlayers("friend");
        listFriend.clear();
        friendTable.getItems().clear();
        
        for(int i=0;i<gamesFriendIdArr.length;i++){
            if(i==0){
                gamesFriendIdArr[i]=String.valueOf(gamesFriendIdArr[i].charAt(1));
            }else if(i==gamesFriendIdArr.length-1){
                gamesFriendIdArr[i]=String.valueOf(gamesFriendIdArr[i].substring(0, gamesFriendIdArr[i].length()-1));
            }
            listFriend.add(new GameModel(gamesFriendIdArr[i],friendPlayersArr[i]));
            System.out.println(gamesFriendIdArr[i]+friendPlayersArr[i]);
            
        }
        idFr.setCellValueFactory(new PropertyValueFactory<GameModel,String>("id"));
        playersFr.setCellValueFactory(new PropertyValueFactory<GameModel,String>("players"));
        
        friendTable.setItems(listFriend);
        friendTable.setOnMouseClicked(ev->events(friendTable,ev));
        
        
        
        String gamesOnlineId = d1.selectGamesId("online");
        String gamesOnlineIdArr[] = gamesOnlineId.split(",");

        String onlinePlayersArr[] = d1.selectGamesPlayers("online");
        
        for(int i=0;i<gamesOnlineIdArr.length;i++){
            if(i==0){
                gamesOnlineIdArr[i]=String.valueOf(gamesOnlineIdArr[i].charAt(1));
            }else if(i==gamesOnlineIdArr.length-1){
                gamesOnlineIdArr[i]=String.valueOf(gamesOnlineIdArr[i].substring(0, gamesOnlineIdArr[i].length()-1));
            }
            
            listOnline.add(new GameModel(gamesOnlineIdArr[i],onlinePlayersArr[i]));
            
        }
        idOnline.setCellValueFactory(new PropertyValueFactory<GameModel,String>("id"));
        playersOnline.setCellValueFactory(new PropertyValueFactory<GameModel,String>("players"));
        onlineTable.setItems(listOnline);
        onlineTable.setOnMouseClicked(ev->events(onlineTable,ev));
        

    }
    private void events(TableView table,MouseEvent e){
        try {
            GameModel gi=(GameModel) table.getSelectionModel().getSelectedItem();
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/replay.fxml"));
            
            root = loader.load();
            ReplayController controller=loader.getController();
            controller.initData(gi.getId());
            Stage window= new Stage();
            window.setScene(new Scene(root, 530, 400));
            
            window.show();
            
        } catch (IOException ex) {
            Logger.getLogger(LoadgameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
