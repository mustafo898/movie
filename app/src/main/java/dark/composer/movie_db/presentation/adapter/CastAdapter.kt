package dark.composer.movie_db.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.R
import dark.composer.movie_db.data.remote.models.response.pagination.cast.Cast
import dark.composer.movie_db.databinding.ItemActorBinding
import dark.composer.movie_db.utils.Constants

class CastAdapter() : RecyclerView.Adapter<CastAdapter.MoviesHolder>() {
    private val movieList = mutableListOf<Cast>()

    inner class MoviesHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: Cast) {
            if (list.profile_path.isNullOrBlank()) {
                binding.image.setImageResource(R.drawable.pauk2)
            } else {
                Glide.with(binding.root).load("${Constants.BASE_IMAGE_URL}${list.profile_path}").into(binding.image)
            }
//            checkAndDownload(list.profile_path,binding.image,binding.root.context)
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

    fun setPersons(list: List<Cast>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size
}