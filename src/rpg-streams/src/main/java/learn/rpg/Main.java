package learn.rpg;

import learn.rpg.data.NameRepository;
import learn.rpg.data.PlayerRepository;
import learn.rpg.domain.PlayerService;
import learn.rpg.models.Player;

import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Stream<Player> playerStream = getPlayers().stream();
        playerStream.forEach(System.out::println);
        // lambda: accept a Player and print it to the console.
        playerStream.forEach(player -> System.out.println(player));


        /* The filter method accepts a Predicate<T> parameter. In this case, it's Predicate<Player>. A predicate
        represents a method that accepts a parameter of the stream's type and returns a boolean. */
        Stream<Player> playersFromThailand = playerStream.filter(
                player -> player.getCountry().equalsIgnoreCase("Thailand"));
        playersFromThailand.forEach(System.out::println);
        /* As the number of operations increases, we may not want to track a bunch of intermediate variables. It's
        idiomatic to "chain" method calls together to avoid variables. */
        getPlayers().stream()
                .filter(player -> player.getCountry().equalsIgnoreCase("Thailand"))
                .forEach(System.out::println);
        /* A predicate can use any member of the element's type. To find players who aren't currently playing a hero,
         we can filter based on the hero list size.*/
        getPlayers().stream()
                .filter(player -> player.getLastName().startsWith("B")) // <-- this changed
                .forEach(System.out::println);

        // Skip over 100 of the 106 players who have a last name that starts with "B"
        // and print the last 6.
        getPlayers().stream()
                .filter(player -> player.getLastName().startsWith("B"))
                .skip(100)
                .forEach(System.out::println);
        // Only print the first 5 players who have a last name that starts with "B".
        getPlayers().stream()
                .filter(player -> player.getLastName().startsWith("B"))
                .limit(5)
                .forEach(System.out::println);
        // Skip the first 500 players, then print the next 10 players.
        getPlayers().stream()
                .skip(500)
                .limit(10)
                .forEach(System.out::println);


        // forEach method doesn't return a stream. It's a terminal operation. Once we call forEach, the chain is over.


    }

    static List<Player> getPlayers() {
        PlayerRepository playerRepo = new PlayerRepository("players.csv");
        NameRepository nameRepo = new NameRepository("characters.csv");
        PlayerService service = new PlayerService(playerRepo, nameRepo);
        return service.generate();
    }
}












































