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
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import kotlinx.coroutines.launch
import kz.android.tron.App
import kz.android.tron.R
import kz.android.tron.databinding.FragmentMovieListBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.adapter.MovieAdapter
import kz.android.tron.presentation.adapter.MovieBannerAdapter
import kz.android.tron.presentation.adapter.MoviesGenreAdapter
import kz.android.tron.presentation.viewmodel.MovieViewModel
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    @Inject lateinit var viewModelFactory: MovieModelFactory
    @Inject lateinit var popularMoviesAdapter: MovieAdapter
    @Inject lateinit var topRatedMoviesAdapter: MovieAdapter
    @Inject lateinit var viewPagerAdapter: MovieBannerAdapter
    @Inject lateinit var genreAdapter: MoviesGenreAdapter

    private val component by lazy { (requireActivity().application as App).component }
    private val listViewModel: MovieViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        binding = FragmentMovieListBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topRatedTitle.setOnClickListener {
            launchMovieContent(SORT_BY_TOP_RATED, getString(R.string.rating_top_movies))
        }
        binding.popularMoviesTitle.setOnClickListener {
            launchMovieContent(SORT_BY_POPULARITY, getString(R.string.popular_movies))
        }

        setupRecyclerViews()

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

        lifecycleScope.launch {
            listViewModel.getMovieList(sortBy = SORT_BY_POPULARITY).collect {
                popularMoviesAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            listViewModel.getMovieList(sortBy = SORT_BY_POPULARITY).collect {
                viewPagerAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            listViewModel.getMovieList(sortBy = SORT_BY_TOP_RATED).collect {
                topRatedMoviesAdapter.submitData(it)
            }
        }

    }

    private fun loadState(listener: CombinedLoadStates) {
        val case = listener.refresh is LoadState.Loading && popularMoviesAdapter.itemCount == 0
        if (case) startShimmer()
        else stopShimmer()
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




