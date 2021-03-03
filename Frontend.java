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
     * Main method for running the program
     * @param args      
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
                if (input.isBlank() || input != "x") {
                    printBaseMenu(1);
                } else {
                    printBaseMenu(Integer.parseInt(input));
                }
                input = scnr.next();
                switch (input) {
                    default:
                        int inputInt = Integer.parseInt(input);
                        if (inputInt < 0 || inputInt > (b.getNumberOfMovies() - 1)) {
                            System.out.println("That is not a valid command");
                            break;
                        }
                        printBaseMenu(inputInt);
                        break;
                    case "g":
                        printGenreMenu();
                        input = scnr.next();
                        while (!input.contentEquals("x")) {
                            // get selected genre
                            List<String> allGenres = b.getAllGenres();
                            List<String> selGenres = b.getGenres();
                            int i = Integer.parseInt(input) + 1;
                            String currGenre = allGenres.get(i);
                            // loop through selected genre list
                            for (int j = 0; j < selGenres.size(); j++) {
                                // if in the selected list deselect otherwise add to selected list
                                if (currGenre.equalsIgnoreCase(selGenres.get(j))) {
                                    b.removeGenre(currGenre);
                                } else {
                                    b.addGenre(currGenre);
                                }
                            }
                            printGenreMenu();
                            input = scnr.next();   
                        }
                        break;
                    case "r":
                        printRatingsMenu();
                        input = scnr.next();
                        while (!input.contentEquals("x")) {
                            List<String> selRatings = b.getAvgRatings();
                            int i = (int) Double.parseDouble(input);
                            String currRating = allRatings.get(i);
                            // loop through selected genre list
                            for (int j = 0; j < selRatings.size(); j++) {
                                // if in the selected list deselect otherwise add to selected list
                                if (1 > 2 ) { // FIXME
                                    b.removeAvgRating(input);
                                } else {
                                    b.addAvgRating(input);
                                }
                            }
                            printGenreMenu();
                            input = scnr.next();   
                        }
                        break;
                    case "x":
                        System.out.println("Goodbye!");
                        scnr.close();
                        done = true;
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
            System.out.println(i + "\t" + name + "\t" + genres + "\t" + rating);                 
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
        for (int i = 0; i < allGenres.size(); i++) {
            for (int j = 0; j < selGenres.size(); j++) {
                if (allGenres.get(i).contains(selGenres.get(j))) {
                    System.out.println((i + 1) + ". [x] " + allGenres.get(i));                   
                } else {
                    System.out.println((i + 1) + ". [ ] " + allGenres.get(i));
                }
            }
        }
        
        // print commands
        System.out.println("Enter 'x' to return to base mode");
        System.out.println("Enter a number to select/deselect genres");
               
    }

    /**
     * ------------ NEED TO FINISH ------------
     * Prints the ratings menu with the selected ratings
     */
    public static void printRatingsMenu() {
        // print header
        System.out.print(ratingsHeader);
        
        // print selected ratings
        List<String> selRatings = b.getAvgRatings();
        if (selRatings.size() == 0) {
            // if nothing selected print empty menu
            for (int x = 0; x < allRatings.size(); x++) {
                System.out.println((x) + ". [ ] " + allRatings.get(x));
            }
            System.out.println("Enter 'x' to return to base mode");
            System.out.println("Enter a number to select/deselect ratings");
            return;
        }
        for (int i = 0; i < allRatings.size(); i++) {
            for (int j = 0; j < selRatings.size(); j++) {
                String selected = selRatings.get(j);
                int selRating = (int) Double.parseDouble(selected);
                if (selRating >= i && selRating <= (i + .99)) {
                    System.out.println(i + ". [x] " + allRatings.get(i));                   
                } else {
                    System.out.println(i + ". [ ] " + allRatings.get(i));
                }
            }
        }
        
        // print commands
        System.out.println("Enter 'x' to return to base mode");
        System.out.println("Enter a number to select/deselect ratings");
        
    }
}
