package com.orhanavan.mymovieexplorer.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hoc081098.viewbindingdelegate.viewBinding
import com.orhanavan.mymovieexplorer.R
import com.orhanavan.mymovieexplorer.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.orhanavan.mymovieexplorer.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private val binding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonLoadPopular.setOnClickListener{
            viewModel.loadPopular()
        }
    }
}