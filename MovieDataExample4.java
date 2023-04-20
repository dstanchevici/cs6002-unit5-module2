
import java.util.*;

class MovieData {
    String title;
    double budget;
    double revenue;
    String director;
    ArrayList<String> actors = new ArrayList<>();
}


public class MovieDataExample4 {

    static ArrayList<MovieData> movies = new ArrayList<>();

    public static void main (String[] argv)
    {
	// Read in the data.
	readMovieData ();

	// The top 10 directors (in terms of # movies made):
	directorsWithMostMovies (10);
    }

    static void directorsWithMostMovies (int N)
    {
	// 1. Make a HashSet of directors.
	HashSet<String> directors = makeDirectorSet ();

	// 2. Make a HashMap<String, Integer> to store the
	//    count of # movies for each director. We'll time this
	//    for a comparison.
	long startTime = System.currentTimeMillis ();

	// First approach:
	//HashMap<String, Integer> movieCount = setMovieCount (directors);

	// Second approach:
	HashMap<String, Integer> movieCount = setMovieCount2 (/*directors*/); // the argument is redundant

	long endTime = System.currentTimeMillis ();
	System.out.println ("time for movie count: " + (endTime-startTime));

	// 3. Now print and remove top N.
	for (int i=0; i<N; i++) {
	    // Remove from the hashmap the director with the highest count.
	    String director = directorWithMost (directors, movieCount);
	    int count = movieCount.get (director);
	    System.out.println (director + " #movies=" + count);
	    directors.remove (director);
	    movieCount.remove (director);
	}

    }

    static HashMap<String, Integer> setMovieCount (HashSet<String> directors)
    {
	// Iterate through the directors (outer loop)
	// Then through the movies (inner loop)
	// Count the number of movies for each director.
	// Put the pair (director, count) in the HashMap.

	HashMap<String, Integer> map = new HashMap<>(); 

	for (String d: directors) {
	    
	    int movieCnt = 0;
	    for (MovieData m: movies) {
		if ( m.director != null && m.director.equals(d) ) {
		    movieCnt ++;
		}
	    }
	    
	    map.put (d, movieCnt);
	}

	/*
	for (Map.Entry<String, Integer> entry : map.entrySet()) {
	    System.out.println(entry.getKey() + ": " + entry.getValue());
	}
	*/
		
	return map;
    }

    static HashMap<String, Integer> setMovieCount2 (/*HashSet<String> directors*/) // I removed this parameter from the original script b/c it seems redundant.
    {
	// Iterate through the movies.
	// For each director, pull out the count from the HashMap
	// Update the count and put the pair (director, count) back in.

	HashMap<String, Integer> map = new HashMap<>();

	for (MovieData m: movies) {
	    if (m.director == null) continue;

	    if ( map.isEmpty() || !map.containsKey(m.director) ) {
		map.put (m.director, 1);
	    }
	    else {
		int newCnt = map.get(m.director) + 1;
		map.put (m.director, newCnt);
	    }
	}
	
	return map;
    }

    static HashSet<String> makeDirectorSet ()
    {
	// Create an empty HashSet
	// Then, iterate through movies, extracting the director's name,
	// and placing each in the HashSet.
	// A HashSet does not allow duplicates and so the set
	// will not have duplicates.
	// Note: check to make sure you are not inserting a null string.
	
	HashSet<String> h = new HashSet<>();
	
	for (MovieData m: movies) {
	    if (m.director != null) {
		h.add (m.director);
	    }
	}

	return h;
    }

    static String directorWithMost (HashSet<String> directors, HashMap<String, Integer> numMovies)
    {
	// Identify the director with the most movies by iterating
	// through the set of directors, and for each, obtaining their
	// movie-count via the hashmap.
	
	String directorWithMost = "";
	int greatestNum = 0;
	
	for (String d: directors) {
     	    int dCnt = numMovies.get (d);
	    if (dCnt > greatestNum) {
		directorWithMost = d;
		greatestNum = dCnt;
	    }
	}
	
	return directorWithMost;
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
