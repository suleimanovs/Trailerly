package kz.android.tron.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import kz.android.tron.App
import kz.android.tron.databinding.FragmentMovieDetailBinding
import kz.android.tron.presentation.adapters.youtube_adapter.YoutubePlayerAdapter
import kz.android.tron.presentation.ui.SetupActionbar
import kz.android.tron.presentation.viewmodel.MovieViewModel
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: MovieModelFactory
    @Inject
    lateinit var adapter: YoutubePlayerAdapter

    private val args by navArgs<MovieDetailFragmentArgs>()
    private val component by lazy { (requireActivity().application as App).component }
    private var setupActionbar by Delegates.notNull<SetupActionbar>()
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        component.inject(this)
        setupActionbar = context as SetupActionbar
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, st: Bundle?): View {
        adapter.lifecycle = lifecycle
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movie = args.movie
        binding.trailers.adapter = adapter
        setupActionbar.setTitle(args.movie.title)

        lifecycleScope.launch {
            viewModel.getMovieTrailers(args.movie.id).collect {
                adapter.submitData(it)
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}