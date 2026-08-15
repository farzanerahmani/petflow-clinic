package com.roochi.petflowshared.finder;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public interface EntityFinder <T,ID>{
    T findById(ID id);
}
