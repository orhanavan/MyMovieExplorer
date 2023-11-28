package com.orhanavan.mymovieexplorer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.orhanavan.mymovieexplorer.BuildConfig
import com.orhanavan.mymovieexplorer.R
import com.orhanavan.mymovieexplorer.data.model.Genre
import com.orhanavan.mymovieexplorer.data.model.Movie
import com.orhanavan.mymovieexplorer.databinding.ItemMovieBinding
import com.orhanavan.mymovieexplorer.util.formatRating
import com.orhanavan.mymovieexplorer.util.formatStar

class MovieListAdapter(
    var itemList: List<Movie> = emptyList(),
    var genreList: List<Genre> = emptyList()
): RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = itemList[position]
        val context = holder.binding.root.context

        holder.binding.movieName.text = item.title

        val data = "${BuildConfig.PHOTO_URL}${item.posterPath}"
        holder.binding.movieImage.load(data) {
            crossfade(true)
            transformations(
                RoundedCornersTransformation(context.resources.getDimension(R.dimen.movie_card_corner))
            )
                //.error()
                //.placeholder()
        }
        holder.binding.movieRating.text = item.voteAverage.formatRating()
        holder.binding.movieRatingBar.rating = item.voteAverage.formatStar()
        holder.binding.movieGenres.text = getGenreNamesByIds(item.genreIds)
    }

    private fun getGenreNamesByIds(ids: List<Int>): String {
        val selectedGenres = genreList.filter { it.id in ids }
        return selectedGenres.joinToString(separator = ", ") { it.name }
    }
}