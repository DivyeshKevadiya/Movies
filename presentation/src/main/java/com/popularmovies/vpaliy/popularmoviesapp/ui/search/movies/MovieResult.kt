package com.popularmovies.vpaliy.popularmoviesapp.ui.search.movies

import com.popularmovies.vpaliy.data.mapper.Mapper
import com.popularmovies.vpaliy.domain.entity.Movie
import com.popularmovies.vpaliy.popularmoviesapp.App
import com.popularmovies.vpaliy.popularmoviesapp.di.component.DaggerSearchComponent
import com.popularmovies.vpaliy.popularmoviesapp.di.module.SearchModule
import com.popularmovies.vpaliy.popularmoviesapp.ui.model.MediaModel
import com.popularmovies.vpaliy.popularmoviesapp.ui.search.MediaAdapter
import com.popularmovies.vpaliy.popularmoviesapp.ui.search.SearchContract
import com.popularmovies.vpaliy.popularmoviesapp.ui.search.SearchResult
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class MovieResult : SearchResult<Movie>() {

  override var presenter: SearchContract.Presenter<Movie>? = null
    @Inject set(value) {
      field = value
      field?.attachView(this)
    }

  @Inject lateinit var mapper: Mapper<MediaModel, Movie>

  private val adapter by lazy { MediaAdapter(context) }

  override fun showResult(data: List<Movie>) {
    result.adapter = adapter
    adapter.data = mapper.map(data).toMutableList()
  }

  override fun inputCleared() = adapter.clear()

  override fun appendResult(data: List<Movie>) {
    adapter.append(mapper.map(data))
  }

  override fun empty() {}

  override fun inject() {
    DaggerSearchComponent.builder()
        .applicationComponent(App.component)
        .searchModule(SearchModule())
        .build().inject(this)
  }
}