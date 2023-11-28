package com.orhanavan.mymovieexplorer.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hoc081098.viewbindingdelegate.viewBinding
import com.orhanavan.mymovieexplorer.BuildConfig
import com.orhanavan.mymovieexplorer.R
import com.orhanavan.mymovieexplorer.databinding.FragmentDetailBinding
import com.orhanavan.mymovieexplorer.util.formatDate
import com.orhanavan.mymovieexplorer.util.formatRating
import com.orhanavan.mymovieexplorer.util.formatStar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding<FragmentDetailBinding>()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = args.movie
        with(binding) {
            movieTitle.text = movie.title
            movieImage.load("${BuildConfig.PHOTO_URL}${movie.posterPath}") {
                crossfade(true)
                transformations(RoundedCornersTransformation(requireActivity().resources.getDimension(R.dimen.movie_card_corner)))
            }
            movieRating.text = movie.voteAverage.formatRating()
            movieRatingBar.rating = movie.voteAverage.formatStar()
            movieReleaseDate.text = movie.releaseDate.formatDate()
            movieOverview.text = movie.overview
            movieGenres.text = movie.genres

            btnBack.setOnClickListener { findNavController().popBackStack() }
        }
    }
}