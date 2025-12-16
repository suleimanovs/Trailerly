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
import kz.android.tron.databinding.FragmentMovieListByGenreBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.TrailerlyApplication
import kz.android.tron.presentation.adapters.MovieAdapter
import kz.android.tron.presentation.ui.SetupActionbar
import kz.android.tron.presentation.util.Genre
import kz.android.tron.presentation.viewmodel.GenreContentViewModel
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieListByGenreFragment : Fragment(R.layout.fragment_movie_list_by_genre) {

    private val binding by viewBinding(FragmentMovieListByGenreBinding::bind)

    @Inject lateinit var viewModelFactory: MovieModelFactory
    private val adapter by lazy { MovieAdapter(MovieAdapter.LayoutType.GRID) }

    private val component by lazy { (requireActivity().application as TrailerlyApplication).component }
    private val movieModel: GenreContentViewModel by viewModels { viewModelFactory }
    private var setupActionbar by Delegates.notNull<SetupActionbar>()
    private val args by navArgs<MovieListByGenreFragmentArgs>()

    override fun onAttach(context: Context) {
        component.inject(this)
        setupActionbar = context as SetupActionbar
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionbar.setTitle(getString(R.string.genre_title_label, Genre.entries.find {  it.id == args.genreId}?.displayName.orEmpty()))
        binding.movieRV.adapter = adapter
        adapter.onMovieClickListener = ::onMovieClickListener
        lifecycleScope.launch { movieModel.getMoviesByGenre(args.genreId).collect(adapter::submitData) }
    }

    private fun onMovieClickListener(movie: Movie){
        findNavController().navigate(MovieListByGenreFragmentDirections.toMovieDetailFragment(movie))
    }
}