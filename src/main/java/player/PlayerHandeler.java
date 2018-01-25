package player;

public class PlayerHandeler {


    public static Player[] initializePlayers(){

        Player[] players = new Player[2];

        System.out.println("Please fill in a name player 1:");
        Player player1 = new Player();
        System.out.println("Please fill in a name player 2:");
        Player player2 = new Player();

        player1.togglePlayerTurn();

        while (player1.equals(player2)) {
            System.out.println("Please fill in an unique name (So not " + player1.getName() + ")");
            player2 = new Player();
        }

        while (player1.equalsMark(player2)) {
            System.out.println("Because Player 1 and 2 have the same initials, player 2 has to choose a playing mark");
            player2.setPlayerMark();
        }

        players[0] = player1;
        players[1] = player2;



        return players;
    }

    public static Player changePlayer(Player[] players){
        Player becomesCurrentPlayer;
        if(players[0].isMyTurn()){
            becomesCurrentPlayer = players[1];
        }else{
            becomesCurrentPlayer= players[0];
        }
        players[0].togglePlayerTurn();
        players[1].togglePlayerTurn();

        return becomesCurrentPlayer;
    }




}
