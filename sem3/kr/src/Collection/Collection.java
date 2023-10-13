package Collection;

import Exception.EmptyException;
import Bulb.Bulb;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;

public class Collection<T extends Bulb> extends ArrayList<T> {
    public Collection<T> reverseSortOrder() throws EmptyException {
        if (this.isEmpty()) {
            throw new EmptyException();
        }
        Collection<T> tmp = new Collection<T>();
        tmp.addAll(this);
        tmp.sort(Comparator.reverseOrder());
        return tmp;
    }

    public Collection<T> sortByRatio() throws EmptyException {
        if (this.isEmpty()) {
            throw new EmptyException();
        }
        Collection<T> tmp = new Collection<T>();
        tmp.addAll(this);
        tmp.sort((o1, o2) -> {
            Double tmp1 = o1.countPrice() / o1.getPower();
            Double tmp2 = o2.countPrice() / o2.getPower();
            return tmp1.compareTo(tmp2);
        });
        tmp.sort(Comparator.reverseOrder());
        return tmp;
    }

    public List<String> getListWithManufacturer() throws EmptyException {
        if (this.isEmpty()) {
            throw new EmptyException();
        }
        Set<String> set = new HashSet<>();
        for (T t : this) {
            set.add(t.getManufacturer());
        }
        return new ArrayList<>(set);
    }

    public double averagePriceOfManufacture(String manufacture) throws EmptyException {
        if (this.isEmpty()) {
            throw new EmptyException();
        }
        return this.
                stream().
                filter(p -> p.getManufacturer().equals(manufacture)).
                mapToDouble(T::countPrice).
                average().orElseThrow();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T t : this) {
            sb.append(t.toString()).append('\n');
        }
        return "Collection{ " + sb + " }";
    }
}
