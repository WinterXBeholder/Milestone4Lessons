package learn.cupcake;

import learn.cupcake.data.Repository;
import learn.cupcake.models.Entry;

import java.time.Year;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        /*

        Cupcake

        https://trends.google.com/trends/explore?date=all&q=%2Fm%2F03p1r4

        Numbers represent search interest relative to the highest point on the chart for the given region and time.
        A value of 100 is the peak popularity for the term. A value of 50 means that the term is half as popular.
        A score of 0 means there was not enough data for this term.

        1) Use manual looping to answer the following questions:

        a) Display the rankings for 2010
        b) Which month/year has the highest ranking?
        c) Which month was the first month to have a ranking of 50 or greater?
        d) Which year has the highest average ranking?

        2) Use the Streams API to answer the same questions.

         */

        Repository repository = new Repository("./data/google-trends-data.csv");

        List<Entry> entries = repository.getEntries();
        System.out.println(entries.size());

        /*
        Entry maxRank = entries.get(0);
        YearMonth firstFiftyMonth = null;
        int sum = 0;
        int count = 0;
        Entry lastYear = entries.get(0);
        double average = 0.0;
        Entry highestAverage = entries.get(0);
        for (Entry e : entries) {
            if (e.getYearMonth().getYear() == 2010) {
                System.out.println(e.getScore());
            }
            if (e.getScore() > maxRank.getScore()) {
                maxRank = e;
            }
            if (firstFiftyMonth == null && e.getScore() >= 50) {
                firstFiftyMonth = e.getYearMonth();
            }
            if (lastYear.getYearMonth().getYear() == e.getYearMonth().getYear()) {
                sum += e.getScore();
                count += 1;
            } else if (lastYear.getYearMonth().getYear() != e.getYearMonth().getYear()) {
                if (sum / count > average) {
                    average = sum/count;
                    highestAverage = e;
                }
                lastYear = e;
                sum = e.getScore();
                count = 1;
            }
        }
        System.out.printf("%nYeah/month with highest rank (%s): %s", maxRank.getScore(), maxRank.getYearMonth());
        System.out.printf("%nFirst month with score 50+: %s", firstFiftyMonth);
        System.out.printf("%nYear with highest average rank (%s): %s", Math.round(average),
                highestAverage.getYearMonth().getYear());
        */

        entries.stream().filter(e -> e.getYearMonth().getYear() == 2010).forEach(System.out::println);

        entries.stream().collect(Collectors.summarizingInt(Entry::getScore)).getMax();




        Entry e = entries.stream()
                .filter(entry -> entry.getScore() >= 50)
                .min(Comparator.comparing(Entry::getYearMonth))
                .orElse(null);
        System.out.println(e.getYearMonth());



    }
}




























