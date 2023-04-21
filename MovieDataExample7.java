
import java.util.*;

public class MovieDataExample7 {

    static ArrayList<MovieData> movies = new ArrayList<>();

    public static void main (String[] argv)
    {
	// Read in the data.
	readMovieData ();

	// NOTE: at this point, one could shorten the movies list 
	// for the purposes of debugging.
	// shortenMovieList (100);

	// 1. Extract from the data a set of actor names.
	HashSet<String> actors = makeActorNameSet ();
	System.out.println ("# actors: " + actors.size());

	// 2. Now build the list of actors, along with their movie count.
	long startTime = System.currentTimeMillis ();
	ArrayList<Actor> actorList = makeActorList2 (actors);

	long endTime = System.currentTimeMillis ();
	System.out.println ("time for actor-movie count: " + (endTime-startTime));
	// 3. Sort.
	startTime = System.currentTimeMillis ();

	Collections.sort (actorList);

	endTime = System.currentTimeMillis ();
	System.out.println ("time for sort: " + (endTime-startTime));

	// 4. Print top 30 actors by movie count.
	int N = 30;
	for (int i=0; i<N; i++) {
	    Actor a = actorList.get (i);
	    System.out.println ("#" + i + ": " + a.name + " #movies=" + a.numMovies);
	}
    }



    static ArrayList<Actor> makeActorList2 (HashSet<String> actors)
    {
	// Build a HashMap of <String, Actor>.
	HashMap<String, Actor> actorMap = new HashMap<>();

	// Iterate through the movies.
	int count = 0;
	for (MovieData m: movies) {
	    count ++;
	    if (m.actors != null) {          // Safety-check for null.
		for (String a: m.actors) {
		    if (a != null) {
			// If already in map, then fetch and incement counter.
			// Else make a new Actor(), set name, numMovies=1
			// and "put" in map.

			// WRITE YOUR CODE HERE.
			if ( actorMap.containsKey(a) ) {
			    actorMap.get(a).numMovies += 1;
			}
			else {
			    Actor newEntry = new Actor ();
			    newEntry.name = a;
			    newEntry.numMovies = 1;
			    actorMap.put (a, newEntry);
			}
			

		    }
		}
	    }
	}

	// Now make a list out of the map.
	ArrayList<Actor> actorList = new ArrayList<>();
	for (String name: actors) {
	    Actor a = actorMap.get (name);
	    if (a != null) {
		actorList.add (a);
	    }
	}

	System.out.println ("List: #actors: " + actorList.size());
	return actorList;
    }



    static ArrayList<Actor> makeActorList (HashSet<String> actors)
    {
	ArrayList<Actor> actorList = new ArrayList<>();

	// Iterate through actor names in HashSet (outer loop).
	for (String a: actors) {
	    // Count how many movies.
	    int count = 0;

	    // Inner loop: iterate through movies. Make sure the actor field
	    // in MovieData is not null.

	    // WRITE YOUR CODE HERE.
	    for (MovieData m: movies) {
		if ( m.actors != null) {
		    for (String actor: m.actors) {
			if (a.equals(actor)) {
			    count++;
			    break;
			}
		    }
		}
	    }

	    Actor aWithCnt = new Actor ();
	    aWithCnt.name = a;
	    aWithCnt.numMovies = count;
	    actorList.add (aWithCnt);
	    

	    // This is just to show progress in the outer loop.
	    if (count > 30) {
		// Print actors who've made at least 30 movies.
		System.out.println (a + ": #movies=" + count);
	    }
	}

	return actorList;
    }

    static HashSet<String> makeActorNameSet ()
    {
	// Make a HashSet of actor names, ensuring no null string is added.
	HashSet<String> actors = new HashSet<>();

	// INSERT YOUR CODE HERE.
	for (MovieData m: movies) {
	    if (m.actors != null) {
		for (String a: m.actors) {
		    if (a != null) {
			actors.add (a);
		    }
		}
	    }
	}
	
	return actors;
    }


    static void shortenMovieList (int N)
    {
	// Make a new array list of type MovieData:
	ArrayList<MovieData> shortList = new ArrayList<>();

	// Pull out the first N movies from the movies arraylist
	// and add those to shortList: 
	// WRITE THIS CODE HERE
	for (int i=0; i<N; i++) {
	    MovieData m = movies.get(i);
	    shortList.add (m);
	}

	// Reassign to movies:
	movies = shortList;
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
