/*+.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.controller;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.Window;
import tictac.model.ComputerModel;
import tictac.model.AIComputer;
import tictac.services.Dialogue;
public class ComputerController { 
    
     private ComputerModel model = new ComputerModel();
     private AIComputer modelAI = new AIComputer(model);
    // private StartController startController = new StartController();
     private Sounds sounds=new Sounds();
     private int player_X=1;
     private int player_O=2;
     File file;
//ids of my buttons:
 @FXML
 public ImageView B00;
 @FXML
 private ImageView B01;
 @FXML
 private ImageView B02;
 @FXML
 private ImageView B10;
 @FXML
 private ImageView B11;
 @FXML
 private ImageView B12;
 @FXML
 private ImageView B20;
 @FXML
 private ImageView B21;
 @FXML
 private ImageView B22; 
 /*
  @FXML
 private Label turn;
 @FXML 
 private Button R1;
*/

 
    @FXML
     public void reset() {
        model.resetNumbClicked();
        model.resetClicks();
        model.restBoard();
        model.setWinner(false);
        model.setTurn(true);
         model.setWinnerNumber("No Winner");
        // zeros();

        
        System.err.println("reset");
        B00.setImage(null);
        B01.setImage(null);
        B02.setImage(null);
        B10.setImage(null);
        B11.setImage(null);
        B12.setImage(null);
        B20.setImage(null);
        B21.setImage(null);
        B22.setImage(null);

    }
   
 private int counts=9;

//KeyEvent event
 @FXML
    public void clicked(MouseEvent e) throws FileNotFoundException, InterruptedException{
     
 if(model.getTurn() && model.canPlay())
 {
      //That is mean this is turn of x:
          Xplay(((ImageView)e.getSource()).getId());
          System.out.println("Turn befor Computer start"+ model.getTurn());
       //    noOnewinner();
         //   winnerdialog();
            if(model.canPlay() && !model.getTurn()){
                new java.util.Timer().schedule( 
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            try {                                  
                                    computerTurn();
                                    System.out.println("Turn after Computer play"+ model.getTurn());
                                } 
                            catch (FileNotFoundException ex) {
                                   Logger.getLogger(ComputerController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }
                    },1500); 
                    
                          
            }
          
         
    }
 else
 {
     System.out.println("It's Not Your turn");
 }
    }
    //B00
public void Xplay(String idClicked) throws FileNotFoundException{
     Image image_X = new Image(this.getClass().getResourceAsStream("../images/Ximg.PNG"));
     int index1= Character.getNumericValue(idClicked.charAt(1));
     int index2= Character.getNumericValue(idClicked.charAt(2));
      if(model.allow(index1,index2)){
           sounds.playSound1();
           switch(idClicked){
               case "B00":
                   B00.setImage(image_X);
                   model.setTurn(false);
                break;
                case "B01":
                   B01.setImage(image_X);
                   model.setTurn(false);
                break;
                case "B02":
                   B02.setImage(image_X);
                   model.setTurn(false);
                break;
                case "B10":
                   B10.setImage(image_X);
                   model.setTurn(false);
                break;
                case "B11":
                   B11.setImage(image_X);
                   model.setTurn(false);
                break;
                case "B12":
                   B12.setImage(image_X);
                   model.setTurn(false);
                break;
                case "B20":
                   B20.setImage(image_X);
                   model.setTurn(false);
                break;
                case "B21":
                   B21.setImage(image_X);
                   model.setTurn(false);
                break;
                case "B22":
                   B22.setImage(image_X);
                   model.setTurn(false);
                break;
           }
           model.checkWinner();         
           Dialoge();
            //model.setTurn(false);        
           // System.out.println("player Winner Dailog");
      }
 }
    public void computerTurn() throws FileNotFoundException
    {
         if(model.playable())
         {
         Image image_O = new Image(this.getClass().getResourceAsStream("../images/Oimg.PNG"));
        // switch(model.computerPlays()){
         switch(modelAI.Computerturn()){
           case "B00" :
                                B00.setImage(image_O);
           break;
          case "B01" :
                                B01.setImage(image_O);         
           break;
           case "B02" :
                               B02.setImage(image_O);
            break;
           case "B10" :
                               B10.setImage(image_O);
            break;
            case "B11" :
                               B11.setImage(image_O);
            break;
           case "B12" :
                               B12.setImage(image_O);
            break;
            case "B20" :
                                B20.setImage(image_O);
            break;
            case "B21" :
                               B21.setImage(image_O);
            break;
            case "B22" :
                               B22.setImage(image_O);
            break;
            default: 
                System.out.println("Can not play");
                break;
         }
              sounds.playSound2(); 
              model.checkWinner();
              Dialoge();
              model.setTurn(true); 
          
              System.out.println("Computer Winner Dailog");
    
    }
         
      
    }
   public void Dialoge()
{

if(model.getClicks()==0 && model.playable() || (!model.playable()))
{   
    Window window = B00.getScene().getWindow();
Platform.runLater(() -> {
               Dialogue  dialog = new Dialogue(window , model.getWinnerNumber());
               if(model.getClicks()==0 && model.playable())
               {
                  // gameReplay.add("It's a Tie");
               dialog.setlabeltext("");
               }
               else
               {
                   //gameReplay.add(model.getWinnerNumber());
                    dialog.setlabeltext(model.getWinnerNumber() );
               }
                dialog.reset().setOnAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
                       reset();
                       dialog.getMediaPlayer().stop();
                       dialog.getStage().close();
                       }
                });
                dialog.replay().setOnAction((ev)->{
                 //   replay();
                 dialog.getMediaPlayer().stop();
                    dialog.getStage().close();
                });
             
                 });
}

}

@FXML
public void returnToStart(ActionEvent e) throws IOException{
         Parent root= FXMLLoader.load(getClass().getResource("../view/startGUI.fxml"));
        Button btn = (Button) e.getSource();
        String id = btn.getId();
        Stage window= (Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 530, 400));
}
    
}
