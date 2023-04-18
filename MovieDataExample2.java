
import java.util.*;

class MovieData {
    String title;
    double budget;
    double revenue;
    String director;
    ArrayList<String> actors = new ArrayList<>();
}


public class MovieDataExample2 {

    static ArrayList<MovieData> movies = new ArrayList<>();

    public static void main (String[] argv)
    {
	// Read in the data.
	readMovieData ();

	// Most profitable.
	mostProfitableMovie ();
    }

    static void mostProfitableMovie ()
    {
	MovieData mostProfitable = new MovieData (); // budget and revenue are 0 by default
	double greatestProfit = 0;

	for (MovieData m: movies) {
	    if ( (m.revenue-m.budget) > greatestProfit ) {
		greatestProfit = m.revenue-m.budget;
		mostProfitable = m;
	    }
	}
	
	System.out.println ("The most profitable movie in the data set:");
	System.out.println (" Title: " + mostProfitable.title);
	System.out.printf (" Budget: %,.2f \n", mostProfitable.budget);
	System.out.printf (" Revenue: %,.2f \n", mostProfitable.revenue);
	System.out.printf (" Profit: %,.2f \n", greatestProfit);
	
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
