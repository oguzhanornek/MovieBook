package oguzhan.ornek.moviebook.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import oguzhan.ornek.moviebook.R
import oguzhan.ornek.moviebook.adapter.SearchMovieAdapter
import oguzhan.ornek.moviebook.databinding.FragmentSearchBinding
import oguzhan.ornek.moviebook.viewmodel.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val adapter = SearchMovieAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        with(searchViewModel) {
            binding.searchEdt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    searchViewModel.getSearchedMovie(s.toString())
                }
            })
            binding.apply {
                lifecycleOwner = this@SearchFragment
                viewModel = searchViewModel

                recylerViewSearchFragment.adapter = adapter
            }

            searchMovieLiveData.observe(viewLifecycleOwner, {
                adapter.apply {
                    movieClickListener = {
                        val action =
                            SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(it)
                        findNavController().navigate(action)
                    }
                    setSearchMovie(it.toMutableList())
                    notifyDataSetChanged()
                }
            })

            errorMessage.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }
    }


}