package com.saba.android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.saba.android.databinding.RowMovieItemLayoutBinding
import com.saba.base.BaseRecyclerAdapter
import com.saba.presentation.model.MovieUiModel

/**
 * Adapter class for Movies RecyclerView
 */
class MovieAdapter : BaseRecyclerAdapter<MovieUiModel, RowMovieItemLayoutBinding, MovieViewHolder>(MovieItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RowMovieItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding = binding)
    }
}

class MovieItemDiffUtil : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem == newItem
    }

}