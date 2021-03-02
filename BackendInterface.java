///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           //TODO: Insert project name
// Course:          300, Fall, 2020
//
// Author:          Ethan McKellips
// Email:           emckellips@wisc.edu
// Lecturer's Name: Mouna Kacem
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// https://cs200-www.cs.wisc.edu/wp/syllabus/#academicintegrity
// Source or Recipient; Description
// 
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.List;

public interface BackendInterface {	
	public void addGenre(String genre);
	public void addAvgRating(String rating);
	public void removeGenre(String genre);
	public void removeAvgRating(String rating);
	public List<String> getGenres();
	public List<String> getAvgRatings();
	public int getNumberOfMovies();
	public List<MovieInterface> getThreeMovies(int startingIndex);
	public List<String> getAllGenres();
	
}
