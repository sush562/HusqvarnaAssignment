package com.husqvarna.dsassignment.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.husqvarna.dsassignment.R
import com.husqvarna.dsassignment.data.entity.TMDBMovieDetail
import com.husqvarna.dsassignment.databinding.FragmentMovieDetailBinding
import com.husqvarna.dsassignment.utils.getMovieRuntime
import com.husqvarna.dsassignment.utils.getReleaseDate
import com.husqvarna.dsassignment.view.viewmodel.MovieDetailViewModel
import com.husqvarna.dsassignment.view.viewmodel.MovieSharedViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_MOVIE_ID = "arg_movie_id"
private const val ARG_MOVIE_BACKDROP_PATH = "arg_movie_backdrop_path"
private const val ARG_MOVIE_TITLE = "arg_movie_title"

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private val movieSharedViewModel: MovieSharedViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieDetailViewModel.movieId = it.getInt(ARG_MOVIE_ID)
            movieDetailViewModel.backdropPath = it.getString(ARG_MOVIE_BACKDROP_PATH) ?: ""
            movieDetailViewModel.movieTitle = it.getString(ARG_MOVIE_TITLE) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(movieId: Int, backdropPath: String, movieTitle: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MOVIE_ID, movieId)
                    putString(ARG_MOVIE_BACKDROP_PATH, backdropPath)
                    putString(ARG_MOVIE_TITLE, movieTitle)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        displayImage(movieDetailViewModel.backdropPath)
        movieSharedViewModel.setToolbarTitle(movieDetailViewModel.movieTitle)
        movieSharedViewModel.isFetchingData(true)
        movieDetailViewModel.fetchMovieDetail()
    }

    private fun initObservers() {
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { detail ->
            movieSharedViewModel.isFetchingData(false)
            detail?.let {
                setupUI(detail)
            } ?: movieSharedViewModel.showError()
        }
    }

    private fun setupUI(detail: TMDBMovieDetail) {
        movieSharedViewModel.setToolbarTitle(detail.originalTitle)
        displayImage(detail.backdropPath)
        binding.movieDetailReleaseYearTv.text = getReleaseDate(detail.releaseDate)
        binding.movieDetailDurationTv.text = getMovieRuntime(detail.runtime)
        binding.movieDetailOverviewTv.text = detail.overview
        binding.movieDetailRatingTv.text = detail.voteAverage.toString()
        binding.movieDetailVoteCountTv.text = detail.voteCount.toString()
    }

    private fun displayImage(path: String) {
        if(path.isNotEmpty()) {
            context?.let {
                Glide.with(it).load(it.getString(R.string.image_base_url_w500) + path).diskCacheStrategy(
                    DiskCacheStrategy.ALL).into(binding.movieDetailPosterIv)
            }
        }
    }

}