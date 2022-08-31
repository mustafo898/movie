package dark.composer.movie_db.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dark.composer.movie_db.data.models.actor.response.Result
import dark.composer.movie_db.utils.Constants
import dark.composer.movie_db.databinding.ItemActorBinding

class ActorsAdapter(val context: Context) : RecyclerView.Adapter<ActorsAdapter.ActorHolder>() {
    private val actorList = mutableListOf<Result>()

    inner class ActorHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: Result) {
            Glide.with(context).load("${Constants.BASE_IMAGE_URL}${list.profile_path}")
                .into(binding.image)
        }
    }

    fun setActor(list: List<Result>) {
        actorList.clear()
        actorList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ActorHolder(
        ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ActorHolder, position: Int) = holder.bind(actorList[position])

    override fun getItemCount() = actorList.size
}