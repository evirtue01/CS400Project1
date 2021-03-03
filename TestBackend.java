// --== CS400 File Header Information ==--
// Name: Ethan McKellips
// Email: emckellips@wisc.edu
// Team: Red
// Group: IG
// TA: Sid
// Lecturer: Florian
// Notes to Grader: N/A

// Imports
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * This serves as the dummy class that tests the backend functionality
 * @author Ethan McKellips
 *
 */
public class TestBackend {

	/**
	 * Runs every test
	 * @param args
	 */
    public static void main(String[] args) {
    
       try {
    	   (new TestBackend()).runTests();
       } catch (IOException e) {
    	   e.printStackTrace();
       }
    }

    /**
     * Runs every test
     * @throws IOException
     */
    public void runTests() throws IOException {
        // add all tests to this method
        if (this.testInitialNumberOfMovies()) {
            System.out.println("Test initial number of movies: PASSED");
        } else {
            System.out.println("Test initial number of movies: FAILED");
        }
        if (this.testInitialNumberOfMovies()) {
            System.out.println("Test get all genres: PASSED");
        } else {
            System.out.println("Test get all genres: FAILED");
        }
        if (this.testGetThreeMoviesInitial()) {
            System.out.println("Test get three movies sorted by rating: PASSED");
        } else {
            System.out.println("Test get three movies sorted by rating: FAILED");
        }
        if (this.testAddGenre()) {
            System.out.println("Test add genre: PASSED");
        } else {
            System.out.println("Test add genre: FAILED");
        }
        if (this.testAddAvgRating()) {
            System.out.println("Test add average ratings: PASSED");
        } else {
            System.out.println("Test add average rating: FAILED");
        }
        if (testRemoveGenre()) {
            System.out.println("Test remove genre: PASSED");
        } else {
            System.out.println("Test remove genre: FAILED");
        }
        if (testRemoveAvgRating()) {
            System.out.println("Test remove average ratings: PASSED");
        } else {
            System.out.println("Test remove average rating: FAILED");
        }
        if (testGetGenres()) {
            System.out.println("Test get genres : PASSED");
        } else {
            System.out.println("Test get genres: FAILED");
        }
        if (testGetAvgRatings()) {
            System.out.println("Test get average ratings: PASSED");
        } else {
            System.out.println("Test get average rating: FAILED");
        }
        if (testStringConstructor()) {
        	System.out.println("Test string constructor: PASSED");
        } else {
        	System.out.println("Test string constructor: FAILED");
        }
    }

    /**
             * This test instantiates the back end with three movies and tests whether the
             * initial selection is empty (getNumberOfMovies() returns 0). It passes when
             * 0 is returned and fails in all other cases, including when an exception is
             * thrown.
             * @return true if the test passed, false if it failed
             */
            public boolean testInitialNumberOfMovies() {
                    try {
                            // instantiate once BackendInterface is implemented
                        BackendInterface backendToTest = new Backend(new StringReader(
                            "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                                + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
                                + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                                + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));

                            if (backendToTest.getNumberOfMovies() == 3) {
                                    // test passed
                                    return true;
                            } else {
                                // test failed
                                return false;
}
                        }catch(Exception e) {
                    e.printStackTrace();
                    // test failed
                    return false;
    }
    }
            
            public boolean testStringConstructor() throws IOException {
            	String input = "The Martian, 2016, \"Sci Fi\", Ridley Scott, A team and his crew need return to a lost astronaut alone in space, 7.3";
            	// Test 1 - tests that no errors are made
            	BackendInterface backendToTest = new Backend(input);
            	
            	
            	return true;
            }

    /**
     * This test instantiates the back end with three movies and tests whether
     * the getAllGenres method return the correct set of genres for those three
     * movies.
     * @return true if the test passed, false if it failed
     */
    public boolean testGetAllGenres() {
        try {
            // instantiate once BackendInterface is implemented
            BackendInterface backendToTest = new Backend(new StringReader(
                "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote \n"
                    + "The Source of Shadows,The Source of Shadows, 2020, Horror, 83, USA, English, n \"Ryan Bury, Jennifer Bonior\", \"Jennifer Bonior, Trevor Botkin\", Four Thieves Productions, \"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\", \"A series of stories woven together by one of our most primal fears, t he fear of the unknown.\", 3.5\n"
                    + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                    + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));

            if (backendToTest.getAllGenres().size() == 5
                && backendToTest.getAllGenres().contains("Horror")
                && backendToTest.getAllGenres().contains("Action")
                && backendToTest.getAllGenres().contains("Comedy")
                && backendToTest.getAllGenres().contains("Musical")
                && backendToTest.getAllGenres().contains("Romance")) {// test passed
                return true;
            } else {
                // test failed
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            return false;
        }
    }


    /**
     * This test instantiates the back end with three movies and tests whether the
     * initial list returned by getThreeMovies starting with the first movie (0)
     * is empty. It passes when 0 is returned and fails in all other cases, including
     * when an exception is thrown.
     * @return true if the test passed, false if its failed
     */
    public boolean testGetThreeMoviesInitial() {
        try {
            BackendInterface backendToTest = new Backend(new StringReader(
                "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                    + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
                    + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                    + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));

            if (backendToTest.getThreeMovies(0).size() == 0) {
                // test passed
                return true;
            } else {
                // test failed
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            return false;
        }
    }


    /**
     * Tests the functionality of the addGenre() method in the backend
     * @return true if true, false if not
     */
    public boolean testAddGenre() {
        try {
            BackendInterface backendToTest = new Backend(new StringReader(
                "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                    + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
                    + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                    + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));


            backendToTest.addGenre("Romance");
            if (!backendToTest.getGenres().contains("Romance")) {
                System.out.println("Test 1 failed");
                return false;
            }
            if (backendToTest.getGenres().size() != 1) {
                System.out.println("Test 2 failed");
                return false;
            }
            backendToTest.addGenre("Western");
            if (!backendToTest.getGenres().contains("Western")) {
            	System.out.println("Test 3 failed");
                return false;
            }
            if (backendToTest.getGenres().size() != 2) {
            	System.out.println("Test 4 failed");
                return false;
            }
            backendToTest.addGenre("Documentary");
            if (!backendToTest.getGenres().contains("Documentary")) {
            	System.out.println("Test 5 failed");
                return false;
            }
            if (backendToTest.getGenres().size() != 3) {
            	System.out.println("Test 6 failed");
                return false;
            }
            backendToTest.addGenre("Romance");
            if (backendToTest.getGenres().size() != 3) {
                System.out.println("Test 7 failed");
                return false;
            }
            backendToTest.addGenre(null);
            if (backendToTest.getGenres().size() != 3) {
            	System.out.println("Test 8 failed");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            return false;
        }
        // all tests passed
        return true;
    }

    /**
     * Tests the functionality of the testAddAvgRating functionality
     * @return true if all tests pass, false otherwise
     * @throws IOException
     */
    public boolean testAddAvgRating() throws IOException {
        Backend backendToTest = new Backend(new StringReader(
            "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
                + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));

        try {
            if (backendToTest.getAvgRatings().size() != 0) {
                System.out.println("Test 1 failed");
                return false;
            }
            backendToTest.addAvgRating("7");
            if (backendToTest.getAvgRatings().size() != 1 || !backendToTest.containsRating("7")) {
                System.out.println("Test 2 failed");
                return false;
            }
            backendToTest.addAvgRating("5");
            if (backendToTest.getAvgRatings().size() != 2 || !backendToTest.containsRating("5")) {
            	System.out.println("Test 3 failed");
                return false;
            }
            backendToTest.addAvgRating("10");
            if (backendToTest.getAvgRatings().size() != 3 || !backendToTest.containsRating("10")) {
            	System.out.println("Test 4 failed");
                return false;
            }
            backendToTest.addAvgRating(null);
            if (backendToTest.getAvgRatings().size() != 3) {
            	System.out.println("Test 5 failed");
            	return false;
            }
            backendToTest.addAvgRating("8");
            if (backendToTest.getAvgRatings().size() != 4 || !backendToTest.containsRating("8")) {
            	System.out.println("Test 6 failed");
                return false;
            }
            backendToTest.addAvgRating("1.875");
            if (backendToTest.getAvgRatings().size() != 5 || !backendToTest.containsRating("1")) {
            	System.out.println("Test 7 failed");
                return false;
            }
            backendToTest.addAvgRating("2.483");
            if (backendToTest.getAvgRatings().size() != 6 || !backendToTest.containsRating("2")) {
            	System.out.println("Test 8 failed");
                return false;
            }
            // adds an AvgRating that is already within the list ensures the method returns false
            backendToTest.addAvgRating("9.48");
            if (backendToTest.getAvgRatings().size() != 7 || !backendToTest.containsRating("9")) {
            	System.out.println("Test 9 failed");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // test failed
            return false;
        }
        // all tests passed
        return true;
    }

    /**
     * Test that tests if the functionality of the 
     * @return
     * @throws IOException
     */
    public boolean testContainsGenre() throws IOException {
        Backend backendToTest = new Backend(new StringReader(
            "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
                + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));


        // test to see if genre that is not within emptylist is returned false
        if (backendToTest.containsGenre("Romance")) {
            return false;
        }

        backendToTest.addGenre("Romance");
        backendToTest.addGenre("Western");


        if (!backendToTest.containsGenre("Romance")) {
            return false;
        }

        if (!backendToTest.containsGenre("Western")) {
            return false;
        }
        backendToTest.addGenre("Science Fiction");
        
        if (!backendToTest.containsGenre("Science Ficiton")) {
            return false;
        }
        
        backendToTest.addGenre("Drama");

        if (!backendToTest.containsGenre("Drama")) {
            return false;
        }
        

        backendToTest.addGenre("Comedy");

        if (!backendToTest.containsGenre("Comedy")) {
            return false;
        }
        backendToTest.addGenre("Musical");

        if (!backendToTest.containsGenre("Musical")) {
            return false;
        }

        backendToTest.addGenre("Thriller");

        if (!backendToTest.containsGenre("Thriller")) {
            return false;
        }

        // passes null parameter through containsGenre
        if (!backendToTest.containsGenre(null)) {
            return false;
        }

        // adds genre that is not in list and ensures it returns false
        if (backendToTest.containsGenre("Country")) {
            return false;
        }

        return true;
    }

    public boolean testContainsRating() throws IOException {
        Backend backendToTest = new Backend(new StringReader(
            "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
                + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));


        // checks if containsRating returns false on empty list
        if (backendToTest.containsRating("-1")) {
            return false;
        }
        // passes 3 positive integers to method and checks if contains rating returns true
        backendToTest.addAvgRating("1");
        backendToTest.addAvgRating("2");
        backendToTest.addAvgRating("3");

        if (!backendToTest.containsRating("1")) {
            return false;
        }
        if (!backendToTest.containsRating("2")) {
            return false;
        }
        if (!backendToTest.containsRating("3")) {
            return false;
        }
        // passes a double type as a string and ensures only the first number is collected as avg
        // rating
        backendToTest.addAvgRating("6.8");
        backendToTest.addAvgRating("7.2");
        backendToTest.addAvgRating("2.5");

        if (!backendToTest.containsRating("6") || backendToTest.containsRating("6.8")) {
            return false;
        }
        if (!backendToTest.containsRating("7") || backendToTest.containsRating("7.2")) {
            return false;
        }
        if (!backendToTest.containsRating("2") || backendToTest.containsRating("2.5")) {
            return false;
        }

        // checks that ratings outside of 0-10(inclusive are not added to list
        backendToTest.addAvgRating("-8");
        backendToTest.addAvgRating("-5.35");
        backendToTest.addAvgRating("12");


        if (backendToTest.containsRating("-8")) {
            return false;
        }
        if (backendToTest.containsRating("-5")) {
            return false;
        }
        if (backendToTest.containsRating("12")) {
            return false;
        }
        return true;
    }

    public boolean testRemoveGenre() throws IOException {
        Backend backendToTest = new Backend(new StringReader(
			    "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
			        + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
			        + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
			        + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));
		

        String[] testGenres = new String[] {"Romance", "Western", "Musical", "Thriller", "Horror",
            "War", "Fiction", "Animation", "Noir", "Historical Drama", "Epic", "Short", "Sports",
            "Mystery", "Fantasy", "Biographical", "Documentay", "Indie", "SuperHero", "Science",
            "Silent", "Satire", "Parody Film"};

        // adds a collection of genres to list
        for (int i = 0; i < testGenres.length; i++) {
            backendToTest.addGenre(testGenres[i]);
        }

        backendToTest.removeGenre("Epic");
        if (backendToTest.containsGenre("Epic")) {
            return false;
        }
        backendToTest.removeGenre("Science");
        if (backendToTest.containsGenre("Science")) {
            return false;
        }

        backendToTest.removeGenre("Horror");
        if (backendToTest.containsGenre("Horror")) {
            return false;
        }

        backendToTest.removeGenre("Fantasy");
        if (backendToTest.containsGenre("Fantasy")) {
            return false;
        }

        // passes null genre as parameter to remove
        try {
            backendToTest.removeGenre(null);
        } catch (Exception e) {

        }

        return true;
    }

    /**
     * Tests the functionality of the removeAvgRating() method in the backend class
     * @return true if all tests pass, false otherwise
     * @throws IOException
     */
    public boolean testRemoveAvgRating() throws IOException {
        Backend backendToTest = new Backend(new StringReader(
			    "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
			        + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
			        + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
			        + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));
		

        String[] testRatings =
            new String[] {"4", "7", "9", "3", "2", "5.7", "6.4", "1.6", "10", "0.6"};

        // adds a collections of ratings to list
        for (int i = 0; i < testRatings.length; i++) {
            backendToTest.addAvgRating(testRatings[i]);
        }

        backendToTest.removeAvgRating("4");
        if (backendToTest.containsRating("4")) {
            return false;
        }

        backendToTest.removeAvgRating("3");
        if (backendToTest.containsRating("3")) {
            return false;
        }

        backendToTest.removeAvgRating("9");
        if (backendToTest.containsRating("9")) {
            return false;
        }
        backendToTest.removeAvgRating("0");
        if (backendToTest.containsRating("0")) {
            return false;
        }

        // removes a rating that is null not in the list
        try {
            backendToTest.removeAvgRating(null);
        } catch (Exception e) {

        }

        // removes a rating that is not in the list
        try {
            backendToTest.removeAvgRating("8");
        } catch (Exception e) {

        }
        return true;

    }

    /**
     * Tests the functionality of the getGenres() method
     * @return true if all tests pass, false otherwise
     * @throws IOException
     */
    public boolean testGetGenres() throws IOException {
        Backend backendToTest = new Backend(new StringReader(
            "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
                + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));


        String[] testGenres = new String[] {"Romance", "Western", "Musical", "Thriller", "Horror",
            "War", "Fiction", "Animation", "Noir", "Historical Drama", "Epic", "Short", "Sports",
            "Mystery", "Fantasy", "Biographical", "Documentary", "Indie", "SuperHero", "Science",
            "Silent", "Satire", "Parody Film"};

        for (int i = 0; i < testGenres.length; i++) {
            backendToTest.addGenre(testGenres[i]);
        }
        List<String> genresReturned = backendToTest.getGenres();

        for (int i = 0; i < testGenres.length; i++) {
            if (!genresReturned.contains(testGenres[i])) {
                return false;
            }
        }

        return true;

    }

    public boolean testGetAvgRatings() throws IOException {
        Backend backendToTest = new Backend(new StringReader(
			    "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
			        + "The Source of Shadows,The Source of Shadows,2020,Horror,83, USA,English,n \"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions ,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hum mel, Janice Kingsley Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Moun ter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by  one of our most primal fears, t he fear of the unknown.\",3.5\n"
			        + "The Insurrection,The Insurrection,2020,Action,90,USA,Englis h,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine H arrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
			        + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\" Jessi ca Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel \",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their p arents and friends to stay together. A  musical adaptation of the 1983 film.\",5.4\n"));
	


        String[] testRatings = new String[] {"1", "2", "3", "4", "7", "9", "3", "5.7", "6.4", "3.0",
            "9.8", "0.6", "9.5", "10.233"};

        String[] ratingValues = new String[] {"1", "2", "3", "4", "7", "9", "5", "6", "9", "0", "10"};

        // adds a collections of ratings to list
        for (int i = 0; i < testRatings.length; i++) {
            backendToTest.addAvgRating(testRatings[i]);
        }

        List<String> ratingsReturned = backendToTest.getAvgRatings();

        for (int i = 0; i < ratingValues.length; i++) {
            if (!ratingsReturned.contains(ratingValues[i])) {
                return false;
            }
        }

        return true;
    }
}

