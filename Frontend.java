// --== CS400 File Header Information ==--
// Name: Aidan Lonergan
// Email: alonergan@wisc.edu
// Team: blue
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
    
    private static String detailsHeader =
        "=======================\n"
      + "     Movie Details     \n"
      + "=======================\n";        

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
            // app loop
            while (!done) {
                // print three movies and commands
                printBaseMenu();
                String input = scnr.next();
                switch (input) {
                    case "1":                
                    case "2":
                    case "3":
                        // get first three movies at index of input
                        // the first element will be the movie selected
                        List<MovieInterface> movies = b.getThreeMovies((Integer.parseInt(input) - 1));
                        String movieDetails = movies.get(0).toString();
                        while (!input.contentEquals("x")) {
                            // print details
                            System.out.print(detailsHeader);
                            System.out.println(movieDetails);
                            System.out.println("Enter 'x' to exit to return to base mode");
                            input = scnr.next();                            
                        }
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
                    default:
                        System.out.println("That is not a valid command");
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
    public static void printBaseMenu() {
        // print header
        System.out.print(baseHeader);
        
        // print top 3 movies
        List<MovieInterface> movies = b.getThreeMovies(0);
        System.out.println("#\tMovie Name\tGenres\tRating");
        for (int i = 0; i < movies.size(); i++) {
            String name = movies.get(i).getTitle();
            List<String> genreList = movies.get(i).getGenres();
            String genres = "";
            for (int j = 0; j < genreList.size(); j++) {
                genres = genres + genreList.get(j) + ", "; // TODO fix formatting
            }
            double rating = (double) movies.get(i).getAvgVote();
            System.out.println("Printing movie");
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
        for (int i = 0; i < allRatings.size(); i++) {
            for (int j = 0; j < selRatings.size(); j++) {
                String selected = selRatings.get(j);
                int selRating = (int) Double.parseDouble(selected);
                if (selRating >= i && selRating <= (i + .99)) {
                    System.out.println((i + 1) + ". [x] " + allRatings.get(i));                   
                } else {
                    System.out.println((i + 1) + ". [ ] " + allRatings.get(i));
                }
            }
        }
        
        // print commands
        System.out.println("Enter 'x' to return to base mode");
        System.out.println("Enter a number to select/deselect ratings");
        
    }
}
