package oguzhan.ornek.moviebook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import oguzhan.ornek.moviebook.R
import oguzhan.ornek.moviebook.adapter.SimilarMovieListAdapter
import oguzhan.ornek.moviebook.databinding.FragmentMovieDetailBinding
import oguzhan.ornek.moviebook.util.bindUrlImage
import oguzhan.ornek.moviebook.viewmodel.MovieDetailViewModel
import oguzhan.ornek.moviebook.viewmodel.SimilarViewModel

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val movieDetailViewModel : MovieDetailViewModel by viewModels()
    private val similarViewModel : SimilarViewModel by viewModels()
    private lateinit var binding : FragmentMovieDetailBinding
    private val args : MovieDetailFragmentArgs by navArgs()
    private val adapter = SimilarMovieListAdapter ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        initSimilarObservers()

    }


    fun observeLiveData(){

        movieDetailViewModel.getMovieDetail(args.movieId)
        movieDetailViewModel.movieDetailLiveData.observe(viewLifecycleOwner, Observer {
          it?.let {

              binding.movieName.text = it.title
              binding.descriptionDetailText.text = it.overview
              binding.detailScreenPoster.bindUrlImage(it.poster_path)
              binding.imdbLink.text = "IMDB Link : www.imdb.com/title/${it.imdb_id}/"
              binding.budgetText.text = "The budget of the movie : ${it.budget} $"
              binding.relaseDateText.text = "Release date : ${it.release_date}"
              binding.voteText.text = "It got ${it.vote_average} points out of ${it.vote_count} votes."
          }
            binding.imdbLink.setOnClickListener { click ->
                val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToWebViewFragment("https://www.imdb.com/title/${it.imdb_id}/")
                findNavController().navigate(action)
            }
        })
        movieDetailViewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initSimilarObservers() {
        with(similarViewModel) {
            getSimilarMovie(args.movieId)
            binding.apply {
                lifecycleOwner = this@MovieDetailFragment
                viewModel = similarViewModel
                similarRecylerView.adapter = adapter
            }

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