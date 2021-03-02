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
			if (movies.values[index].size() != 0) {
				for (int j = 0; j < movies.values[index].size(); j++) {
					returnList.add( (MovieInterface) movies.values[index].get(j));
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
