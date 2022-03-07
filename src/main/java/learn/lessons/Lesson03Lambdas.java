package learn.lessons;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Lesson03Lambdas {
    public static void main(String[] args) {

        // this lambda
        /*
        parameter, no type required. It's inferred.
        |
        | arrow separates parameters from method body
        | |
        | |       method body and implicit boolean return
        | |       |
        v v       v
        a -> a.getCity().equalsIgnoreCase("Roanoke")


        // is equivalent to
        static boolean inRoanoke(Address a) {
            return a.getCity().equalsIgnoreCase("Roanoke");
        } */



        /*
        // A lambda's contract is enforced by a data type.

        Stream<Address> addressStream = getAddressStream();

        // filter accepts Predicate<T>, which means:
        // one parameter and a boolean return value.
        addressStream.filter(a -> a.getCity().equals("Chicago"));

        // forEach accepts Consumer<T>, which means:
        // one parameter and no return value.
        addressStream.forEach(a -> System.out.println(a));

        // sorted accepts Comparator<T>, which means:
        // two parameters and an int return value.
        addressStream.sorted((a, b) -> a.getCity().compareTo(b.getCity()));

        // map accepts Function<T, R>, which means:
        // one parameter and an inferred return value.
        addressStream.map(a -> a.getCity());
         */


    }

    public void lambdaDeconstruction() {
        // Some lambda syntax is optional. From complete to sparse:

        // 1. Named method
        Predicate<Address> criteria = this::inRoanoke;
        /* Wherever a lambda is required, we can use a named method to satisfy its contract. In a non-static method,
        use this::methodName. In a static context, use TypeName::methodName. */

        // 2. explicit parameter type and return
        criteria = (Address a) -> {
            return a.getCity().equalsIgnoreCase("Roanoke");
        }; /* A lambda can include parameter type names, a return statement, and a full code block for the method body.*/

        // 3. inferred parameter type, explicit return
        criteria = (a) -> {
            return a.getCity().equalsIgnoreCase("Roanoke");
        }; // If the parameter type is omitted, the compiler infers it.

        // 4. no parameter parentheses, inferred parameter type, explicit return
        criteria = a -> {
            return a.getCity().equalsIgnoreCase("Roanoke");
        }; /* Parameter parentheses are not required unless there are no parameters. Then empty parentheses are
        required. */

        // 5. inferred parameter type and return
        criteria = a -> a.getCity().equalsIgnoreCase("Roanoke");
        /* Usually, if we're using a lambda, we're trying to be concise. The most concise syntax removes anything that
        isn't required. */
    }
    boolean inRoanoke(Address a) {
        return a.getCity().equalsIgnoreCase("Roanoke");
    }

    static List<Address> filter(List<Address> addresses, Predicate<Address> criteria) {
        ArrayList<Address> result = new ArrayList<>();
        for (Address a : addresses) {
            if (criteria.test(a)) {
                result.add(a);
            }
        }
        return result;
    }
}
