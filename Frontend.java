// --== CS400 File Header Information ==--
// Name: Aidan Lonergan
// Email: alonergan@wisc.edu
// Team: red
// Role: Front End Developer
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: Implementation is not currently finished
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Frontend {
    
    private static String baseHeader =
        "=======================\n"
      + "Welcome to Movie Mapper\n"
      + "=======================\n";

    private static String genreHeader =
        "=======================\n"
      + "  Genre Selection Mode \n"
      + "=======================\n"
      + "Selected genres are marked with a [x]\n";

    private static String ratingsHeader =
        "=======================\n"
      + " Rating Selection Mode \n"
      + "=======================\n"
      + "Selected ratings are marked with a [x]\n";      

    private static Backend b;
    
    private static List<String> allRatings;
    
    /**
     * main method for running the program with a while loop and switch statement
     *     
     */
    public static void main(String[] args) {
        // initialize input
        Scanner scnr = new Scanner(System.in);
        boolean done = false;
        BufferedReader r;
        // populate allRatings list
        allRatings = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            allRatings.add(i + ".0 - " + i + ".99");
        }

        try {
            r = new BufferedReader(new FileReader("src/movies.csv"));
            b = new Backend(r);
            String input = "";
            // app loop
            while (!done) {
                // if no input print default screen
                if (input.isBlank() || input.contentEquals("x")) {
                    printBaseMenu(1);
                }
                input = scnr.next();
                switch (input) {

                    case "g":
                        printGenreMenu();
                        input = scnr.next();
                        while (!input.contentEquals("x")) {
                            // get selected genre
                            List<String> allGenres = b.getAllGenres();
                            List<String> selGenres = b.getGenres();
                            int i = Integer.parseInt(input) - 1;
                            String currGenre = allGenres.get(i);
                            
                            // loop through selected rating list 
                            if (selGenres.size() == 0 || selGenres == null) {
                                b.addGenre(currGenre);
                                printGenreMenu();
                                input = scnr.next();
                                continue;
                            }
                            
                            // if in the selected list deselect otherwise add to selected list
                            if (b.containsGenre(currGenre)) {
                                b.removeGenre(currGenre);
                            } else {
                                b.addGenre(currGenre);
                            }  
                            
                            printGenreMenu();
                            input = scnr.next();   
                        }
                        break;
                        
                    case "r":
                        printRatingsMenu();
                        input = scnr.next();
                        while (!input.contentEquals("x")) {
                         // get selected rating from list and get selected rating
                            List<String> selRatings = b.getAvgRatings();                            
                            int i = (int) Double.parseDouble(input);
                            String rating = allRatings.get(i);
                            char char0 = rating.charAt(0);
                            String chosenRating = String.valueOf(char0);
                         // loop through selected rating list 
                            if (selRatings.size() == 0 || selRatings == null) {
                                b.addAvgRating(input);
                                printRatingsMenu();
                                input = scnr.next();
                                continue;
                            }
                            
                            // if in the selected list deselect otherwise add to selected list
                            if (b.containsRating(chosenRating)) {
                                b.removeAvgRating(input);
                            } else {
                                b.addAvgRating(input);
                            }                                
                            
                            printRatingsMenu();
                            input = scnr.next();   
                        }
                        break;
                        
                    case "x":
                        System.out.println("Goodbye!");
                        scnr.close();
                        done = true;
                        break;
                        
                    default:                        
                        int inputInt = Integer.parseInt(input);
                        if (inputInt < 0 || inputInt > (b.getNumberOfMovies() - 1)) {
                            System.out.println("That is not a valid command");
                            break;
                        }
                        printBaseMenu(inputInt);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error parsing file");
        }
    }

    /**
     * Prints the base menu for the program
     */
    public static void printBaseMenu(int index) {
        // test--
        List<MovieInterface> movies1 = b.getThreeMovies((5));
        List<MovieInterface> movies2 = b.getThreeMovies((10));

        // print header
        System.out.print(baseHeader);
        
        // print top 3 movies
        List<MovieInterface> movies = b.getThreeMovies((index));
        System.out.println("#\tName\t\tGenres\t\tRating");
        for (int i = 0; i < movies.size(); i++) {
            String name = movies.get(i).getTitle();
            List<String> genreList = movies.get(i).getGenres();
            String genres = "";
            for (int j = 0; j < genreList.size(); j++) {
                genres = genres + genreList.get(j) + " "; // TODO fix formatting
            }
            double rating = (double) movies.get(i).getAvgVote();
            System.out.println(index + "\t" + name + "\t" + genres + "\t" + rating);
            index++;
        }
        
        // print commands
        System.out.println("Enter a number to browse movies");
        System.out.println("Enter 'g' to enter Genre Selection mode");
        System.out.println("Enter 'r' to enter Rating Selection mode");
        System.out.println("Enter 'x' to exit the program");
        
    }

    /**
     * Prints the genre menu with selected genres
     */
    public static void printGenreMenu() {
        // print header
        System.out.print(genreHeader);
        
        // print selected genres
        List<String> allGenres = b.getAllGenres();
        List<String> selGenres = b.getGenres();
        int numSel = selGenres.size();
        int numGenres = allGenres.size();
        for (int i = 0; i < allGenres.size(); i++) {
            if (b.containsGenre(allGenres.get(i))) {
                System.out.println((i + 1) + ". [x] " + allGenres.get(i));
                continue;
            } else {
                System.out.println((i + 1) + ". [ ] " + allGenres.get(i));
                continue;
            }
            
        }
        
        // print commands
        System.out.println("Enter 'x' to return to base mode");
        System.out.println("Enter a number to select/deselect genres");
               
    }

    /**
     * 
     * Prints the ratings menu with the selected ratings
     */
    public static void printRatingsMenu() {
        // print header
        System.out.print(ratingsHeader);        
        List<String> selRatings = b.getAvgRatings();
        // if nothing selected print empty menu
        if (selRatings.size() == 0) {
            for (int x = 0; x < allRatings.size(); x++) {
                System.out.println((x) + ". [ ] " + allRatings.get(x));
            }
            System.out.println("Enter 'x' to return to base mode");
            System.out.println("Enter a number to select/deselect ratings");
            return;
        }
        // print selected ratings
        for (int i = 0; i < allRatings.size(); i++) {
            for (int j = 0; j < selRatings.size(); j++) {
                // get the value of the rating at index i
                String rating = allRatings.get(i);
                char char0 = rating.charAt(0);
                String iRating = String.valueOf(char0);
                
                if (b.containsRating(iRating)) {
                    System.out.println(i + ". [x] " + allRatings.get(i));
                    break;
                } else {
                    System.out.println(i + ". [ ] " + allRatings.get(i));
                    break;
                }
            }
        }
        
        // print commands
        System.out.println("Enter 'x' to return to base mode");
        System.out.println("Enter a number to select/deselect ratings");
        
    }
}
