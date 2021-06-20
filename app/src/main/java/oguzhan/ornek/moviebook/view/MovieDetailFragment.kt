package oguzhan.ornek.moviebook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import oguzhan.ornek.moviebook.R
import oguzhan.ornek.moviebook.adapter.SimilarMovieListAdapter
import oguzhan.ornek.moviebook.databinding.FragmentMovieDetailBinding
import oguzhan.ornek.moviebook.viewmodel.MovieDetailViewModel

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()
    private val adapter = SimilarMovieListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailViewModel.logMovieDetail()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

    }

    private fun initObservers() {
        with(movieDetailViewModel) {
            getMovieDetail(args.movieId)
            binding.apply {
                lifecycleOwner = this@MovieDetailFragment
                viewModel = movieDetailViewModel
                similarRecylerView.adapter = adapter

                movieDetailLiveData.observe(viewLifecycleOwner, { movieDetail ->
                    imdbLink.setOnClickListener {
                        val action =
                            MovieDetailFragmentDirections.actionMovieDetailFragmentToWebViewFragment(
                                "https://www.imdb.com/title/${movieDetail.imdb_id}/"
                            )
                        findNavController().navigate(action)
                    }
                })

                similarMoviesLiveData.observe(viewLifecycleOwner, {
                    adapter.apply {
                        setSimilarMovie(it.toMutableList())
                        notifyDataSetChanged()
                    }
                })

                errorMessage.observe(viewLifecycleOwner, {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

