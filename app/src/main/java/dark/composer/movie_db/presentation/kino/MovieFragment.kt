package dark.composer.movie_db.presentation.kino

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dark.composer.movie_db.databinding.FragmentMovieBinding
import dark.composer.movie_db.presentation.BaseFragment
import dark.composer.movie_db.presentation.main.MainViewModel
import dark.composer.movie_db.utils.Constants
import kotlin.math.log

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate){
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreate() {
        var id:Long = 0
        val bundle: Bundle? = this.arguments
        bundle?.let {
            id = it.getLong("MOVIE_ID",0)
            Toast.makeText(requireContext(), id.toString(), Toast.LENGTH_SHORT).show()
        }
        getMoviesByGenres(id)
    }

    private fun getMoviesByGenres(id:Long){
        viewModel.moviesByGenreListLiveData.observe(requireActivity()){
            Glide.with(requireContext()).load("${Constants.BASE_IMAGE_URL}${it.posterPath}").into(binding.image)
            Toast.makeText(requireContext(), it.backdropPath, Toast.LENGTH_SHORT).show()
            Log.d("WWWW", "getMoviesByGenres: ${it.backdropPath}")
            binding.name.text = it.title
            binding.desc.text = it.overview
            binding.ratingBar.rating = it.popularity.toFloat()
        }
        viewModel.getMoviesByGenres(id)
    }
}