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
import kz.android.tron.presentation.TrailerlyApplication
import kz.android.tron.databinding.FragmentMovieListByFilterBinding
import kz.android.tron.presentation.adapters.MovieAdapter
import kz.android.tron.presentation.ui.SetupActionbar
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import kz.android.tron.presentation.viewmodel.MovieViewModel
import javax.inject.Inject
import kotlin.properties.Delegates


class MovieListByFilterFragment : Fragment(R.layout.fragment_movie_list_by_filter) {

    private val binding by viewBinding(FragmentMovieListByFilterBinding::bind)

    @Inject lateinit var viewModelFactory: MovieModelFactory
    private val moviesAdapter by lazy { MovieAdapter(MovieAdapter.LayoutType.GRID) }

    private val component by lazy { (requireActivity().application as TrailerlyApplication).component }
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }
    private val args by navArgs<MovieListByFilterFragmentArgs>()
    private var setupActionbar by Delegates.notNull<SetupActionbar>()

    override fun onAttach(context: Context) {
        component.inject(this)
        setupActionbar = context as SetupActionbar
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionbar.setTitle(args.title)
        
        binding.movieRV.adapter = moviesAdapter
        moviesAdapter.onMovieClickListener = {
            findNavController().navigate(MovieListByFilterFragmentDirections.toDetailFragment(it))
        }
        lifecycleScope.launch { viewModel.getMovieList(args.sortBy).collect (moviesAdapter::submitData) }
    }
}