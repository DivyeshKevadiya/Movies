package com.popularmovies.vpaliy.popularmoviesapp.di.module

import com.popularmovies.vpaliy.data.Config
import com.popularmovies.vpaliy.data.repository.*
import com.popularmovies.vpaliy.domain.entity.Actor
import com.popularmovies.vpaliy.domain.entity.Movie
import com.popularmovies.vpaliy.domain.entity.TVShow
import com.popularmovies.vpaliy.domain.repository.MediaRepository
import com.popularmovies.vpaliy.domain.repository.SearchRepository
import com.vpaliy.tmdb.TMDB
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {
  private val client = TMDB.create(Config.API_KEY, {
    val okHttp = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .build()
    setOkHttpClient(okHttp)
  })

  @Provides
  @Singleton
  internal fun movieRepository(repository: MovieRepository)
      : MediaRepository<Movie> = repository

  @Provides
  @Singleton
  internal fun tvRepository(repository: TVRepository)
      : MediaRepository<TVShow> = repository

  @Provides
  @Singleton
  internal fun tvSearch(repository: TVSearchRepository)
      : SearchRepository<TVShow> = repository

  @Provides
  @Singleton
  internal fun movieSearch(repository: MovieSearchRepository)
      : SearchRepository<Movie> = repository

  @Provides
  @Singleton
  internal fun peopleSearch(repository: PeopleRepository)
      : SearchRepository<Actor> = repository

  @Provides
  @Singleton
  internal fun genreService() = client.genreService

  @Provides
  @Singleton
  internal fun moviesService() = client.moviesService

  @Provides
  @Singleton
  internal fun searchService() = client.searchService

  @Provides
  @Singleton
  internal fun tvService() = client.tvService
}
