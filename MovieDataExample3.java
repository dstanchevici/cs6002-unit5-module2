
import java.util.*;

class MovieData {
    String title;
    double budget;
    double revenue;
    String director;
    ArrayList<String> actors = new ArrayList<>();
}


public class MovieDataExample3 {

    static ArrayList<MovieData> movies = new ArrayList<>();

    public static void main (String[] argv)
    {
	// Read in the data.
	readMovieData ();

	// Most profitable.
	//mostProfitableMovie ();

	// Sort the movies list by profitability.
	sortByProfitability ();
	// And then print the top 20 movies (title, profit).
	mostProfitableMovies (20);
    }

    static void mostProfitableMovies (int N)
    {
	for (int i=0; i<N; i++) {
	    String title = movies.get(i).title;
	    double profit = movies.get(i).revenue-movies.get(i).budget;
	    System.out.printf ("%s: %.2f \n", title, profit); 
	}
    }
    
    //////// Sort by Profitability //////////
    
    static void sortByProfitability ()
    {
	quickSort (0, movies.size()-1);
    }

    static void quickSort (int l, int r)
    {
	if (l >= r) {
	    return;
	}
	int p = partition (l, r);
	quickSort (l, p-1);
	quickSort (p+1, r);
    }

    static int partition (int l, int r)
    {
	double pivot = movies.get(r).revenue - movies.get(r).budget;
	int i = l-1; // rightmost ind of smaller than pivot
	for (int j=l; j<r; j++) {
	    double profit = movies.get(j).revenue - movies.get(j).budget;
	    if (profit > pivot) {
		i++;
		swap (i, j);
	    }
	}
	swap (i+1, r); // put pivot in right position
	return i+1; // return ind of pivot
    }

    static void swap (int ind1, int ind2)
    {
	MovieData temp = movies.get(ind1);
	movies.set ( ind1, movies.get(ind2) );
	movies.set (ind2, temp);
    }

    /////// End of Sort by Profitability ///

    

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
