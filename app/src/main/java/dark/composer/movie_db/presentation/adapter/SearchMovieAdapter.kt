package dark.composer.movie_db.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.R
import dark.composer.movie_db.data.remote.models.response.pagination.movie.ResultMovie
import dark.composer.movie_db.databinding.ItemSearchBinding
import dark.composer.movie_db.utils.Constants

class SearchMovieAdapter() : RecyclerView.Adapter<SearchMovieAdapter.MoviesHolder>() {
    private val movieList = mutableListOf<ResultMovie>()

    inner class MoviesHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ResultMovie) {
            binding.calendar.text = list.release_date
            if (list.poster_path.isNullOrBlank()) {
                binding.image.setImageResource(R.drawable.pauk2)
            } else {
                Glide.with(binding.root).load("${Constants.BASE_IMAGE_URL}${list.poster_path}").into(binding.image)
            }
            binding.name.text = list.title
            binding.rate.text = list.vote_average.toString()
            itemView.setOnClickListener {
                itemClickListener?.invoke(list.id.toLong())
            }
        }
    }

    private var itemClickListener: ((id: Long) -> Unit)? = null
    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    fun setMovies(list: List<ResultMovie>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size
}