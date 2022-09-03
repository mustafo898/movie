package dark.composer.movie_db.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.R
import dark.composer.movie_db.data.remote.models.response.pagination.person.ResultPerson
import dark.composer.movie_db.databinding.ItemActorBinding
import dark.composer.movie_db.utils.Constants

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.MoviesHolder>() {
    private val movieList = mutableListOf<ResultPerson>()

    inner class MoviesHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ResultPerson) {
            if (list.profile_path.isNullOrBlank()) {
                binding.image.setImageResource(R.drawable.pauk2)
            } else {
                Glide.with(binding.root).load("${Constants.BASE_IMAGE_URL}${list.profile_path}").into(binding.image)
            }
            binding.name.text = list.name
            itemView.setOnClickListener {
                itemClickListener?.invoke(list.id.toLong())
            }
        }
    }

    private var itemClickListener: ((id: Long) -> Unit)? = null
    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    fun setPersons(list: List<ResultPerson>) {
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