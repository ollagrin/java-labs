package Container;

import Exceptions.EmptyContainerException;

import java.util.ArrayList;
import java.util.Collections;

public class Container<T extends Comparable<T>> extends ArrayList<T>{  // Number & Comparable 

    public T max() throws EmptyContainerException {
        if(isEmpty()){
            throw new EmptyContainerException();
        }
        return Collections.max(this);
    }
    
    public T min() throws EmptyContainerException {
        if(isEmpty()){
            throw new EmptyContainerException();
        }
        return Collections.min(this);
    }

//    public Double average() {
//        double sum = 0;
//        for(T el : this){
//            sum += el.doubleValue();
//        }
//        return sum / this.size();
//    }
}
