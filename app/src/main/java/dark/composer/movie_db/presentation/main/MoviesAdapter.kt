package dark.composer.movie_db.presentation.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import dark.composer.movie_db.utils.Constants
import dark.composer.movie_db.databinding.ItemMovieBinding

class MoviesAdapter(val context: Context) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {
    private val movieList = mutableListOf<ResultX>()

    inner class MoviesHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ResultX){
            Glide.with(context).load("${Constants.BASE_IMAGE_URL}${list.poster_path}").into(binding.image)
            binding.name.text = list.original_title
            binding.ratingBar.rating = list.popularity.toFloat()
            Log.d("TTTTTT", "bind: ${list.original_title}")
            itemView.setOnClickListener {
                itemClickListener?.invoke(list.id.toLong())
            }
        }
    }

    private var itemClickListener: ((id: Long) -> Unit)? = null
    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    fun setMovies(list:List<ResultX>){
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) = holder.bind(movieList[position])

    override fun getItemCount() = movieList.size
}