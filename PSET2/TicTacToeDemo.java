public class TicTacToeDemo {
    public static void main(String[] args) {
        GameManager game = new GameManager(3);
        TokenFactory factory = new TokenFactory();
        game.setFactory(factory);

        Player p1 = new Player();
        Player p2 = new Player();

        game.addPlayer(p1);
        game.addPlayer(p2);

        game.ready();

        game.play(p1, 1,1);
        game.play(p2,0,0);
        game.play(p1, 0,1);
        game.play(p1,2,1);
        game.play(p1, 2,1);
        game.play(p2,2,0);
        game.play(p1,2,1);

    }
}

class GameManager {

    Player[] players;
    Token[][] grid;
    TokenFactory tf;
    private int size;

    GameManager(int size) {
        this.size = size;
        players = new Player[2];
        grid = new Token[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Token();
            }
        }
    }

    void ready() {
        players[0].setTurn(true);
        players[1].setTurn(false);
        System.out.println("Game ready, it is now " + players[0].getName() + "'s turn.");
    }

    void setFactory(TokenFactory f) {
        tf = f;
    }

    void addPlayer(Player p) {
        if (players[0] == null && players[1] == null) {
            players[0] = p;
            p.setName("x");
        } else if (players[0] != null && players[1] == null) {
            if (players[0].getName() == "x") {
                players[1] = p;
                p.setName("o");
            }
        } else {
            System.out.println("There are already two players.");
        }
        System.out.println("Player " + p.name + " added.");
    }

    void play(Player player, int x, int y) {
        if (!player.getTurn()) {
            System.out.println("Not " + player.getName() + "'s turn. Wait for the other player to move.");
        } else {
            try {
                System.out.println("\nPlayer " + player.getName() + " places token at (" + x + ", " + y + ")");
                grid[x][y] = tf.makeToken(player.getName());
                showGrid();
                if (checkWin(player, x, y)) {
                    System.out.println("Player " + player.getName() + " won the game.");
                }
                turnChange();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void showGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size ; j++) {
                System.out.printf("[" + grid[i][j].type + "]");
            }
            System.out.printf("\n");
        }
        System.out.println("");
    }

    void turnChange() {
        if (players[0].getTurn()) {
            players[0].setTurn(false);
            players[1].setTurn(true);
        } else if (players[1].getTurn()) {
            players[1].setTurn(false);
            players[0].setTurn(true);
        }
    }

    boolean checkWin(Player player, int x, int y) {

        for (int i = 0; i < size; i++) {
            if (grid[x][i].type != player.getName()) {
                break;
            }
            if (i == size - 1) {
                return true;
            }
        }

        for (int i = 0; i < size; i++) {
            if (grid[i][y].type != player.getName()) {
                break;
            }
            if (i == size - 1) {
                return true;
            }
        }

        if (x == y) {
            for(int i = 0; i < size; i++){
                if(grid[i][i].type != player.getName())
                    break;
                if(i == size - 1){
                    return true;
                }
            }
        }

        return false;
    }


}

class Player {
    String name;
    boolean myTurn = false;
    String getName() {return name;}
    void setName(String n) {name=n;}
    void setTurn(boolean b) {myTurn = b;}
    boolean getTurn() {return myTurn;}
}

class Token {
    String type;
    Token() {
        type = " ";
    }
    Token(String tokenType) {
        type = tokenType;
    }
}

class TokenFactory {
    Token makeToken(String type) throws Exception {
        if (type.equalsIgnoreCase("o")) {
            return new Token("o");
        } else if (type.equalsIgnoreCase("x")) {
            return new Token("x");
        } else {
            throw new Exception("Token not made, invalid type.");
        }
    }
}
