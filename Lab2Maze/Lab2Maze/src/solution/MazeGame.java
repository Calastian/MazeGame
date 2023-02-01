package solution;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;



/**
 * A maze game.
 * 
 * @author
 * @version
 *
 */
public class MazeGame
{
    /**
     * The size of each side of the game map.
     */
    private final static int HEIGHT = 19;
    private final static int WIDTH = 39;

    /**
     * The game map, as a 2D array of ints.
     */
    private boolean[][] blocked;
    
    //for bread crumbs
    private boolean[][] visited;
    
    /**
     * The current location of the player vertically.
     */
    // TODO: add field here.
    private int userCol;
    /**
     * The current location of the player horizontally.
     */
    // TODO: add field here.
    private int userRow;
    /**
     * The scanner from which each move is read.
     */
    // TODO: add field here.
    private Scanner moveScanner;
    /**
     * The row and column of the goal.
     */
    // TODO: add fields here.
    private int goalRow;
    private int goalCol;
    /**
     * The row and column of the start.
     */
    // TODO: add fields here.
    private int startRow;
    private int startCol;
    /**
     * Constructor initializes the maze with the data in 'mazeFile'.
     * @param mazeFile the input file for the maze
     */
    public MazeGame(String mazeFile)
    {
        // TODO
        loadMaze(mazeFile);
        this.moveScanner = new Scanner(System.in);
    }

    /**
     * Constructor initializes the maze with the 'mazeFile' and the move 
     * scanner with 'moveScanner'.
     * @param mazeFile the input file for the maze
     * @param moveScanner the scanner object from which to read user moves
     */
    public MazeGame(String mazeFile, Scanner moveScanner)
    {
    	// TODO
        loadMaze(mazeFile);
        this.moveScanner = new Scanner(System.in);
        this.moveScanner = moveScanner;
    }

    /**
     * getMaze returns a copy of the current maze for testing purposes.
     * 
     * @return the grid
     */
    public boolean[][] getMaze()
    {
        if (blocked == null)
        {
            return null;
        }
        else{
        boolean[][] copy = new boolean[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                copy[i][j] = blocked[i][j];
            }
        }
        return copy;
    }
    }

    /**
     * setMaze sets the current map for testing purposes.
     * 
     * @param maze
     *            another maze.
     */
    public void setMaze(boolean[][] maze)
    {
        this.blocked = maze;
    }
    /**
     * This is sets the user Columns.
     * @param userCol
     */
    public void setUserCol(int userCol)
    {
        this.userCol = userCol;
    }
    /**
     * This sets the User Row
     * @param userRow
     */
    public void setUserRow(int userRow)
    {
        this.userRow = userRow;
    }
    /**
     * This sets the move input from the scanner.
     * @param moveScanner
     */
    public void setMoveScanner(Scanner moveScanner)
    {
        this.moveScanner = moveScanner;
    }
    /**
     * this returns the User column.
     * @return Integer
     */
    public int getUserCol()
    {
        return userCol;
    }
    /**
     * This returns the users row.
     * @return
     */
    public int getUserRow()
    {
        return userRow;
    }
    /**
     * This returns the move Scanner.
     * @return
     */
    public Scanner getMoveScanner()
    {
        return moveScanner;
    }
   
    /**
     * Function loads the data from the maze file and creates the 'blocked' 
     * 2D array.
     *  
     * @param mazeFile the input maze file.
     */
    // TODO: private void loadMaze(String mazeFile)
    private void loadMaze(String mazeFile)
    {
        blocked = new boolean[HEIGHT][WIDTH];
        visited = new boolean[HEIGHT][WIDTH];
        try {
            FileReader file = new FileReader(mazeFile);
            Scanner input = new Scanner(file);
            for (int i = 0; i < HEIGHT; i++)
            {
                for (int j = 0; j < WIDTH; j++)
                {
                   char c = input.next().charAt(0);
                    
                   if (c == '1')
                    {
                        blocked[i][j] = true;
                    }
                    else if (c == '0')
                    {
                        blocked[i][j] = false;
                    }
                    else if(c == 'S')
                    {
                        blocked[i][j] = false;
                        this.startRow = i;
                        this.startCol = j;
                       
                    }
                    else if(c == 'G')
                    {
                        blocked[i][j] = false;
                        this.goalRow = i;
                        this.goalCol = j;
                    }
                }
            }
            input.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Actually plays the game.
     */
    public void playGame()
    {
        String inpuString = null;
        
        do
        {
            
            printMaze();
            System.out.println("Please make a move ethier (Down, Up, Left, Right)");
            inpuString = getMoveScanner().next();
            makeMove(inpuString);
            if (inpuString.equals("quit"))
            {
                break;
            }
            // boolean moveSuccess = makeMove(inpuString);
        }   
        while (!(playerAtGoal()) || inpuString.toLowerCase().charAt(0) == 'q');
        System.out.println("Well Done Completing the maze!");

        
    }

    /**
     * Checks to see if the player has won the game.
     * @return true if the player has won.
     */
    // TODO: public boolean playerAtGoal()
    public boolean playerAtGoal()
    {
        if (userCol == goalCol&& userRow == goalRow )
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    /**
     * Makes a move based on the String.
     * 
     * @param move
     *            the direction to make a move in.
     * @return whether the move was valid.
     */
    public boolean makeMove(String move)
    {
        
        // TODO
        if (move.toLowerCase().charAt(0) == 'u')
        {
            if (userRow > 0 && !(blocked[userRow - 1][userCol]))
            {
                visited[userRow][userCol] = true;
                userRow -= 1;
                
            }
            return true;
        }
        // == false && userRow < HEIGHT - 1
        else if (move.toLowerCase().charAt(0) == 'd')
        {
            if (userRow < HEIGHT - 1 && !(blocked[userRow + 1][userCol]))
            {
                visited[userRow][userCol] = true;
                userRow += 1;
            }
            return true;
        }
        else if (move.toLowerCase().charAt(0) == 'r')
        {
            if (userCol < WIDTH - 1 && !(blocked[userRow][userCol + 1]))
            {
                visited[userRow][userCol] = true;
                userCol += 1;
                
            }
            return true;
        }
        else if (move.toLowerCase().charAt(0) == 'l')
        {
            if (userCol > 0 && !(blocked[userRow][userCol - 1]))
            {
                visited[userRow][userCol] = true;
                userCol -= 1;
               
            }
            return true;
        }
        else if (move.toLowerCase().charAt(0) == 'q')
        {
            System.out.println("GoodBye!");
            return true;
        }
        else
        {
            return false;
        }
        
    
        
    }

    /**
     * Prints the map of the maze.
     */
    public void printMaze()
    {
        // TODO
        System.out.println("*---------------------------------------*");
        
        for (int i = 0; i < blocked.length; i++)
          {
            System.out.print('|');
            for (int j = 0; j < blocked[i].length; j++)
            {
              if(userRow == i && userCol == j)
              {
                  System.out.print('@');
              }
              else if (startRow == i && startCol == j)
              {
                  System.out.print('S');
                  
              }
              else if (goalRow == i  && goalCol == j) 
              {
                  System.out.print('G');
              }
              else if (blocked[i][j])
              {
                  System.out.print('X');
              }
              else if (!(visited[i][j]))
              {
                  System.out.print(' ');
              }
              else if (visited[i][j])
              {
                  System.out.print('.');
              }
              
                         
             }
            System.out.println('|');   
           }  
        System.out.println("*---------------------------------------*");
     }
       
        
    

    /**
     * Creates a new game, using a command line argument file name, if one is
     * provided.
     * 
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        String mapFile = "Lab2Maze/data/easy.txt";
        Scanner scan = new Scanner(System.in);
        MazeGame game = new MazeGame(mapFile, scan);
        game.playGame();
        
    }
}
