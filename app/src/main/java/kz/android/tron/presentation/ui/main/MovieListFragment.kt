package kz.android.tron.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.android.tron.R
import kz.android.tron.databinding.FragmentMovieListBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.TrailerlyApplication
import kz.android.tron.presentation.adapters.banner_adapter.MovieBannerAdapter
import kz.android.tron.presentation.adapters.genres_adapter.MoviesGenreAdapter
import kz.android.tron.presentation.adapters.movie_adapter.MovieAdapter
import kz.android.tron.presentation.util.showErrorSnackbar
import kz.android.tron.presentation.viewmodel.MovieListState
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import kz.android.tron.presentation.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val binding by viewBinding(FragmentMovieListBinding::bind)
    @Inject lateinit var viewModelFactory: MovieModelFactory
    @Inject lateinit var popularMoviesAdapter: MovieAdapter
    @Inject lateinit var topRatedMoviesAdapter: MovieAdapter
    @Inject lateinit var viewPagerAdapter: MovieBannerAdapter
    @Inject lateinit var genreAdapter: MoviesGenreAdapter

    private val component by lazy { (requireActivity().application as TrailerlyApplication).component }
    private val listViewModel: MovieViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        setupRecyclerViews()
        setupSwipeRefresh()
        observeData()
        observeStates()
    }

    private fun setupClickListeners() {
        binding.topRatedTitle.setOnClickListener {
            launchMovieContent(SORT_BY_TOP_RATED, getString(R.string.rating_top_movies))
        }
        binding.popularMoviesTitle.setOnClickListener {
            launchMovieContent(SORT_BY_POPULARITY, getString(R.string.popular_movies))
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener { refreshData() }
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.main_color, R.color.colorPrimary, R.color.colorAccent)
    }

    private fun refreshData() {
        listViewModel.refreshMovieList(SORT_BY_POPULARITY)
        listViewModel.refreshMovieList(SORT_BY_TOP_RATED)
    }

    private fun launchMovieContent(sortBy: String, title: String) {
        findNavController().navigate(MovieListFragmentDirections.byFilterScreen(sortBy, title))
    }

    private fun launchMovieDetail(movie: Movie) {
        findNavController().navigate(MovieListFragmentDirections.byDetailScreen(movie))
    }

    private fun launchGenreContent(id: Int) {
        findNavController().navigate(MovieListFragmentDirections.byGenreScreen(id))
    }

    private fun setupRecyclerViews() {
        popularMoviesAdapter.onMovieClickListener = ::launchMovieDetail
        viewPagerAdapter.onMovieClickListener = ::launchMovieDetail
        topRatedMoviesAdapter.onMovieClickListener = ::launchMovieDetail
        genreAdapter.genreClickListener = ::launchGenreContent

        binding.genreRecycler.adapter = genreAdapter
        binding.bannerViewPager.adapter = viewPagerAdapter
        binding.popularMoviesRV.adapter = popularMoviesAdapter
        binding.topRatedRV.adapter = topRatedMoviesAdapter

        popularMoviesAdapter.addLoadStateListener(::loadState)
    }

    private fun observeData() {
        lifecycleScope.launch { listViewModel.getMovieList(SORT_BY_POPULARITY).collectLatest(popularMoviesAdapter::submitData) }
        lifecycleScope.launch { listViewModel.getMovieList(SORT_BY_POPULARITY).collectLatest(viewPagerAdapter::submitData) }
        lifecycleScope.launch { listViewModel.getMovieList(SORT_BY_TOP_RATED).collectLatest(topRatedMoviesAdapter::submitData) }
    }

    private fun observeStates() {
        lifecycleScope.launch {
            listViewModel.movieListState.collect { state ->
                when (state) {
                    is MovieListState.Loading -> {
                        if (popularMoviesAdapter.itemCount == 0) {
                            startShimmer()
                        }
                        binding.swipeRefreshLayout.isRefreshing = true
                    }

                    is MovieListState.Success -> {
                        binding.swipeRefreshLayout.isRefreshing = false
                        stopShimmer()
                    }

                    is MovieListState.Error -> {
                        binding.swipeRefreshLayout.isRefreshing = false
                        stopShimmer()
                        showError(state.message)
                    }
                }
            }
        }
    }

    private fun showError(message: String) {
        showErrorSnackbar(message)
    }

    private fun loadState(listener: CombinedLoadStates) {
        val isLoading = listener.refresh is LoadState.Loading
        val isEmpty = popularMoviesAdapter.itemCount == 0

        when {
            isLoading && isEmpty -> {
                startShimmer()
                binding.swipeRefreshLayout.isRefreshing = false
            }

            isLoading && !isEmpty -> {
                binding.swipeRefreshLayout.isRefreshing = true
                stopShimmer()
            }

            else -> {
                binding.swipeRefreshLayout.isRefreshing = false
                stopShimmer()
            }
        }
    }

    private fun startShimmer() {
        binding.shimmerContainer.visibility = View.VISIBLE
        binding.ShimmerLayout.startShimmer()
    }

    private fun stopShimmer() {
        binding.shimmerContainer.visibility = View.GONE
        binding.ShimmerLayout.stopShimmer()
    }

    override fun onDestroy() {
        popularMoviesAdapter.removeLoadStateListener(::loadState)
        super.onDestroy()
    }

    companion object {
        const val SORT_BY_TOP_RATED = "vote_count.desc"
        const val SORT_BY_POPULARITY = "popularity.desc"
    }
}
