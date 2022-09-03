package dark.composer.movie_db.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.R
import dark.composer.movie_db.data.remote.models.response.detail.video.ResultVideo
import dark.composer.movie_db.data.remote.models.response.pagination.cast.Cast
import dark.composer.movie_db.databinding.ItemActorBinding
import dark.composer.movie_db.databinding.ItemTrailersBinding
import dark.composer.movie_db.utils.Constants

class YoutubeAdapter() : RecyclerView.Adapter<YoutubeAdapter.MoviesHolder>() {
    private val movieList = mutableListOf<ResultVideo>()

    inner class MoviesHolder(private val binding: ItemTrailersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ResultVideo) {
            Glide.with(binding.root.context)
                .load("https://img.youtube.com/vi/${list.key}/mqdefault.jpg")
                .into(binding.image)
            binding.play.setOnClickListener {
                itemClickListener?.invoke(list.key)
            }
        }
    }

    private var itemClickListener: ((id: String) -> Unit)? = null
    fun setItemClickListener(f: (id: String) -> Unit) {
        itemClickListener = f
    }

    fun setTrailer(list: List<ResultVideo>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(
            ItemTrailersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size
}