import java.util.*;

public class Actor implements Comparable {

    String name;
    int numMovies;

    public int compareTo (Object obj)
    {
	Actor a = (Actor) obj;
	if (numMovies > a.numMovies) {
	    return -1; 
	}
	else if (numMovies < a.numMovies) {
	    return 1;
	}
	else {
	    return 0;
	}
    }
}
