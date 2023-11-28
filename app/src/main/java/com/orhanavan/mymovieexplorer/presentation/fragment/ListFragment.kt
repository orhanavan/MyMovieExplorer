package com.orhanavan.mymovieexplorer.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoc081098.viewbindingdelegate.viewBinding
import com.orhanavan.mymovieexplorer.R
import com.orhanavan.mymovieexplorer.data.model.Movie
import com.orhanavan.mymovieexplorer.databinding.FragmentListBinding
import com.orhanavan.mymovieexplorer.presentation.adapter.ItemClickListener
import com.orhanavan.mymovieexplorer.presentation.adapter.MovieListAdapter
import com.orhanavan.mymovieexplorer.presentation.adapter.SingleItemDecoration
import com.orhanavan.mymovieexplorer.presentation.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), ItemClickListener {

    private val viewModel: ListViewModel by viewModels()
    private val binding by viewBinding<FragmentListBinding>()
    private val movieListAdapter = MovieListAdapter(this)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = movieListAdapter
            addItemDecoration(SingleItemDecoration(resources.getDimension(R.dimen.movie_space).toInt()))
        }

        viewModel.popularList.observe(viewLifecycleOwner) { list ->
            list?.let {
                movieListAdapter.itemList = it
                movieListAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onMovieClicked(movie: Movie) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(movie)
        findNavController().navigate(action)
    }
}