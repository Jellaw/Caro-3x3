package com.example.tictactoemvvm.model;


// ô nào là của người chơi nào đã đánh
public class Cell {
    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public Cell(String name, String value){
        player = new Player(name,value);
    }

    public boolean isEmpty() {
        return player == null || player.value == Player.PlayerValue.VALUE_EMPTY;
    }
}
