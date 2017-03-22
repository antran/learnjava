package com.antt.dsa;

import java.util.*;

/**
 * Created by BL on 3/15/2017.
 */
public class AwsTest {
    public static class Movie  {
        private int movieId;
        private float rating;
        private ArrayList<Movie> similarMovies;

        public Movie(int movieId, float rating) {
            this.movieId = movieId;
            this.rating = rating;
        }

        public int getId() {
            return movieId;
        }

        public float getRating() {
            return rating;
        }

        public ArrayList<Movie> getSimilarMovies() {
            return similarMovies;
        }

        public void setSimilarMovies(ArrayList<Movie> similarMovies) {
            this.similarMovies = similarMovies;
        }
    }
    public static Set<Movie> getMovieRecommendations (Movie movie, int N)
    {
        // WRITE YOUR CODE HERE
        Hashtable<Movie, Boolean> map = new Hashtable<>();
        Queue<Movie> movies = new LinkedList<>();
        map.put(movie, true);
        movies.addAll(movie.getSimilarMovies());
        while(movies.size() > 0) {
            Movie m = movies.poll();
            if (m != null) {
                if (!map.containsKey(m)) {
                    map.put(m, true);
                    if (m.getSimilarMovies() != null)
                        movies.addAll(m.getSimilarMovies());
                }
            }
        }

        map.remove(movie);
        // sort
        List<Movie> ll = new ArrayList<>(map.keySet());
        if (ll.size() < N) return new HashSet<>(ll);
        Collections.sort(ll, (o1, o2) -> {
            if (o2.getRating() > o1.getRating()) return 1;
            if (o2.getRating() == o1.getRating()) return 0;
            return -1;
        });
        // get N highest, all if N > total size
        return new HashSet<>(ll.subList(0, N));

    }

    public static int hasBalancedBrackets(String str)
    {
        // WRITE YOUR CODE HERE
        if (str == null) return 1;
        int ret = 0;
        String brackets = "<>(){}[]";
        Hashtable<Character,Character> map = new Hashtable<>();
        map.put('{','}');
        map.put('<','>');
        map.put('[',']');
        map.put('(',')');
//        map.put("<",">");
//        map.put("<",">");
//        map.put("<",">");
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
//            System.out.printf("c=%c ", c);
            if (brackets.indexOf(c) < 0) {
                continue;
            }

            if (st.size() == 0) {
                st.push(c);
                //System.out.printf(", size=0 add stack %c ", c);
            } else {
                if (map.containsKey(st.peek()) && map.get(st.peek()) == c) {
                    Character t = st.pop();
//                    System.out.printf(", pop stack %c ", t);

                } else {
                    st.push(c);
//                    System.out.printf(", add stack %c ", c);

                }
            }
//            System.out.println();
        }
        return st.size() == 0 ? 1 : 0;
    }

    public static double sqrt(int a) {
        double x = a/2.;
        double min = 0.;
        double max = a;
        while (Math.abs(x*x - a) > 0.001) {
            if (x*x > a) {
                double t = max;
                max = x;
                x = x - (t - x)/2;
            } else if (x*x  < a ) {
                double t = min;
                min = x;
                x = x + (x - t)/2;
            } else {
                return x;
            }
        }
        return x;
    }

    public static void main(String[] args) {
//        char c = '[';
//        Character cc = new Character('[');
//        System.out.println(c == cc);
        System.out.printf("1 =  %d \n",hasBalancedBrackets("{}"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("()"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("<>"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("[]"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("[[]]"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("[<>]"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("[<{}>]"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("[]<>"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("((()))[]<>"));
        System.out.printf("1 =  %d \n",hasBalancedBrackets("a[]e<>123"));
        System.out.printf("0 =  %d \n",hasBalancedBrackets("{"));
        System.out.printf("0 =  %d \n",hasBalancedBrackets("}"));
        System.out.printf("0 =  %d \n",hasBalancedBrackets("{}"));
        System.out.printf("0 =  %d \n",hasBalancedBrackets("{<}"));
        System.out.printf("0 =  %d \n",hasBalancedBrackets("}{}"));
        System.out.printf("0 =  %d \n",hasBalancedBrackets("[<]>"));

        Movie a = new Movie(1, (float) 1.6);
        Movie b = new Movie(2, 3.6f);
        Movie cc = new Movie(3, 2.4f);
        Movie d = new Movie(4, 4.8f);
        a.setSimilarMovies(new ArrayList<Movie>() {{ add(b); add(cc);}});
        b.setSimilarMovies(new ArrayList<Movie>() {{ add(d);}});
        cc.setSimilarMovies(new ArrayList<Movie>() {{ add(d);}});
        Set<Movie> r = getMovieRecommendations(a, 2);
        for(Movie m: r) {
            System.out.printf("%d %f, ", m.getId(), m.getRating());
        }
        System.out.println();
        r = getMovieRecommendations(a, 10);
        for(Movie m: r) {
            System.out.printf("%d %f, ", m.getId(), m.getRating());
        }

        System.out.printf("sqrt(%d) = %f\n", 2, sqrt(2));
        System.out.printf("sqrt(%d) = %f\n", 3, sqrt(3));
        System.out.printf("sqrt(%d) = %f\n", 4, sqrt(4));
        System.out.printf("sqrt(%d) = %f\n", 5, sqrt(5));
        System.out.printf("sqrt(%d) = %f\n", 8, sqrt(8));
        System.out.printf("sqrt(%d) = %f\n", 9, sqrt(9));
    }
}
