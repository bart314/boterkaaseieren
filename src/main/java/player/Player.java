package player;


import java.util.Objects;
import java.util.Scanner;

public class Player {
    private String name;
    private char playerMark;
    private boolean myTurn;
    private Scanner reader = new Scanner(System.in);

    Player() {
        this.name = reader.next();
        this.playerMark = this.name.toUpperCase().charAt(0);
    }

    public String getName() {
        return name;
    }

    public char getPlayerMark() {
        return playerMark;
    }

    public void setPlayerMark() {
        this.playerMark = reader.next().toUpperCase().charAt(0);
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void togglePlayerTurn() {
        myTurn = !myTurn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName());

    }


    public boolean equalsMark(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getPlayerMark(), player.getPlayerMark());

    }
}
