package com.gcsw.workbench.console.utils;

import java.io.Serializable;
import java.io.SerializablePermission;

/**
 * Created by Administrator on 2015/7/24.
 */
public class DictRecord<T, E> implements Serializable {
    private final static long serialVersionUID = 97417957195798L;
    private final T key;
    private final E value;

    public DictRecord(T key, E value) {
        this.key = key;
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public T getKey() {

        return key;
    }
}
