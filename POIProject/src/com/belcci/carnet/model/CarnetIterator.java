package com.belcci.carnet.model;

public interface CarnetIterator {
       public ProductItem first();
       public boolean hasNext();
       public ProductItem next();
       public ProductItem prev();
       public void reset();
}
