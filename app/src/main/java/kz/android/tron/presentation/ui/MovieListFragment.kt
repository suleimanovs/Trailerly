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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kz.android.tron.App
import kz.android.tron.R
import kz.android.tron.databinding.FragmentMovieListBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.adapter.MovieAdapter
import kz.android.tron.presentation.adapter.MoviesGenreAdapter
import kz.android.tron.presentation.adapter.PopularMovieBannerAdapter
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import kz.android.tron.presentation.viewmodel.MovieListViewModel
import javax.inject.Inject


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    @Inject lateinit var viewModelFactory: MovieModelFactory
    @Inject lateinit var popularMoviesAdapter : MovieAdapter
    @Inject lateinit var topRatedMoviesAdapter : MovieAdapter
    @Inject lateinit var viewPagerAdapter : PopularMovieBannerAdapter
    @Inject lateinit var genreAdapter : MoviesGenreAdapter

    private val component by lazy { (requireActivity().application as App).component }
    private val listViewModel: MovieListViewModel by viewModels { viewModelFactory }



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
        if (popularMoviesAdapter.currentList.isEmpty()) {
            startShimmer()
        }


        binding.topRatedTitle.setOnClickListener {
            launchMovieContent(SORT_BY_TOP_RATED, getString(R.string.rating_top_movies))
        }


        binding.popularMoviesTitle.setOnClickListener {
            launchMovieContent(SORT_BY_POPULARITY, getString(R.string.popular_movies))
        }

        setupGenreAdapter()
        setupPopularRecyclerView()
        setupTopRatedRecyclerView()

    }

    private fun setupGenreAdapter() {
        binding.genreRecycler.adapter = genreAdapter
        genreAdapter.genreClickListener = {
            findNavController().navigate(
                MovieListFragmentDirections.actionMovieListFragmentToGenreContentFragment(it)
            )
        }
    }

    private fun launchMovieContent(sortBy: String, title: String) {
        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMoviesContentFragment(sortBy,title)
        )
    }

    private fun launchMovieDetail(movie: Movie) {
        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie)
        )
    }


    private fun setupTopRatedRecyclerView() {
        topRatedMoviesAdapter.onPosterClickListener = {
            launchMovieDetail(it)
        }
        binding.topRatedRV.adapter = topRatedMoviesAdapter
        listViewModel.getMovieList(sortBy = SORT_BY_TOP_RATED).onEach {
            topRatedMoviesAdapter.submitList(it)
        }.launchIn(lifecycleScope)
    }


    private fun setupPopularRecyclerView() {

        popularMoviesAdapter.onPosterClickListener = { launchMovieDetail(it) }
        viewPagerAdapter.onPosterClickListener = { launchMovieDetail(it) }

        binding.bannerViewPager.adapter = viewPagerAdapter
        binding.popularMoviesRV.adapter = popularMoviesAdapter

        listViewModel.getMovieList(sortBy = SORT_BY_POPULARITY).onEach {
            stopShimmer()
            popularMoviesAdapter.submitList(it)
            viewPagerAdapter.submitList(it)
        }.launchIn(lifecycleScope)

    }


    private fun startShimmer() {
        binding.ShimmerLayout.startShimmer()
        binding.shimmerContainer.visibility = View.VISIBLE
    }

    private fun stopShimmer() {
        binding.shimmerContainer.visibility = View.GONE
        binding.ShimmerLayout.stopShimmer()
    }

    companion object {
        const val SORT_BY_TOP_RATED = "vote_count.desc"
        const val SORT_BY_POPULARITY = "popularity.desc"
    }

}




