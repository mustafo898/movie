package dark.composer.movie_db.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.R
import dark.composer.movie_db.data.remote.models.response.pagination.review.ResultReview
import dark.composer.movie_db.databinding.ItemReviewBinding
import dark.composer.movie_db.utils.Constants

class ReviewAdapter() : RecyclerView.Adapter<ReviewAdapter.MoviesHolder>() {
    private val movieList = mutableListOf<ResultReview>()

    inner class MoviesHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ResultReview) {
            if (list.author_details.avatar_path.isNullOrEmpty()){
                binding.avatar.setImageResource(R.drawable.pauk)
            }else{
                Glide.with(binding.root).load("${Constants.BASE_IMAGE_URL}${list.author_details.avatar_path}").into(binding.avatar)
            }
            binding.name.text = list.author
            binding.post.text = list.content
            binding.rate.text = list.author_details.rating.toString()

            itemView.setOnClickListener {
                itemClickListener?.invoke(list.id.toLong())
            }
        }
    }

    private var itemClickListener: ((id: Long) -> Unit)? = null
    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    fun setReview(list: List<ResultReview>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount() = movieList.size
}