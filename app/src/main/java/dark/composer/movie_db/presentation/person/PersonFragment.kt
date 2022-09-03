package dark.composer.movie_db.presentation.person

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import dagger.hilt.android.AndroidEntryPoint
import dark.composer.movie_db.R
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.databinding.FragmentPersonBinding
import dark.composer.movie_db.presentation.BaseFragment
import dark.composer.movie_db.presentation.adapter.PersonAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonFragment : BaseFragment<FragmentPersonBinding>(FragmentPersonBinding::inflate) {
    private val viewModel: PersonViewModel by viewModels()

    private val adapter by lazy {
        PersonAdapter()
    }

    override fun onViewCreate() {
        binding.list.adapter = adapter

        observe()
        send()
    }

    private fun observe(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenStarted {
                viewModel.personList.collect {
                    when (it) {
                        is BaseNetworkResult.Success -> {
                            it.data?.let { list ->
                                adapter.setPersons(list.results)
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
        viewModel.getPopularPerson(1)
        adapter.setItemClickListener {
            navController.navigate(R.id.action_personFragment_to_personDetailFragment, bundleOf("ID" to it))
        }
    }
}