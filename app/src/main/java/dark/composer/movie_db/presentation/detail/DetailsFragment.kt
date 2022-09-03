package dark.composer.movie_db.presentation.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearSnapHelper
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dark.composer.movie_db.R
import dark.composer.movie_db.YoutubeActivity
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.databinding.FragmentDeatilsBinding
import dark.composer.movie_db.presentation.BaseFragment
import dark.composer.movie_db.presentation.adapter.*
import dark.composer.movie_db.utils.Constants
import dark.composer.movie_db.utils.changeMoneyType
import dark.composer.movie_db.utils.runtimeToHM
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDeatilsBinding>(FragmentDeatilsBinding::inflate) {
    private val viewModel: DetailsViewModel by viewModels()
    private var d: Long = 0
    private val adapterPerson by lazy {
        CastAdapter()
    }

    private val adapterReview by lazy {
        ReviewAdapter()
    }

    private val adapterVideo by lazy {
        YoutubeAdapter()
    }

    private val adapterRecommend by lazy {
        TopRatedAdapter()
    }

    private val adapterSimilar by lazy {
        TopRatedAdapter()
    }

    private val adapterGenre by lazy {
        GenreMiniAdapter()
    }

    override fun onViewCreate() {
        val bundle: Bundle? = this.arguments
        bundle?.let {
            d = it.getLong("ID")
        }
        Toast.makeText(requireContext(), "$d", Toast.LENGTH_SHORT).show()
        binding.listCoast.adapter = adapterPerson
        binding.listReviews.adapter = adapterReview
        binding.trailer.adapter = adapterVideo
        binding.recommend.adapter = adapterRecommend
        binding.genre.adapter = adapterGenre
        binding.similar.adapter = adapterSimilar

        val snapper = LinearSnapHelper()
        snapper.attachToRecyclerView(binding.trailer)
        observe()
        send()
        getPopularMovies()
    }


    @SuppressLint("SetTextI18n")
    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.movie.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { detail ->
                                binding.apply {
                                    name.text = detail.original_title
                                    rate.text = detail.vote_average.toString()
                                    calendar.text = detail.release_date
                                    about.text = detail.overview
                                    time.text = detail.runtime.runtimeToHM()
                                    language.text = detail.original_language
                                    status.text = detail.status
                                    revenue.text = "\$${detail.revenue.toString().changeMoneyType()}"
                                    budget.text = "\$${detail.budget.toString().changeMoneyType()}"
                                    totalVote.text = detail.vote_count.toString()
                                }
                                adapterGenre.setGenres(detail.genres)

                                if (detail.poster_path.isNullOrBlank()) {
                                    binding.imageSmall.setImageResource(R.drawable.pauk2)
                                } else {
                                    Glide.with(requireContext()).load("${Constants.BASE_IMAGE_URL}${detail.poster_path}").into(binding.imageSmall)
                                }

                                if (detail.backdrop_path.isNullOrBlank()) {
                                    binding.imageLarge.setImageResource(R.drawable.pauk2)
                                } else {
                                    Glide.with(requireContext()).load("${Constants.BASE_IMAGE_URL}${detail.backdrop_path}").into(binding.imageLarge)
                                }
                            }
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                it.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.person.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { detail ->
                                adapterPerson.setPersons(detail.cast)
                            }
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                it.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.video.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { detail ->
                                adapterVideo.setTrailer(detail.results)
                            }
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                it.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.review.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { detail ->
                                adapterReview.setReview(detail.results)
                            }
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                it.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.recommend.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { detail ->
                                adapterRecommend.setMovies(detail.results)
                            }
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                it.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.similar.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { detail ->
                                adapterSimilar.setMovies(detail.results)
                            }
                        }
                        is BaseNetworkResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                it.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is BaseNetworkResult.Loading -> {}
                    }
                }
            }
        }
    }

    private fun send() {
        viewModel.getGenres(d)
        binding.back.setOnClickListener {
            navController.popBackStack()
        }

        adapterVideo.setItemClickListener {
            val intent = Intent(requireContext(), YoutubeActivity::class.java)
            intent.putExtra("YOUTUBE_VIDEO_ID", it)
            startActivity(intent)
        }
        viewModel.getReview(d)

        viewModel.getRecommendMovie(id = d, page = 1)
        viewModel.getSimilarMovie(id = d, page = 1)
    }

    private fun getPopularMovies() {
        binding.tab.addTab(binding.tab.newTab().setText("Review"))
        binding.tab.addTab(binding.tab.newTab().setText("Cast"))

        binding.listReviews.visibility = View.VISIBLE
        viewModel.getVideo(d)

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.listCoast.visibility = View.GONE
                        binding.listReviews.visibility = View.VISIBLE
                        viewModel.getReview(d)
                    }
                    1 -> {
                        binding.listCoast.visibility = View.VISIBLE
                        binding.listReviews.visibility = View.GONE
                        viewModel.getCasts(d)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}