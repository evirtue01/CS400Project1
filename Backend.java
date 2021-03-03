// --== CS400 File Header Information ==--
// Name: Ethan McKellips
// Email: emckellips@wisc.edu
// Team: Red
// Group: IG
// TA: Sid
// Lecturer: Florian
// Notes to Grader: N/A

// Imports
import java.util.List;
import java.util.zip.DataFormatException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * This class runs the backend of the movie mapper project
 * @author Ethan McKellips
 *
 */
public class Backend implements BackendInterface{
	// Instance variables
	private List<String> genres = new LinkedList<String>();
	private List<String> genresAdded = new LinkedList<String>();
	private List<String> ratings = new LinkedList<String>();
	private HashTableMap movies = new HashTableMap();
	private Reader reader;
	
	/**
	 * Default constructor
	 * @param r - the reader that iterates through the movies
	 * @throws IOException - throws IOException if necessary
	 */
	public Backend(Reader r) throws IOException {
		MovieDataReader reader = new MovieDataReader();
		List<MovieInterface> s = null;
		try {
			 s = reader.readDataSet(r);
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < s.size(); i++) {
			Scanner scan = new Scanner(s.get(i).toString());
			while (scan.hasNext()) {
				if (scan.next().equals("Genres:")) {
					String nextGenre = "";
					while (!nextGenre.equals("Director:")) {
						if (nextGenre.equals("")) {
							nextGenre = scan.next();
							movies.put(nextGenre, s.get(i));
						} else {
							if (nextGenre.contains(",")) {
								nextGenre = nextGenre.substring(0, nextGenre.length() - 1);
							}
							genres.add(nextGenre);
							nextGenre = scan.next();
						}
						
						
					}
				}
			}
		}
	}
	
	
	/**
	 * Note to anyone who uses this method: be sure to use the following format on every line for this constructor to work effectively:
	 * <TITLE>, <YEAR>, <GENRE(S)> (don't put a comma in for multiple genres), <DIRECTOR>, <DESCRIPTION>, <AVG VOTE>
	 * @param movieData - a list of movies, each separated by a line with the appropriate categories per the format above
	 * @throws IOException for the scanner
	 */
	public Backend (String movieData) throws IOException {
		String movieString = "";
		String title = "";
		
		Scanner scanner = new Scanner(movieData);
		while (scanner.hasNextLine()) {
			// The movie (entire line)
			movieString = scanner.nextLine();
			Scanner searchMovie = new Scanner(movieString);
			
			// Creates title
			title = searchMovie.next();
			if (!title.contains(",")) {
				String search = "";
				while (!search.contains(",")) {
					search = searchMovie.next();
					title = title + " " + search;
				}
				title = title.substring(0, title.length() - 1);
			} else {
				title = title.substring(0, title.length() - 1);
			}
			
			// Year
			String yearInString = searchMovie.next();
			yearInString = yearInString.substring(0, yearInString.length() - 1);
			int year = 0;
			try {
				 year = Integer.parseInt(yearInString);
			} catch (NumberFormatException e) {
				year = 0;
			}
			
			// Genres
			String genre = searchMovie.next();
			if (!genre.contains(",")) {
				while (!genre.contains(",")) {
					genre = genre + " " + searchMovie.next();
				}
			}
			genre = genre.substring(0, genre.length() - 1);
			List<String> movieGenres = new LinkedList();
			movieGenres.add(genre);
			
			// Director
			String director = searchMovie.next();
			if (!director.contains(",")) {
				while (!director.contains(",")) {
					director = director + " " + searchMovie.next();
				}
			}
			director = director.substring(0, director.length() - 1);
			
			// Description
			String description = searchMovie.next();
			if (!description.contains(",")) {
				while (!description.contains(",")) {
					description = description + " " + searchMovie.next();
				}
			}
			description = description.substring(0, description.length() - 1);
			
			// Avg vote
			String ratingInString = searchMovie.next();
			int rating;
			try {
				rating = Integer.parseInt(ratingInString);
			} catch (NumberFormatException e) {
				rating = 0;
			}
			float ratingToFloat = (float) rating;
			
			// Creating the movie object and inserting it into the hash table
			Movie movie = new Movie(title, year, movieGenres, director, description, ratingToFloat);
			movies.put(ratingToFloat, movie);
		}
		scanner.close();
		
		
		
		/**
		 * BufferedReader br = new BufferedReader(reader);
		MovieDataReader reader = new MovieDataReader();
		List<MovieInterface> s = new LinkedList();
		try {
			s = reader.readDataSet(br);
		} catch (IOException | DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < s.size(); i++) {
			movies.put(s.get(i).getGenres(), s.get(i));
		}
		 */
	}

	/**
	 * Adds a genre
	 */
	@Override
	public void addGenre(String genre) {
		if (genre == null) {
			return;
		}
		if (!genresAdded.contains(genre)) {
			genresAdded.add(genre);
		}
		if (!genres.contains(genre)) {
			genres.add(genre);
		}
		
	}
	
	/**
	 * Adds an average rating
	 */
	@Override
	public void addAvgRating(String rating) {
		double ratingToDouble = 0.0;
		Integer ratingToInt = 0;
		if (rating == null) {
			return;
		}
		try {
			ratingToDouble = Double.parseDouble(rating);
			ratingToInt = (int) ratingToDouble;
		} catch (Exception e) {
			System.out.println("This format is not accepted.");
			return;
		}
		if (ratingToDouble < 0.0 ||  ratingToInt > 10) {
			System.out.println("The average rating " + rating + " is not accepted.");
			return;
		}
		String check = ratingToInt.toString();
		for (int i = 0; i < ratings.size(); i++) {
			if (ratings.get(i).equals(check)) {
				return;
			}
		}
		ratings.add(check);
		
	}

	/**
	 * Removes a genre
	 */
	@Override
	public void removeGenre(String genre) {
		boolean flag = false;
		for (int i = 0; i < genres.size(); i++) {
			if (genres.get(i).equals(genre)) {
				genres.remove(i);
				flag = true;
				break;
			}
		}
		
	}

	/**
	 * Removes an average rating
	 */
	@Override
	public void removeAvgRating(String rating) {
		for (int i = 0; i < ratings.size(); i++) {
			if (ratings.get(i).equals(rating)) {
				ratings.remove(i);
			}
		}
	}

	/**
	 * Retrieves genres that user has added
	 */
	@Override
	public List<String> getGenres() {
		return genresAdded;
	}

	/**
	 * Gets a list of average ratings
	 */
	@Override
	public List<String> getAvgRatings() {
		return ratings;
	}

	
	/**
	 * Gets number of movies stored in the hash table
	 */
	@Override
	public int getNumberOfMovies() {
		return movies.size();
	}

	/**
	 * Shows each genre to the user
	 */
	@Override
	public List<String> getAllGenres() {
		return genres;
	}

	/**
	 * Gets three movies from a starting index
	 * @param startingIndex - the index at which the user wants to start from
	 * @return three movies
	 */
@Override
	public List<MovieInterface> getThreeMovies(int startingIndex) {
		int index = startingIndex;
		int movieNum = 0;
		List<MovieInterface> returnList = new LinkedList();
		for (int i = 0; i < movies.values.length; i++) {
			if (index > movies.values.length) {
				index = 0;
			}
			if (movies.values[i].size() != 0) {
				for (int j = 0; j < movies.values[i].size(); j++) {
					returnList.add( (MovieInterface) movies.values[i].get(j));
					movieNum++;
					if (movieNum == 3) break;
				}
			}
			if (movieNum == 3) {
				break;
			}
		}
		return returnList;
		}

	/**
	 * Determines if a given rating is stored in the backend class
	 * @param numberInString - rating being searched for
	 * @return returns true if the rating is in the backend class - returns false if not
	 */
	public boolean containsRating(String numberInString) {
		for (int i = 0; i < ratings.size(); i++) {
			if (ratings.get(i).equals(numberInString)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Like the containsRating() method, except it searches for genre
	 * @param genre genre being searched for
	 * @return true if the the genre is located in the class, false if otherwise
	 */
	public boolean containsGenre(String genre) {
		for (int i = 0; i < genres.size(); i++) {
			if (genres.get(i).equals(genre)) {
				return true;
			}
		}
		return false;
	}
}
