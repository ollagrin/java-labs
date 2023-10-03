package Present;

import Candy.Candy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Present<T extends Candy> extends ArrayList<T> {
    //private List<Candy> candies;

    public double getTotalWeight() {
        double weight = 0;
        for (Candy candy : this) {
            weight += candy.getWeight();
        }
        return weight;
    }

    public List<Candy> getCandyBySugar(double leftRange, double rightRange) {
        List<Candy> choosingCandies = new ArrayList<Candy>();
        for (Candy candy : this) {
            if (leftRange <= candy.getAmountOfSugar() && rightRange >= candy.getAmountOfSugar()) {
                choosingCandies.add(candy);
            }
        }
        return choosingCandies;
    }

    public List<Candy> filterCandies(Predicate<Candy> predicate) {
        return stream().filter(predicate).collect(Collectors.toList());
    }

    public void sortCandies(Comparator<T> comparator) {
        this.sort(comparator);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Present{");
        for (T t : this) {
            sb.append(t.toString());
        }
        return sb.toString();
    }
}
