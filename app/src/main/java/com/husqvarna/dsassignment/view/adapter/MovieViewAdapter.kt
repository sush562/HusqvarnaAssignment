package com.husqvarna.dsassignment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husqvarna.dsassignment.R
import com.husqvarna.dsassignment.data.entity.TMDBMovie
import com.husqvarna.dsassignment.databinding.FragmentMovieListItemBinding

class MovieViewAdapter(
    private val values: List<TMDBMovie>, private val onClickPositionCallBack: (Int) -> Unit
) : RecyclerView.Adapter<MovieViewAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        return ViewHolder(
            FragmentMovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Glide.with(mContext).load(mContext.getString(R.string.image_base_url_w500) + item.posterPath).into(holder.contentView)
        holder.contentView.setOnClickListener {
            onClickPositionCallBack(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val contentView: ImageView = binding.moviePosterIv
    }

}