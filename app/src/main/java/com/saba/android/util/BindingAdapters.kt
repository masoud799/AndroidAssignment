package com.saba.android.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.saba.presentation.model.PictureUiModel


@BindingAdapter("url")
fun ImageView.loadFromUrl(image: PictureUiModel?) {
    Glide.with(context).load(image?.smallImage).into(this)
}