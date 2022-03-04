package kz.android.tron.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.android.tron.App
import kz.android.tron.databinding.FragmentMovieContentBinding
import kz.android.tron.presentation.adapter.MovieAdapter
import kz.android.tron.presentation.viewmodel.GenreContentViewModel
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject


class GenreContentFragment : Fragment() {

    private var _binding: FragmentMovieContentBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var viewModelFactory: MovieModelFactory
    @Inject lateinit var moviesAdapter: MovieAdapter

    private val component by lazy { (requireActivity().application as App).component }
    private val movieModel: GenreContentViewModel by viewModels { viewModelFactory }
    private val args by navArgs<GenreContentFragmentArgs>()


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentMovieContentBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieLabelId = args.genreId

        movieModel.movieGenreList.observe(viewLifecycleOwner) { moviesAdapter.submitList(it) }
        setupAdapter()
    }


    private fun setupAdapter() {
        binding.allMovies.adapter = moviesAdapter
        launchLoadData()
        setupOnPosterClickListener()
        setupAdapterOnReachListener()
    }

    private fun setupOnPosterClickListener() {
        moviesAdapter.onPosterClickListener = {
            findNavController().navigate(
                GenreContentFragmentDirections.actionGenreContentFragmentToMovieDetailFragment(it)
            )
        }
    }

    private fun setupAdapterOnReachListener() {
        moviesAdapter.onReachEndListener = {
            launchLoadData()
        }
    }

    private fun launchLoadData() = movieModel.getMoviesByGenre(genreId = args.genreId)


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}