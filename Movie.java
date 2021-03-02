// --== CS400 File Header Information ==--
// Name: Ryan Toh
// Email: retoh@wisc.edu
// Team: IG Red
// Role: Data Wrangler
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: n/a

import java.util.List;

public class Movie implements MovieInterface {
    private String title;
    private Integer year;
    private List<String> genres;
    private String director;
    private String description;
    private Float avgVote;

    public Movie(String title, Integer year, List<String> genres, String director, String description, Float avgVote) {
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.director = director;
        this.description = description; 
        this.avgVote = avgVote;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public Integer getYear() {
        return this.year;
    }

    @Override
    public List<String> getGenres() {
        return this.genres;
    }

    @Override
    public String getDirector() {
        return this.director;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Float getAvgVote() {
        return this.avgVote;
    }

    @Override
    public int compareTo(MovieInterface otherMovie) {
        if(this.avgVote.equals(otherMovie.getAvgVote())) { // if avgVote is equal to the argument return 0
            return 0;
        } else if(this.avgVote < otherMovie.getAvgVote()) { // if avgVote is lesser than the argument return 1
            return 1;
        } else { // if avgVote is greater than the argument return -1
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\n" +
                "Year: " + this.year + "\n" +
                "Genres: " + this.genres.toString().replace("[", "").replace("]", "") + "\n" +
                "Director: " + this.director + "\n" +
                "Description: " + this.description + "\n" +
                "Avg Vote: " + this.avgVote + "\n";
    }
}
