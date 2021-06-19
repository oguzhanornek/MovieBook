package oguzhan.ornek.moviebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import oguzhan.ornek.moviebook.R
import oguzhan.ornek.moviebook.adapter.PopularMovieListAdapter
import oguzhan.ornek.moviebook.databinding.FragmentPopularListBinding
import oguzhan.ornek.moviebook.viewmodel.PopularViewModel

@AndroidEntryPoint
class PopularListFragment : Fragment() {

    private lateinit var bindingPopular : FragmentPopularListBinding
    private val popularViewModel : PopularViewModel by viewModels()
    private val adapter = PopularMovieListAdapter ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingPopular = DataBindingUtil.inflate(inflater,R.layout.fragment_popular_list,container,false)
        return bindingPopular.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }
    private fun initObservers() {
        with(popularViewModel) {
            getPopularMovie()
            bindingPopular.apply {
                lifecycleOwner = this@PopularListFragment
                viewModel = popularViewModel

                recylerViewPopular.adapter = adapter
            }

            popularMoviesLiveData.observe(viewLifecycleOwner, {
                adapter.apply {
                    movieClickListener = {
                        val action = PopularListFragmentDirections.actionPopularListFragmentToMovieDetailFragment(it)
                        findNavController().navigate(action)
                    }
                    setPopularMovie(it.toMutableList())
                    notifyDataSetChanged()
                }
            })

            errorMessage.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it,Toast.LENGTH_SHORT).show()
            })
        }
    }
}