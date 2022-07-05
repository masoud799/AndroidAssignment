package com.saba.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.saba.android.databinding.ActivityMainBinding
import com.saba.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        // do nothing
    }

}