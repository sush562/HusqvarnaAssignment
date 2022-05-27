package com.husqvarna.dsassignment.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.commit
import com.husqvarna.dsassignment.R
import com.husqvarna.dsassignment.view.fragment.MovieDetailFragment
import com.husqvarna.dsassignment.view.fragment.MovieListFragment
import com.husqvarna.dsassignment.view.viewmodel.MovieSharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val movieSharedViewModel: MovieSharedViewModel by viewModel()

    private val progressBar: ProgressBar by lazy { findViewById(R.id.movie_progressbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservers()

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MovieListFragment.newInstance())
            .commit()
    }

    private fun initObservers() {
        movieSharedViewModel.openDetailPage.observe(this) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, MovieDetailFragment.newInstance(it.id, it.backdropPath, it.movieTitle))
                .addToBackStack(null)
                .commit()
        }
        movieSharedViewModel.showError.observe(this) {
            AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(R.string.error_message)
                .setCancelable(true)
                .setNegativeButton(android.R.string.ok, null)
                .show()
        }
        movieSharedViewModel.fetchingData.observe(this) {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        }
        movieSharedViewModel.toolbarTitle.observe(this) {
            supportActionBar?.title = it
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}