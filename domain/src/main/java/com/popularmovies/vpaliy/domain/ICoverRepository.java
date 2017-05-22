package com.popularmovies.vpaliy.domain;

import com.popularmovies.vpaliy.domain.configuration.ISortConfiguration.SortType;

import java.util.List;

import rx.Observable;

public interface ICoverRepository<T> {

    Observable<List<T>> get(SortType type);
    Observable<T> get(int id);
    Observable<List<T>> requestMore(SortType type);
    void update(T item, SortType type);
}
