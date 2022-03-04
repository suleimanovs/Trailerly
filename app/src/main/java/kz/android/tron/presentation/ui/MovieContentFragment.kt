package kz.android.tron.presentation.ui

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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kz.android.tron.App
import kz.android.tron.databinding.FragmentMovieContentBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.adapter.MovieAdapter
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import kz.android.tron.presentation.viewmodel.MovieListViewModel
import javax.inject.Inject


class MoviesContentFragment : Fragment() {

    private var _binding: FragmentMovieContentBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var viewModelFactory: MovieModelFactory
    @Inject lateinit var moviesAdapter: MovieAdapter

    private val component by lazy { (requireActivity().application as App).component }
    private val movieListModel: MovieListViewModel by viewModels { viewModelFactory }


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }
    private val args by navArgs<MoviesContentFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentMovieContentBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        setupPosterClickListener()
        setupAdapterOnReachListener()
        binding.title.text = args.title
        binding.allMovies.adapter = moviesAdapter
        launchLoadData(this.movieListModel.page)

    }

    private fun setupAdapterOnReachListener() {
        moviesAdapter.onReachEndListener = {
            this.movieListModel.incrementPageCount()
            launchLoadData(this.movieListModel.page)
        }
    }

    private fun setupPosterClickListener() {
        moviesAdapter.onPosterClickListener = {
            findNavController().navigate(
                MoviesContentFragmentDirections.actionMoviesContentFragmentToMovieDetailFragment(it)
            )
        }
    }

    private fun launchLoadData(page: Int) {
        this.movieListModel.getAllMovies(args.sortBy, page).onEach {
            moviesAdapter.submitList(mutableListOf<Movie>().apply {
                addAll(moviesAdapter.currentList)
                addAll(it)
            })
        }.launchIn(lifecycleScope)
    }


}