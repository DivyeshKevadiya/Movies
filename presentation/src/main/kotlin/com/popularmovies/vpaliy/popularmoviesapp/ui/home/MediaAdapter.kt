package com.popularmovies.vpaliy.popularmoviesapp.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.popularmovies.vpaliy.popularmoviesapp.R
import com.popularmovies.vpaliy.popularmoviesapp.ui.base.BaseAdapter
import com.popularmovies.vpaliy.popularmoviesapp.ui.model.MediaModel
import com.vpaliy.kotlin_extensions.click
import kotlinx.android.synthetic.main.adapter_media_item.view.*

class MediaAdapter(context: Context, val click: (MediaModel) -> Unit)
  : BaseAdapter<MediaModel>(context) {

  inner class MediaViewHolder(itemView: View)
    : BaseViewHolder(itemView) {
    init {
      itemView.click { click(data[adapterPosition]) }
    }

    override fun bind() = with(itemView) {
      val item = this@MediaAdapter[adapterPosition]
      Glide.with(itemView.context)
          .load(item.poster)
          .priority(Priority.IMMEDIATE)
          .diskCacheStrategy(DiskCacheStrategy.RESULT)
          .placeholder(R.drawable.placeholder)
          .animate(R.anim.fade_in)
          .into(poster)
      title.text = item.title
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)
      : MediaViewHolder
      = MediaViewHolder(inflater.inflate(R.layout.adapter_media_item, parent, false))
}