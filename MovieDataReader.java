// --== CS400 File Header Information ==--
// Name: Ryan Toh
// Email: retoh@wisc.edu
// Team: IG Red
// Role: Data Wrangler
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: n/a

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

public class MovieDataReader implements MovieDataReaderInterface {
    public List<MovieInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException {
        List<MovieInterface> movies = new ArrayList<MovieInterface>();

        // Reads the CSV file and stores the rows and columns
        List<List<String>> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(inputFileReader);
        String currLine = null;

        while ((currLine = br.readLine()) != null) {
            String[] values = currLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            data.add(Arrays.asList(values));
        }

        // Get the fields (column names) and locate the important fields
        List<String> fields = new ArrayList<>();
        fields = data.get(0);
        final String[] SIG_FIELDS = new String[] {"title", "year", "genre", "director", "description", "avg_vote"};
        ArrayList<Integer> sigFieldIndex = new ArrayList<>();

        for (int i = 0; i < SIG_FIELDS.length; i++) {
            sigFieldIndex.add(fields.indexOf(SIG_FIELDS[i]));
        }

        // We go through every entry. Collect the data we want. Create a Movie object. Add the Movie object to the movies list.
        String title;
        Integer year;
        List<String> genres;
        String director;
        String description;
        Float avgVote;

        for (int i = 1; i < data.size(); i++) { // start at index 1 because index 0 is the row of column names
            // Check for uneven row length
            if (data.get(i).size() != fields.size()) throw new DataFormatException("Invalid row length");

            List<String> currRow = data.get(i);

            title = currRow.get(sigFieldIndex.get(0)).replaceAll("^\"|\"$", "");
            year = Integer.parseInt(currRow.get(sigFieldIndex.get(1)));
            genres = this.convertToList(currRow.get(sigFieldIndex.get(2)));
            director = currRow.get(sigFieldIndex.get(3)).replaceAll("^\"|\"$", "");
            description = currRow.get(sigFieldIndex.get(4)).replaceAll("^\"|\"$", "");
            avgVote = Float.parseFloat(currRow.get(sigFieldIndex.get(5)));

            // Check if year is 4 digit Integer
            if ((int) (Math.log10(year) + 1) > 4) throw new DataFormatException("Invalid year format");
            
            // Check if avgVote is within 0.0 to 10.0
            if (avgVote < 0.0 || avgVote > 10.0) throw new DataFormatException("avg_vote out of range");
            
            Movie movie = new Movie(title, year, genres, director, description, avgVote);
            movies.add(movie);
        }

        return movies;
    }

    private List<String> convertToList(String str) {
        List<String> list = new ArrayList<>();

        str = str.replace("\"", "");
        String[] arr = str.split(", ");
        list = Arrays.asList(arr);
        
        return list;
    }
    
}
