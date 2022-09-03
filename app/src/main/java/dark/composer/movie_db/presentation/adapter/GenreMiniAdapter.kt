package dark.composer.movie_db.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.R
import dark.composer.movie_db.data.remote.models.response.pagination.cast.Cast
import dark.composer.movie_db.data.remote.models.response.pagination.genre.Genre
import dark.composer.movie_db.data.remote.models.response.pagination.genre.GenreResponse
import dark.composer.movie_db.databinding.ItemActorBinding
import dark.composer.movie_db.databinding.ItemGenreMiniBinding
import dark.composer.movie_db.utils.Constants

class GenreMiniAdapter : RecyclerView.Adapter<GenreMiniAdapter.MoviesHolder>() {
    private val movieList = mutableListOf<Genre>()

    inner class MoviesHolder(private val binding: ItemGenreMiniBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: Genre) {

            binding.name.text = list.name
            itemView.setOnClickListener {
                itemClickListener?.invoke(list.id)
            }
        }
    }

    private var itemClickListener: ((id: Int) -> Unit)? = null

    fun setItemClickListener(f: (id: Int) -> Unit) {
        itemClickListener = f
    }

    fun setGenres(list: List<Genre>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(ItemGenreMiniBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size
}