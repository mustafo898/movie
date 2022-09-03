package dark.composer.movie_db.presentation.person_detail

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dark.composer.movie_db.R
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.databinding.FragmentPersonDetailBinding
import dark.composer.movie_db.presentation.BaseFragment
import dark.composer.movie_db.presentation.person.PersonViewModel
import dark.composer.movie_db.utils.Constants
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonDetailFragment : BaseFragment<FragmentPersonDetailBinding>(FragmentPersonDetailBinding::inflate) {
    private val viewModel: PersonDetailViewModel by viewModels()
    private var d:Long = 0
    override fun onViewCreate() {
        val bundle: Bundle? = this.arguments
        bundle?.let {
            d = it.getLong("ID")
        }
        Toast.makeText(requireContext(), "$d", Toast.LENGTH_SHORT).show()
        observe()
        send()
    }

    private fun observe(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.person.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { list ->
                                binding.bio.text = list.biography
                                binding.date.text = list.birthday
                                Glide.with(requireContext()).load("${Constants.BASE_IMAGE_URL}${list.profile_path}").into(binding.image)
                                binding.homePage.text = list.homepage
                                Log.d("OOOOO", "observe: ${list.homepage}")
                                binding.homePage.setOnClickListener {
                                    activity?.startActivity(Intent(Intent.ACTION_VIEW,list.homepage.toUri()))
                                }
                                binding.rate.text = list.popularity.toString()
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

    private fun send(){
        viewModel.getDetail(d)
        binding.back.setOnClickListener {
            navController.popBackStack()
        }
    }
}