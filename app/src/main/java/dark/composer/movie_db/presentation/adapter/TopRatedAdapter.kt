package dark.composer.movie_db.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.R
import dark.composer.movie_db.data.remote.models.response.pagination.movie.ResultMovie
import dark.composer.movie_db.databinding.ItemNewsMoviesBinding
import dark.composer.movie_db.utils.Constants

class TopRatedAdapter() : RecyclerView.Adapter<TopRatedAdapter.MoviesHolder>() {
    private val movieList = mutableListOf<ResultMovie>()
    var k = 0
    inner class MoviesHolder(private val binding: ItemNewsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ResultMovie) {
            if (list.poster_path.isNullOrBlank()) {
                binding.image.setImageResource(R.drawable.pauk2)
            } else {
                Glide.with(binding.root).load("${Constants.BASE_IMAGE_URL}${list.poster_path}").into(binding.image)
            }
//            checkAndDownload(list.poster_path,binding.image,binding.root.context)
            k = layoutPosition
            binding.count.text = "${k + 1}"
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
        MoviesHolder(
            ItemNewsMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size
}