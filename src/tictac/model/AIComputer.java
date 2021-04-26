/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.model;
import tictac.model.ComputerModel;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.HashMap;

/**
 *
 * @author M
 */
public class AIComputer {
    
    
  ComputerModel cmodel ;

 int f1 =-1;
 int f2= -1;
 
  HashMap<Integer, Integer> scores = new HashMap<Integer,Integer>();
      public AIComputer(ComputerModel cmodel){
            scores.put(0, 0);
            scores.put(1, -10);
            scores.put(2, 10);
            this.cmodel=cmodel;
      }
 
   //0 means tie, 10 means AI win, -10 means human win
   //int scores[]={0, -10, 10};
    public String Computerturn(){
        //minIntegerValue
        int bestScore= -1000;
        String move="B";
        int index1, index2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(cmodel.numbClicked[i][j]==0){
                    
                    //computer will play
                    cmodel.numbClicked[i][j]=2;
                    Integer score=miniMax(cmodel.numbClicked,0, false);
                   
                    cmodel.numbClicked[i][j]=0;
                    if(score >bestScore){
                        
                        bestScore=score;
                        move="";
                        index1=i;
                        index2= j;
                        move += "B"+ String.valueOf(index1) + String.valueOf(index2);
                        f1=i;
                        f2=j;
                    }
                }
                
            }
            
        }
        
        
        cmodel.numbClicked[f1][f2]=2;
       // System.out.println("Befor decrement"+ cmodel.getClicks());
        cmodel.decClicks();
        //System.out.println("Clicks remaining"+ cmodel.getClicks());
        //System.out.println("position computer choosse"+ move);
        //System.out.println("numbclicked[][]"+  cmodel.numbClicked[f1][f2] );
        return move;
    }
      
    public Integer miniMax(int [][]board,int depth, boolean isMaximizing){
       // System.out.println("Result"+ cmodel.getResult());
       //System.out.println("Enter minimax");
       //checking()==0 || checking()==1 || checking()==2
       Integer resltChecking= checking();
        if(resltChecking != null){
          //  System.out.println("ResultChecking"+ checking());
           // System.out.println("scores[resltChecking]"+ scores.get(resltChecking));
            //if its a tie =0, computer win = 10, human win = -10
            return scores.get(resltChecking);
            
        }
        //Algorithm starts here:
        if(isMaximizing){
            //System.out.println("Enter Maximizing player"+ resltChecking);
            //minIntegerValue
           Integer bestScore= -1000;
           for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the board available?
                    if (cmodel.numbClicked[i][j] == 0) {
                        cmodel.numbClicked[i][j]=2;
                        Integer score = miniMax(cmodel.numbClicked ,depth + 1, false);
              //          System.out.println("score Maximizing player"+ score);
                        cmodel.numbClicked[i][j] =0;
                        bestScore=Math.max(score, bestScore);
                //        System.out.println("Best score in max player"+ bestScore);
                    }
                }//end inner for
           }//end outer for
           
           return bestScore;
                }//end condition maximizing
        else{
            //maxIntegerValue
            Integer bestScore= 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the board available?
                    if (cmodel.numbClicked[i][j]  == 0) {
                        cmodel.numbClicked[i][j] =1;
                        Integer score = miniMax(cmodel.numbClicked,depth + 1, true);
                        cmodel.numbClicked[i][j] =0;
                        bestScore=min(score, bestScore);
                    }
                }//end inner for
           }//end outer for
           return bestScore;
            
        }
    
    }

    //checking winner:
    public boolean equals3(int a, int b, int c){
        return a==b && b==c && a!=0;
    }
    
    
    
    
    public Integer checking(){
        Integer w=null;
          // horizontal
        for (int i = 0; i < 3; i++) {
        if (equals3(cmodel.numbClicked[i][0], cmodel.numbClicked[i][1], cmodel.numbClicked[i][2])) {
                w= cmodel.numbClicked[i][0];
                return w;
             }
         }
         // Vertical
        for (int i = 0; i < 3; i++) {
            if (equals3(cmodel.numbClicked[0][i], cmodel.numbClicked[1][i], cmodel.numbClicked[2][i])) {
                w = cmodel.numbClicked[0][i];
                return w;
            }
        }
          // Diagonal
        if (equals3(cmodel.numbClicked[0][0], cmodel.numbClicked[1][1], cmodel.numbClicked[2][2])) {
                w= cmodel.numbClicked[0][0];
                return w;
        }
        if (equals3(cmodel.numbClicked[2][0], cmodel.numbClicked[1][1], cmodel.numbClicked[0][2])) {
                w = cmodel.numbClicked[2][0];
                return w;
        }
        
        
        
        //checking Tie:
        
          int openSpots = 0;
          for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                        if (cmodel.numbClicked[i][j] == 0) {
                               openSpots++;
                        }
                }
            }
          
            if (w == null && openSpots == 0) {
                return 0;
  } else {
            return w;
  }
}

    
    }
    
    
  
  

