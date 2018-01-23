package src;// deze import hebben we nodig voor gebruikersinput – let daar maar niet op.
import java.util.Scanner;

/*
EEN EXTRA VOORBEELD BIJ §7.6 VAN BLUEJ EDITIE 6: MULTI-DIMENSIONALE ARRAYS;

Een multi-dimensionale array kun je het beste vergelijken met een spelbord. Dit is meestal een 
systeem waarbij coördinaten als (X,Y) kunnen worden weergegeven – denk aan een schaakbord waarbij
je de positie van stukken weergeeft als bijvoorbeeld 'H3'. 

Om dit om een wat eenvoudiger manier duidelijk te maken, is onderstaand spel 'boter kaas en eieren'
uitgeprogrammeerd. Dit heeft een bord als volgt:

		+-------+------+--------+
		| (0,0) | (1,0) | (2,0) | 
		+-------+-------+-------+
		| (0,1) | (1,1) | (2,1) | 
		+-------+-------+-------+
		| (0,2) | (1,2) | (2,2) | 
		+-------+-------+-------+
		
Je ziet hier twee arrays lopen: één voor de X en één voor de Y. Beide lopen van 0 tot 2 
(drie elementen). In de code wordt dit in  het private veld 'board' bijgehouden, waarvan 
het data-type een twee-dimensionale array van char's is:
 
		private char[3][3] board
		             ^  ^
		             |  |
		 X -----------  --------Y
		 
Je kunt dit spel spelen door het op te starten en dan de coördinaten in te vullen wanneer het 
programma daar om vraagt: als ik bijvoorbeeld een X wil zetten op het rechtsmiddelste vakje, typ
ik 2,1 (zonder haakjes). 
Er is een minimale check op de input, want het gaat niet om een correcte werking; als het stuk 
gaat door een verkeerde input kun je vast wel bedenken waarom.

Bestudeer de programmacode en het commentaar dat er tussendoor gegeven is.

*/


public class BoterKaasEieren {
	public static void main(String[] args) {
		new BoterKaasEieren();
	}

	// Dit is het interessante veld. Een twee-dimensionale array die het speelveld bijhoudt.
	private char[][] board;

	// De huidige speler. Dit is ofwel een 'X' of een 'O'
	private char currentPlayerMark;
	private String currentPlayerName;

	private String namePlayer1;
	private String namePlayer2;
	private char markPlayer1;
	private char markPlayer2;

	Scanner reader = new Scanner(System.in);

	//constructor
	public BoterKaasEieren() {
		board = new char[3][3];

		initializeBoard();
		playGame();
	}

	// Dit is feitelijk de 'main loop'. Dit ding blijft lopen totdat het bord vol is
	// of er een winnaar is.
	private void playGame() {
		//De regels hieronder is om user-input op te vangen. Maak je daar niet druk om.
		//Van belang is dat de input wordt opgeslagen in de variable 'input'.
		// Scanner reader = new Scanner(System.in);
		String input = "";
		initializePlayers();


		//De onderstaande loop wordt uitgevoerd zolang 'gameNotFinished' geen 'false'
		//teruggeeft (hoe heet zo'n conditie?). Dat kan omdat er in die loop een methode
		//wordt aangeroepen die de boel blokkeert tot iemand een input heeft gegeven.

		while (gameNotFinished()) {
			//we printen elke keer de nieuwe status van het speelbord even uit
			printBoard();
			changePlayer();

			// Hier geven we aan wie er aan de beurt is en wat hij/zij moet invullen.
			System.out.println("De beurt in aan " + currentPlayerName);
			System.out.println("Geef positie op (x,y): ");

			//Deze methode blijft wachten totdat de gebruiker iets heeft ingeveoerd.
			//Hierom kunnen we deze loop laten blijven lopen zonder dat er continu
			//dingen op het scherm verschijnen.
			input = reader.next();

			//We maken een array van Strings van de input – check de API van de string:
			// https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String)
			String[] coords = input.split(",");

			placeMark(coords);
		}

		// Even het winnende bord afdrukken
		printBoard();

		//als we hier komen, hebben we die reader niet meer nodig, en het is wel netjes om
		//die even expliciet te sluiten.
		reader.close();
	}

	private void initializePlayers() {
		System.out.println("Please fill in name player 1");
		namePlayer1 = fillPlayerName();
		markPlayer1 = fillPlayerMark(namePlayer1);

		System.out.println("Please fill in name player 2");
		namePlayer2 = fillPlayerName();
		markPlayer2 = fillPlayerMark(namePlayer2);

		checkPlayerMarksAreUnique();
		currentPlayerName = namePlayer2;
		currentPlayerMark = markPlayer2;

	}

	private void checkPlayerMarksAreUnique() {
		if (markPlayer2 != markPlayer1) {
			return;
		}
		System.out.println("Because Player 1 and 2 have the same initials, player 2 has to choose a playing mark");
		markPlayer2 = reader.next().toUpperCase().charAt(0);
		checkPlayerMarksAreUnique();
	}

	private String fillPlayerName() {
		String newName;
		newName = reader.next();
		if (!newName.equals(namePlayer1)) {
			return newName;
		}
		System.out.println("Please fill in an unique name (So not " + namePlayer1 + ")");
		return fillPlayerName();
	}

	private char fillPlayerMark(String namePlayer) {
		return namePlayer.toUpperCase().charAt(0);
	}

	private boolean placeMark(String[] coords) {
		//We gaan er even van uit dat er in de input twee getallen zijn gegeven.
		int col = Integer.parseInt(coords[0]);
		int row = Integer.parseInt(coords[1]);

		//Let op hoe we opnieuw door een twee-dimensionale array lopen
		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == '-') {
					board[row][col] = currentPlayerMark;
					return true;
				}
			}
		}

		return false;
	}

	//Hier initialiseren we de twee-dimensionale array. We hebben dus twee for-lussen nodig:
	//voor elke array eentje. De variabel i loopt van 0 tot 2, net als de variabele j (dat
	//klopt ook, want we hebben dat ding geïnitialiseerd op char[3][3]).
	private void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '-'; // initieel is elk element een '-'
			}
		}

		//Even voor het gemak de exacte coördinaten weergeven
		printBoardCoords();
	}


	private void changePlayer() {
		// hoe heet deze constructie? - > Conditional (ternary) Operator -> maar nu niet meer!!!
		if (currentPlayerName.equals(namePlayer1)) {
			currentPlayerName = namePlayer2;
			currentPlayerMark = markPlayer2;
		} else {
			currentPlayerName = namePlayer1;
			currentPlayerMark = markPlayer1;
		}
	}


	private boolean gameNotFinished() {
		return !(isBoardFull() || checkForWin());
	}


	private void printBoard() {
		System.out.println("+---+---+---+");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("+---+---+---+");
		}
	}

	private void printBoardCoords() {
		System.out.println("Vul de coördinaten in zonder haakjes, gescheiden door een komma.");
		System.out.println("De coördinaten in het bord zijn als volgt:");
		System.out.println("+-------+-------+-------+");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print("(" + j + "," + i + ") | ");
			}
			System.out.println();
			System.out.println("+-------+-------+-------+");
		}
	}


	//Opnieuw hebben we hier een dubbele for-lus nodig, om door beide arrays heen te
	//loopen. We checken hier nu voor elk element wat er exact is zit, en als er nog ergens
	//een '-' voorkomt, is het bord nog niet vol (want initieel hebben we het bord volgezet
	//met een '-', in de methode initializeBoard)
	private boolean isBoardFull() {
		boolean isFull = true;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '-') {
					isFull = false; //welke optimalisatie is hier nog mogelijk?
				}
			}
		}
		if (isFull) {
			System.out.println("Het bord is vol; eind van het spel.");
			return true;
		}

		return false;
	}


	// voor de rest: nevermind

	private boolean checkForWin() {
		if (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin()) {
			System.out.println("We hebben een winnaar: " + currentPlayerName);
			return true;
		}
		return false;
	}


	private boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}
		return false;
	}


	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}
		return false;
	}


	private boolean checkDiagonalsForWin() {
		return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
	}


	private boolean checkRowCol(char c1, char c2, char c3) {
		return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}


}
