
import java.util.*;

class MovieData {
    String title;
    double budget;
    double revenue;
    String director;
    ArrayList<String> actors = new ArrayList<>();
}


public class MovieDataExample {

    static ArrayList<MovieData> movies = new ArrayList<>();

    public static void main (String[] argv)
    {
	// Read in the data.
	readMovieData ();

	// Print the first 10 titles.
	for (int i=0; i<10; i++) {
	    MovieData m = movies.get (i);
	    System.out.println (m.title);
	}
    }

    static void readMovieData ()
    {
	DataTool.readData ("movies.data");
	int n = DataTool.getSize ();
	for (int i=0; i<n; i++) {
	    MovieData m = new MovieData ();
	    m.budget = DataTool.getDoubleValue ("budget", i);
	    m.revenue = DataTool.getDoubleValue ("revenue", i);
	    m.title = DataTool.getValue ("title", i);
	    m.director = DataTool.getValue ("director", i);
	    m.actors = DataTool.getValues ("actors", i);
	    movies.add (m);
	}	
    }

}
