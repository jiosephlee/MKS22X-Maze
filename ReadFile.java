import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ReadFile {
  public static void main(String args[]) throws FileNotFoundException {
        //instead of a try/catch, you can throw the FileNotFoundException.
        //This is generally bad behavior

        File text = new File("Mazel.txt");
        // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"

        //inf stands for the input file
        Scanner inf = new Scanner(text);
        int x = 0;
        int y = 0;
        while(inf.hasNextLine()){
            String line = inf.nextLine();
            x++;
            y = line.length();
            System.out.println(line);//hopefully you can do other things with the line
        }
        char[][] maze = new char[x][y];
        //put into array
        inf = new Scanner(text);
        for (int xcor = 0; inf.hasNextLine(); xcor++){
            String line = inf.nextLine();
            for (int ycor = 0; ycor < line.length(); ycor++){
                maze[xcor][ycor] = line.charAt(ycor);
            }
        }
        //print array
        for (char[] i : maze){
            for (char j : i){
                System.out.print(j);
            }
            System.out.println();
        }
    }
}
