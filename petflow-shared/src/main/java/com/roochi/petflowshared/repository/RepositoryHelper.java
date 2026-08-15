package com.roochi.petflowshared.repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
public final class RepositoryHelper{

    public static <T>T getOrThrow(Optional<T> optional,RuntimeException exception){
        return optional.orElseThrow(()->exception);
    }
}
