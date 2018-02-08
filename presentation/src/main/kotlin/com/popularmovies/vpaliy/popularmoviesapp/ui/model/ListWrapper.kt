package com.popularmovies.vpaliy.popularmoviesapp.ui.model

import android.support.v7.widget.RecyclerView

data class ListWrapper(val adapter: RecyclerView.Adapter<*>, val title: String, val bottomReached: (() -> Unit)? = null)