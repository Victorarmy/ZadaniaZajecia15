package com.example.repository;

import java.util.Collection;

public interface Repository <T>{
    boolean add(T t);

    Collection<? extends T> getAll();
}
