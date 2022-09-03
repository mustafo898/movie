package dark.composer.movie_db.presentation.person_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.detail.person.PersonDetail
import dark.composer.movie_db.data.remote.models.response.pagination.person.PersonPaginationResponse
import dark.composer.movie_db.domain.usecase.person.PersonUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(private val useCase: PersonUseCase) : ViewModel() {
    private val _person = MutableSharedFlow<BaseNetworkResult<PersonDetail>>()
    val person = _person.asSharedFlow()

    fun getDetail(id:Long){
        viewModelScope.launch {
            useCase.getDetail(id = id).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _person.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _person.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _person.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}