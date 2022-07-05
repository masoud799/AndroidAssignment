package com.saba.android.ui.main

import com.saba.android.databinding.RowMovieItemLayoutBinding
import com.saba.base.BaseViewHolder
import com.saba.presentation.model.MovieUiModel

/**
 * ViewHolder class for Movie
 */
class MovieViewHolder constructor(
    private val binding : RowMovieItemLayoutBinding
) : BaseViewHolder<MovieUiModel, RowMovieItemLayoutBinding>(binding){

    override fun bind() {
        getRowItem()?.let {
            binding.movie = it
            binding.executePendingBindings()
        }
    }
}