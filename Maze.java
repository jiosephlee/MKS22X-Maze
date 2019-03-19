import java.util.*;
import java.io.*;
public class Maze{

    private char[][]maze;
    private boolean animate;//false by default
    private int[][] moves;
    private int[] s;
    private int path;
    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public static void main(String[] args) throws FileNotFoundException{
        Maze test = new Maze ("Mazel.txt");
        System.out.println(test.s[0] + "," + test.s[1]);
        System.out.println(test.solve());
        System.out.println(test);

    }
    public Maze(String filename) throws FileNotFoundException{
        File text = new File(filename);
        // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"

        //inf stands for the input file
        Scanner inf = new Scanner(text);
        int x = 0;
        int y = 0;
        while(inf.hasNextLine()){
            String line = inf.nextLine();
            x++;
            y = line.length();
        }
        maze = new char[x][y];
        //put into array
        inf = new Scanner(text);
        for (int xcor = 0; inf.hasNextLine(); xcor++){
            String line = inf.nextLine();
            for (int ycor = 0; ycor < line.length(); ycor++){
                maze[xcor][ycor] = line.charAt(ycor);
                if (line.charAt(ycor) == 'S') s = new int[] {xcor,ycor};
            }
        }
        animate = false;
        moves = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
        path = 0;
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }

    public String toString(){
        String output = "";
        for(char[] i: maze){
            for (char j: i){
                output += j + " ";
            }
            output += '\n';
        }
        return output;
    }
    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
            //find the location of the S.
            maze[s[0]][s[1]] = ' ';
            //erase the S

            //and start solving at the location of the s.
            return solve(s[0],s[1],0);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col, int level){ //you can add more parameters since this is private
        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(50);
        }

        //COMPLETE SOLVE
        //if reached the end,  then return 1
        if (maze[row][col] == 'E') return 1;
        if (maze[row][col] != ' ') return -1;

        maze[row][col] = '@';
        path++;
        //if not, branch out to next possible paths
        for (int[] i : moves){
            //System.out.println("yo");
            //check if the next move is within bounds and then branch out.
            //if that brach works then this branch works as well --> return true
            if(solve(row + i[0], col + i[1], level + 1) != -1){
                return path;
            }
        }
        //if the branch fails, reset the spot to 0 and move on to other brances
        maze[row][col] = '.';
        path--;
        return -1; //so it compiles
    }
}
