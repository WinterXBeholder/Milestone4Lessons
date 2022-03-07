package learn.lessons;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Lesson04Streams {
    public static void main(String[] args) {
        // collections
        List<Integer> evenNumbers = List.of(2, 4, 6, 8, 10);
        // List<Player> players = getPlayers();
        // arrays
        String[] colors = {"red", "blue", "yellow", "green"};
        // Create a Stream<T> with collection.stream()
        Stream<Integer> numberStream = evenNumbers.stream();
        // Stream<Player> playerStream = players.stream();
        // Create a Stream<T> with Arrays.stream(array)
        Stream<String> colorStream = Arrays.stream(colors);

        /* Once we have a stream instance, we have access to methods that allow us to manipulate values in the stream.
        Stream methods fall in roughly four categories.

        Filter: Select a subset of elements from the existing stream. Some filter methods use Predicate<T> to decide
                which elements should be included. Others select items based on their position in the stream.
        Sort: Reorder elements in the stream.
        Transform: Also called map. A transform operation takes an element and turns it into something new. For example,
                if we want a stream of city names, we might transform a Stream<Address> to a Stream<String>. There are
                several varieties of transform methods.
        Aggregate: Calculates a single value from many elements. An aggregate could be a count, a sum of values from
                many elements, or a minimum value. Aggregate operations can operate on groups: the number of addresses
                in a specific zip code, the average salary per city, or the maximum point total in a class. */



    }



}









































