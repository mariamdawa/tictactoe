/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.model;

/**
 *
 * @author bloodymary
 */
public class GameModel {
    private String id;
    private String players;

    public GameModel(String id, String players) {
        this.id = id;
        this.players = players;
    }

 

    public String getId() {
        return id;
    }

    public String getPlayers() {
        return players;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlayers(String players) {
        this.players = players;
    }
    
    
    
}
