package com.husqvarna.dsassignment.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.husqvarna.dsassignment.R
import com.husqvarna.dsassignment.data.entity.MovieDetailData
import com.husqvarna.dsassignment.databinding.FragmentMovieListBinding
import com.husqvarna.dsassignment.view.adapter.MovieViewAdapter
import com.husqvarna.dsassignment.view.viewmodel.MovieSharedViewModel
import com.husqvarna.dsassignment.view.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private val movieSharedViewModel: MovieSharedViewModel by sharedViewModel()

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        movieSharedViewModel.setToolbarTitle(getString(R.string.movie_list_title))
        binding.movieListRv.layoutManager = LinearLayoutManager(requireContext())
        if (movieViewModel.movieList.value.isNullOrEmpty()) {
            movieSharedViewModel.isFetchingData(true)
            movieViewModel.fetchPopularMovies()
        }
    }

    private fun initObservers() {
        movieViewModel.movieList.observe(viewLifecycleOwner) { list ->
            movieSharedViewModel.isFetchingData(false)
            if (list.isEmpty()) {
                movieSharedViewModel.showError()
            } else {
                binding.movieListRv.adapter = MovieViewAdapter(list) { position ->
                    val movie = list[position]
                    movieSharedViewModel.openMovieDetailPage(MovieDetailData(movie.id, movie.backdropPath, movie.originalTitle))
                }
            }
        }
    }
}