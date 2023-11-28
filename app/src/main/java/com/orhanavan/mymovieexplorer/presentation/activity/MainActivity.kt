package com.orhanavan.mymovieexplorer.presentation.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoc081098.viewbindingdelegate.viewBinding
import com.orhanavan.mymovieexplorer.R
import com.orhanavan.mymovieexplorer.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.orhanavan.mymovieexplorer.databinding.ActivityMainBinding
import com.orhanavan.mymovieexplorer.presentation.adapter.MovieListAdapter
import com.orhanavan.mymovieexplorer.presentation.adapter.SingleItemDecoration

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private val binding by viewBinding<ActivityMainBinding>()
    private val movieListAdapter = MovieListAdapter()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieListAdapter
            addItemDecoration(SingleItemDecoration(resources.getDimension(R.dimen.movie_space).toInt()))
        }

        viewModel.popularList.observe(this) { list ->
            list?.let {
                movieListAdapter.itemList = list
                movieListAdapter.notifyDataSetChanged()
            }
        }

        viewModel.genreList.observe(this) {list ->
            list?.let {
                movieListAdapter.genreList = list
                movieListAdapter.notifyDataSetChanged()
            }
        }

    }
}