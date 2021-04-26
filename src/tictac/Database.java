package tictac;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Database {
    public void create()
    {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe? createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "0175471031");
                        String sqlCreate = "CREATE TABLE IF NOT EXISTS replay"
            +"  (gameid    int(11) PRIMARY KEY  AUTO_INCREMENT,"         
            + "  steps           VARCHAR(500),"
            + "   players         VARCHAR(500),"
            + "   winner          VARCHAR(45),"
            + "   play_with_who   VARCHAR(45))";

            Statement stmt = con.createStatement();
            stmt.execute(sqlCreate);
            System.err.println("Hi I'm the database");
        }
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void insert(String steps,String players,String winner, String play_with_who)
    {
         try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031");
         //Insert Statment
            String insertString=new String("INSERT INTO replay(steps,players,winner,play_with_who) "+"values(?,?,?,?)");
            PreparedStatement preparedStmt = con.prepareStatement(insertString);
     
            preparedStmt.setString(1, steps);
            preparedStmt.setString(2, players);
            preparedStmt.setString(3, winner);
            preparedStmt.setString(4, play_with_who);
            
            
 
            preparedStmt.execute();
            System.out.println("---------------------------------------------------");
            System.out.println("Insert Stament");
            preparedStmt.close();
    }
          catch(SQLException ex)
        {
            System.out.println("Erroor");
            ex.printStackTrace();
        }
    }
    
    //select games id according to specific type
    public String selectGamesId(String type)
    {
        Queue<Integer> games_id = new LinkedList<>();
        String id;
       
        int i=0;
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031");
          
            //Select statment
             String selectString="select * from replay where play_with_who = ?";
            PreparedStatement preparedStmt = con.prepareStatement(selectString);
            preparedStmt .setString(1,type);
            ResultSet rs=preparedStmt.executeQuery();
            System.out.println("Select Stament");
            while(rs.next())
            {
               
                games_id.add(rs.getInt(1));
               
            }
            
           preparedStmt.close();
        }
           catch(SQLException ex)
        {
            ex.printStackTrace();
        }
         id=games_id.toString();
        return id;      
           
    }
    //select all games id
     public String selectAllGamesId()
    {
        Queue<Integer> games_id = new LinkedList<>();
        String allId;
       
        int i=0;
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031");
          
            //Select statment
             String selectString="select * from replay";
            PreparedStatement preparedStmt = con.prepareStatement(selectString);
          
            ResultSet rs=preparedStmt.executeQuery();
            System.out.println("Select Stament");
            while(rs.next())
            {
               
                games_id.add(rs.getInt(1));
               
            }
            
           preparedStmt.close();
        }
           catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        allId=games_id.toString();
        return allId;
      
           
    }
     
      //select games players according to specific type
    public String [] selectGamesPlayers(String type)
    {
        Queue<String> games_player = new LinkedList<>();
        String players="";
       
       
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031");
            //Select statment
             String selectString="select * from replay where play_with_who = ?";
            PreparedStatement preparedStmt = con.prepareStatement(selectString);
            preparedStmt .setString(1,type);
            ResultSet rs=preparedStmt.executeQuery();
            System.out.println("Select Stament");
            while(rs.next())
            {
               
                games_player.add(rs.getString(3));
               
            }
            
           preparedStmt.close();
        }
           catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        for (String i :games_player) {
              players += i + "/";
             
            }
        String arr[]= players.split("/");
        return arr;      
           
    }
     //select all games players
     public String [] selectAllGamesPlayers()
    {
        Queue<String> games_player = new LinkedList<>();
        String allPlayers="";
       
      
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031");
          
            //Select statment
             String selectString="select * from replay";
            PreparedStatement preparedStmt = con.prepareStatement(selectString);
          
            ResultSet rs=preparedStmt.executeQuery();
            System.out.println("Select Stament");
            while(rs.next())
            {
               
                games_player.add(rs.getString(3));
               
            }
            
           preparedStmt.close();
        }
           catch(SQLException ex)
        {
            ex.printStackTrace();
        }
         for (String i :games_player) {
              allPlayers += i + "/";
             
            }
      String arr[]= allPlayers.split("/");
        return arr;
      
           
    }
    public String selectSteps(int id)
    {
        String step="";
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031");
            //Select statment
             String selectString=new String("select * from replay where gameid= "+id);
            PreparedStatement preparedStmt = con.prepareStatement(selectString); 
            ResultSet rs=preparedStmt.executeQuery(selectString);
            System.out.println("Select Stament");
            while(rs.next())
            {
               
                step=rs.getString(2);
               
            }
            
           preparedStmt.close();
        }
           catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return step;
           
    }
    public String selectPlayers(int id)
    {
        String players="";
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031");
          
            //Select statment
             String selectString=new String("select * from replay where gameid= "+id);
            PreparedStatement preparedStmt = con.prepareStatement(selectString); 
            ResultSet rs=preparedStmt.executeQuery(selectString);
            System.out.println("Select Stament");
            while(rs.next())
            {
               
                players=rs.getString(3);
               
            }
            
           preparedStmt.close();
        }
           catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return players;
           
    }
    public String selectWinner(int id)
    {
        String winner="";
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031"); 
            
         
            //Select statment
             String selectString=new String("select * from replay where gameid= "+id);
            PreparedStatement preparedStmt = con.prepareStatement(selectString); 
            ResultSet rs=preparedStmt.executeQuery(selectString);
            System.out.println("Select Stament");
            while(rs.next())
            {
               
                winner=rs.getString(4);
              
            }
            
           preparedStmt.close();
        }
           catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return winner;
           
    }
    public String selectPlayWithWho(int id)
    {
        String PlayWithWho="";
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "root", "0175471031");
         
            //Select statment
             String selectString=new String("select * from replay where gameid= "+id);
            PreparedStatement preparedStmt = con.prepareStatement(selectString); 
            ResultSet rs=preparedStmt.executeQuery(selectString);
            System.out.println("Select Stament");
            while(rs.next())
            {
               
                PlayWithWho=rs.getString(5);
               
            }
            
           preparedStmt.close();
        }
           catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return PlayWithWho;
           
    }
}
   