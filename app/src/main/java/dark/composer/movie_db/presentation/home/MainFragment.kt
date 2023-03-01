package dark.composer.movie_db.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dark.composer.movie_db.R
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.databinding.FragmentMainBinding
import dark.composer.movie_db.presentation.BaseFragment
import dark.composer.movie_db.presentation.adapter.MovieAdapter
import dark.composer.movie_db.presentation.adapter.TopRatedAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class
MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()

    private val movieAdapter by lazy {
        MovieAdapter()
    }

    private val topAdapter by lazy {
        TopRatedAdapter()
    }

    override fun onViewCreate() {
        binding.moviesList.adapter = movieAdapter
        binding.topList.adapter = topAdapter
        send()
        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.genres.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { list ->
                                list.genres.forEach { genre ->
                                    binding.tab.addTab(
                                        binding.tab.newTab().setText(genre.name).setId(genre.id)
                                    )
                                }
                                viewModel.getMovieByGenre(list.genres.first().id)
                                Toast.makeText(
                                    requireContext(),
                                    "${list.genres.first().id}",
                                    Toast.LENGTH_SHORT
                                ).show()
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.movieByGenre.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.results?.let { list ->
                                Log.d("EEEEE", "mmmmm: $list")
                                movieAdapter.setMovies(list)
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.topRated.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.results?.let { list ->
                                Log.d("EEEEE", "nnnnn: $list")
                                topAdapter.setMovies(list)
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
        viewModel.getGenres()
        viewModel.getTopRated(1)

        topAdapter.setItemClickListener {
            navController.navigate(
                R.id.action_mainFragment_to_detailsFragment,
                bundleOf("ID" to it)
            )
        }

        movieAdapter.setItemClickListener {
            navController.navigate(
                R.id.action_mainFragment_to_detailsFragment,
                bundleOf("ID" to it)
            )
        }

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewModel.getMovieByGenre(it.id)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}