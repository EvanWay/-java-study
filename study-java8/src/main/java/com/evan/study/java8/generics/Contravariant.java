package com.evan.study.java8.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆变
 * 需要传递方法进方法，作为消费者
 */
public class Contravariant {

    public static void main(String[] args) {
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(120.0);

        Filter<Number> filter = new Filter<Number>() {
            @Override
            public boolean test(Number element) {
                return element.doubleValue() > 100.0;
            }
        };

        ContravariantOther other = new ContravariantOther();
        other.removeIf(doubleList, filter);
    }

}

interface Filter<E> {
    boolean test(E element);
}

class ContravariantOther {

    public <E> List<E> removeIf(List<E> list, Filter<? super E> filter) {
        List<E> removeList = new ArrayList<>();
        for (E e : list) {
            if (filter.test(e)) {
                removeList.add(e);
            }
        }
        list.removeAll(removeList);
        return removeList;
    }
}
