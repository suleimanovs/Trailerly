package kz.android.tron.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.launch
import kz.android.tron.R
import kz.android.tron.databinding.FragmentMovieListByGenreBinding
import kz.android.tron.presentation.TrailerlyApplication
import kz.android.tron.presentation.adapters.movie_adapter.MovieGridAdapter
import kz.android.tron.presentation.ui.SetupActionbar
import kz.android.tron.presentation.util.Genre
import kz.android.tron.presentation.viewmodel.GenreContentViewModel
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieListByGenreFragment : Fragment(R.layout.fragment_movie_list_by_genre) {

    private val binding by viewBinding(FragmentMovieListByGenreBinding::bind)

    @Inject lateinit var viewModelFactory: MovieModelFactory
    @Inject lateinit var movieAdapter: MovieGridAdapter

    private val component by lazy { (requireActivity().application as TrailerlyApplication).component }
    private val movieModel: GenreContentViewModel by viewModels { viewModelFactory }
    private var setupActionbar by Delegates.notNull<SetupActionbar>()
    private val args by navArgs<MovieListByGenreFragmentArgs>()

    override fun onAttach(context: Context) {
        component.inject(this)
        setupActionbar = context as SetupActionbar
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionbar.setTitle(getString(R.string.genre_title_label, Genre.entries.find {  it.id == args.genreId}?.displayName.orEmpty()))
        binding.movieRV.adapter = movieAdapter
        movieAdapter.onMovieClickListener = {
            findNavController().navigate(MovieListByGenreFragmentDirections.toMovieDetailFragment(it))
        }

        lifecycleScope.launch {
            movieModel.getMoviesByGenre(args.genreId).collect {
                movieAdapter.submitData(it)
            }
        }
    }
}