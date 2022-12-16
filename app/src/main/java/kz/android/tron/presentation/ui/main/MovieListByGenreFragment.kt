package kz.android.tron.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import kz.android.tron.App
import kz.android.tron.R
import kz.android.tron.databinding.FragmentMovieListByGenreBinding
import kz.android.tron.presentation.adapters.movie_adapter.MovieAdapter
import kz.android.tron.presentation.ui.SetupActionbar
import kz.android.tron.presentation.util.Genres
import kz.android.tron.presentation.viewmodel.GenreContentViewModel
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates


class MovieListByGenreFragment : Fragment() {

    private var _binding: FragmentMovieListByGenreBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var viewModelFactory: MovieModelFactory
    @Inject lateinit var movieAdapter: MovieAdapter

    private val component by lazy { (requireActivity().application as App).component }
    private val movieModel: GenreContentViewModel by viewModels { viewModelFactory }
    private var setupActionbar by Delegates.notNull<SetupActionbar>()
    private val args by navArgs<MovieListByGenreFragmentArgs>()

    override fun onAttach(context: Context) {
        component.inject(this)
        setupActionbar = context as SetupActionbar
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentMovieListByGenreBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionbar.setTitle(getString(R.string.genre_title_label,Genres.getGenreNameById(args.genreId)))
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}