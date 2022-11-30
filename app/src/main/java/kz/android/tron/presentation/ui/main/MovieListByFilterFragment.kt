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
import kz.android.tron.databinding.FragmentMovieListByFilterBinding
import kz.android.tron.presentation.adapter.MovieAdapter
import kz.android.tron.presentation.ui.SetupActionbar
import kz.android.tron.presentation.viewmodel.MovieViewModel
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates


class MovieListByFilterFragment : Fragment() {

    private var _binding: FragmentMovieListByFilterBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var viewModelFactory: MovieModelFactory
    @Inject lateinit var moviesAdapter: MovieAdapter

    private val component by lazy { (requireActivity().application as App).component }
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }
    private val args by navArgs<MovieListByFilterFragmentArgs>()
    private var setupActionbar by Delegates.notNull<SetupActionbar>()


    override fun onAttach(context: Context) {
        component.inject(this)
        setupActionbar = context as SetupActionbar
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentMovieListByFilterBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionbar.setTitle(args.title)
        binding.movieRV.adapter = moviesAdapter


        moviesAdapter.onMovieClickListener = {
            findNavController().navigate(MovieListByFilterFragmentDirections.toDetailFragment(it))
        }

        lifecycleScope.launch {
            viewModel.getMovieList(args.sortBy).collect {
                moviesAdapter.submitData(it)
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}