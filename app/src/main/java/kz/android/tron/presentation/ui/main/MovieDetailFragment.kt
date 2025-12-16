package kz.android.tron.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.android.tron.R
import kz.android.tron.databinding.FragmentMovieDetailBinding
import kz.android.tron.presentation.TrailerlyApplication
import kz.android.tron.presentation.adapters.YoutubePlayerAdapter
import kz.android.tron.presentation.ui.SetupActionbar
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import kz.android.tron.presentation.viewmodel.MovieViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val binding by viewBinding(FragmentMovieDetailBinding::bind)

    @Inject
    lateinit var viewModelFactory: MovieModelFactory
    @Inject
    lateinit var adapter: YoutubePlayerAdapter

    private val args by navArgs<MovieDetailFragmentArgs>()
    private val component by lazy { (requireActivity().application as TrailerlyApplication).component }
    private var setupActionbar by Delegates.notNull<SetupActionbar>()
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        component.inject(this)
        setupActionbar = context as SetupActionbar
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeTrailers()
    }

    private fun setupUI() {
        adapter.lifecycle = lifecycle
        binding.movie = args.movie
        binding.trailers.adapter = adapter
        setupActionbar.setTitle(args.movie.title)
    }

    private fun observeTrailers() {
        lifecycleScope.launch { viewModel.getMovieTrailers(args.movie.id).collectLatest(adapter::submitData) }
    }
}