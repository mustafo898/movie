package dark.composer.movie_db.presentation.main

import android.util.Log
import android.widget.TableLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dark.composer.movie_db.R
import dark.composer.movie_db.presentation.BaseFragment
import dark.composer.movie_db.databinding.FragmentMainBinding
import dark.composer.movie_db.utils.Constants
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import java.net.IDN

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()
    private val movieAdapter by lazy {
        MoviesAdapter(requireContext())
    }

    private val popularAdapter by lazy {
        MoviesAdapter(requireContext())
    }

    private val actorAdapter by lazy {
        ActorsAdapter(requireContext())
    }

    override fun onViewCreate() {
        binding.movieList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.movieList.adapter = movieAdapter

        binding.actorList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.actorList.adapter = actorAdapter

        binding.topMovieList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.topMovieList.adapter = popularAdapter

        movieAdapter.setItemClickListener {
            val bundle = bundleOf("MOVIE_ID" to it)
            navController.navigate(R.id.action_mainFragment_to_movieFragment, bundle)
        }

        popularAdapter.setItemClickListener {
            val bundle = bundleOf("MOVIE_ID" to it)
            navController.navigate(R.id.action_mainFragment_to_movieFragment, bundle)
        }

        getActors()
        getPopularMovies()
        imageSlider()
        getMoviesGenres()
    }

    private fun getActors(){
        viewModel.actorsListLiveData.observe(requireActivity()){
            actorAdapter.setActor(it)
        }
        viewModel.getActors()
    }

    private fun getPopularMovies(){
        binding.popularGenres.addTab(binding.popularGenres.newTab().setText("POPULAR"))
        binding.popularGenres.addTab(binding.popularGenres.newTab().setText("TOP RATED"))
        binding.popularGenres.addTab(binding.popularGenres.newTab().setText("UPCOMING"))

        binding.popularGenres.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        viewModel.popularListLiveData.observe(requireActivity()){
                            popularAdapter.setMovies(it)
                        }
                    }
                    1->{
                        viewModel.topRatedListLiveData.observe(requireActivity()){
                            popularAdapter.setMovies(it)
                        }
                    }
                    2->{
                        viewModel.upcomingListLiveData.observe(requireActivity()){
                            popularAdapter.setMovies(it)
                        }
                        viewModel.getUpcoming()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        viewModel.popularListLiveData.observe(requireActivity()){
            popularAdapter.setMovies(it)
        }
        viewModel.getPopular()
        viewModel.getTopRated()
        viewModel.getUpcoming()
    }

    private fun imageSlider(){
        val imageList = ArrayList<SlideModel>()
        val position = ArrayList<Long>()
        viewModel.moviesListLiveData.observe(requireActivity()){
            it.forEach {movies->
                Log.d("RRRRRRRR", "imageSlider: $it")
                position.add(movies.id.toLong())
                imageList.add(SlideModel("${Constants.BASE_IMAGE_URL}${movies.backdrop_path}",movies.original_title))
            }
        }
        imageList.size
        binding.imageSlider.setImageList(imageList,ScaleTypes.FIT)
//        binding.imageSlider.notify()
        viewModel.getMovies()
//        binding.imageSlider.
    }

    private fun getMoviesGenres(){
        viewModel.moviesGenreListLiveData.observe(requireActivity()){
            it.forEach {movies->
                binding.moviesGenres.addTab(binding.moviesGenres.newTab().setText(movies.name).setId(movies.id))
            }
            getMoviesByGenres(it.first().id)
        }
        binding.moviesGenres.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    getMoviesByGenres(it.id)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        viewModel.getMoviesGenres()
    }

    private fun getMoviesByGenres(id:Int){
        viewModel.moviesByGenreListLiveData.observe(requireActivity()){
            movieAdapter.setMovies(it)
        }
        viewModel.getMoviesByGenres(id)
    }
}