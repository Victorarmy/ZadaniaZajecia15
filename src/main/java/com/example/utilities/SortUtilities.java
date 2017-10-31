package com.example.utilities;

import java.util.Collection;

public interface SortUtilities <T, K>{
    Collection<? extends T> getRightOrder(Collection<? extends T> collection, K orderIdentifier);
}
