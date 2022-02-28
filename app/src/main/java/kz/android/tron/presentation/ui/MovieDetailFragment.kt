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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import kz.android.tron.App
import kz.android.tron.databinding.FragmentMovieDetailBinding
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import kz.android.tron.presentation.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("$_binding is null")

    private val args by navArgs<MovieDetailFragmentArgs>()
    @Inject lateinit var viewModelFactory: MovieModelFactory
    private val component by lazy { (requireActivity().application as App).component }
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, st: Bundle?): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movie = args.movie

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getMovieTrailers(args.movie.id).observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                viewModel.trailers.addAll(it)
                initYouTubePlayerView()
            }
        }

    }

    private fun initYouTubePlayerView() {
        viewLifecycleOwner.lifecycle.addObserver(binding.YouTubePlayer)
        binding.YouTubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                youTubePlayer.loadOrCueVideo(lifecycle, viewModel.getNextTrailerId(), 0f)
                // youTubePlayer.pause() TODO: оно не правильно рабоатает, замени!
                binding.playVideo.setOnClickListener { youTubePlayer.play() }
                setPlayNextVideoButtonClickListener(youTubePlayer)
            }
        })
    }

    private fun setPlayNextVideoButtonClickListener(youTubePlayer: YouTubePlayer) {
        binding.nextTrailer.setOnClickListener {
            youTubePlayer.loadOrCueVideo(lifecycle, viewModel.getNextTrailerId(), 0f)
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}