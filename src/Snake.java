import java.util.*;

/**
 * Created by matth on 20.04.2017.
 */
public class Snake {
    public static void main(String[] args) {
        // TODO read cases from System.in

        String[] food = {"1 1 2 3","4 4 1 1"};
        Queue<Character> instr = new LinkedList<>();
        instr.add('F');
        instr.add('L');
        instr.add('F');
        instr.add('R');
        instr.add('R');
        instr.add('F');
        instr.add('F');
        SnakeGame game = new SnakeGame(4, 2, 3, instr, food);
        game.printGameBoard();
        game.runGame();
    }
}

class SnakeGame {
    int score = 0, numSteps = 0;
    private Tile[][] gameBoard;
    private Queue<Tile> snake;
    private int headCol, headLine;
    private SnakeDir snakeDir = SnakeDir.R;
    private Queue<Character> instructions;

    public SnakeGame(int gridSize, int startCol, int startLine, Queue<Character> instructions, String[] foodBlocks) {
        this.gameBoard = new Tile[gridSize][gridSize];
        this.instructions = instructions;

        // fill gameBoard with tiles
        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard[0].length; y++) {
                gameBoard[x][y] = new Tile();
            }
        }

        // place food
        for (String foodBlock : foodBlocks) {
            String[] blockStrArray = foodBlock.split(" ");
            int[] block = new int[4];
            for (int i = 0; i < 4; i++) {
                block[i] = Integer.parseInt(blockStrArray[i]);
            }

            for (int x = block[0]-1; x < block[0]-1+block[2]; x++) {
                for (int y = block[1]-1; y<block[1]-1+block[3]; y++) {
                    gameBoard[x][y].hasFood = true;
                }
            }
        }

        // place snake
        snake = new LinkedList<>();
        headCol = startCol-1;
        headLine = startLine-1;
        snake.add(gameBoard[headCol][headLine]);
        gameBoard[headCol][headLine].hasFood = false;
    }

    public void runGame() {
        int numInstructions = instructions.size();
        for (int i = 0; i < numInstructions; i++) {
            switch (instructions.remove()) {
                case 'L':
                    snakeDir = snakeDir.turnLeft();
                    System.out.println("turning left");
                    break;
                case 'R':
                    snakeDir = snakeDir.turnRight();
                    System.out.println("turning right");
                    break;
            }
            if (!moveForward()) { // terminate when snake hits obstacle
                return;
            }
            printGameBoard();
        }
    }

    public void printGameBoard() {
        for (int y = 0; y < gameBoard[0].length; y++) {
            for (int x = 0; x < gameBoard.length; x++) {
                if (snake.contains(gameBoard[x][y])) {
                    System.out.print("S ");
                    if (gameBoard[x][y].hasFood) {
                        System.err.println("snaketile has food");
                        System.exit(1);
                    }
                }else if(gameBoard[x][y].hasFood) {
                    System.out.print("\u25A0 ");
                }else {
                    System.out.print("\u25A1 ");
                }
            }
            System.out.println();
        }
        System.out.println(numSteps + " " + score);
    }

    /**
     * moves snake one tile forwards
     * @return true if successful; false if snake hits an obstacle
     */
    private boolean moveForward() {
        Tile tileToMoveTo;
        switch (snakeDir) {
            case U:
                headLine = headLine - 1;
                break;
            case R:
                headCol = headCol + 1;
                break;
            case D:
                headLine = headLine + 1;
                break;
            case L:
                headCol = headCol - 1;
                break;
        }
        try {
            tileToMoveTo = gameBoard[headCol][headLine];
        } catch(ArrayIndexOutOfBoundsException e) { // snake hits wall
            return false;
        }
        if (snake.contains(tileToMoveTo)) { // snake hits itself
            return false;
        }
        snake.add(tileToMoveTo);
        if (tileToMoveTo.hasFood) {
            score++;
            tileToMoveTo.hasFood = false;
        } else {
            snake.remove();
        }
        numSteps++;
        return true;
    }

    private class Tile {
        boolean hasFood = false;
    }

    private enum SnakeDir {
        U, L, D, R;
        private static SnakeDir[] vals = values();

        public SnakeDir turnRight() {
            switch(this) {
                case U: return R;
                case L: return U;
                case D: return L;
                case R: return D;
            }
            throw new RuntimeException("snake direction invalid");
        }

        public SnakeDir turnLeft() {
            switch(this) {
                case U: return L;
                case L: return D;
                case D: return R;
                case R: return U;
            }
            throw new RuntimeException("snake direction invalid");
        }
    }
}
