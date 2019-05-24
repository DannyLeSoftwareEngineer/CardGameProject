package view;

import model.interfaces.Player;

public class PlayerComboItem
{
    private String playerName;
    private Player player;

    //Creates an Comboitem where the player object is stored with the name of the player in the JComboBox, but only
    //the name of the player name is shown in the JComboBox using the toString method
    
    public PlayerComboItem(String key, Player player)
    {
        this.playerName = key;
        this.player = player;
    }

    @Override
    public String toString()
    {
        return playerName;
    }

    public String getKey()
    {
        return playerName;
    }

    public Player getValue()
    {
        return player;
    }
}