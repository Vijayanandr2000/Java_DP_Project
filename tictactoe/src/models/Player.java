package models;

import enums.PlayerType;

abstract public class Player {
    private String name;
    private int id;
    private String symbol;

    public Player(String name, int id, String symbol) {
        this.name = name;
        this.id = id;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    abstract PlayerType getPlayerType();

    abstract Cell makeMove(Board board);
}
