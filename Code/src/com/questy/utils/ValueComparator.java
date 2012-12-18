package com.questy.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Allows one to easily sort a Map by its values. For example:
 *
 * Map<Double, SmartGroup> map = new HashMap<Double, SmartGroup>();
 * ValueComparator vc = new ValueComparator(map);
 * Map<Double, SmartGroup> sortedMap = new TreeMap<Double, SmartGroup>(vc);
 */
public class ValueComparator<T extends Comparable> implements Comparator {

    private Map base;

    public ValueComparator (Map base) {
        this.base = base;
    }

    public int compare(Object a, Object b) {

//        if( ( (T) base.get(a)).compareTo( (T) base.get(b) ) ) {
//
//            return 1;
//
//        } else if( ( (T) base.get(a)).equals( (T) base.get(b)) ) {
//
//            return 0;
//
//        } else {
//
//            return -1;
//
//        }
//
        return ( (T) base.get(a)).compareTo( (T) base.get(b) );


    }
}

