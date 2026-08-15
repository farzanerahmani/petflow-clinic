package com.roochi.petflowshared.finder;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@RequiredArgsConstructor
public abstract class AbstractFinder<T, ID> implements EntityFinder<T, ID> {

    protected abstract JpaRepository<T, ID> repository();

    protected abstract RuntimeException notFoundException(ID id);

    @Override
    public T findById(ID id) {
        return repository()
                .findById(id)
                .orElseThrow(() -> notFoundException(id));
    }
}
