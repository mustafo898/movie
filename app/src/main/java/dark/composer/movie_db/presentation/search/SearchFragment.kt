package dark.composer.movie_db.presentation.search

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import dagger.hilt.android.AndroidEntryPoint
import dark.composer.movie_db.R
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.databinding.FragmentSearchBinding
import dark.composer.movie_db.presentation.BaseFragment
import dark.composer.movie_db.presentation.adapter.SearchMovieAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()

    private val adapter by lazy {
        SearchMovieAdapter()
    }

    override fun onViewCreate() {
        binding.list.adapter = adapter

        observe()
        send()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.search.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { list ->
                                if (list.results.isNullOrEmpty()) {
                                    binding.lin2.visibility = View.VISIBLE
                                    binding.list.visibility = View.GONE
                                } else {
                                    binding.lin2.visibility = View.GONE
                                    binding.list.visibility = View.VISIBLE
                                }
                                adapter.setMovies(list.results)
                            }
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                it.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }
    }

    private fun send() {
        binding.search.addTextChangedListener {
            viewModel.getSearch(page = 1, query = it.toString())
        }

        viewModel.getSearch(page = 1, query = "A")

        adapter.setItemClickListener {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            navController.navigate(
                R.id.action_searchFragment_to_detailsFragment,
                bundleOf("ID" to it)
            )
        }
    }
}
