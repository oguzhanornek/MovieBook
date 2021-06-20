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
import oguzhan.ornek.moviebook.adapter.UpcomingMovieListAdapter
import oguzhan.ornek.moviebook.databinding.FragmentHomeBinding
import oguzhan.ornek.moviebook.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapter = UpcomingMovieListAdapter()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        with(homeViewModel) {
            getUpcomingMovie()
            binding.apply {
                lifecycleOwner = this@HomeFragment
                viewModel = homeViewModel

                recylerViewHomeFragment.adapter = adapter
            }

            upComingMoviesLiveData.observe(viewLifecycleOwner, {
                adapter.apply {
                    movieClickListener = {
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it)
                        findNavController().navigate(action)
                    }
                    setUpcomingMovies(it.toMutableList())
                    notifyDataSetChanged()
                }
            })

            errorMessage.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }
    }

}