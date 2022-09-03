package dark.composer.movie_db.presentation.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.pagination.person.PersonPaginationResponse
import dark.composer.movie_db.domain.usecase.person.PersonUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(private val useCase: PersonUseCase) : ViewModel() {
    private val _personList = MutableSharedFlow<BaseNetworkResult<PersonPaginationResponse>>()
    val personList = _personList.asSharedFlow()

    fun getPopularPersonOfWeek(page:Int){
        viewModelScope.launch {
            useCase.getPopularPersonsOfWeek(page = page).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _personList.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _personList.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _personList.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getPopularPerson(page:Int){
        viewModelScope.launch {
            useCase.getPopular(page = page).onEach { result ->
                when(result){
                    is BaseNetworkResult.Error -> {
                        _personList.emit(BaseNetworkResult.Error(result.message))
                    }
                    is BaseNetworkResult.Loading -> {
                        _personList.emit(BaseNetworkResult.Loading(result.isLoading))
                    }
                    is BaseNetworkResult.Success -> {
                        result.data?.let {
                            _personList.emit(BaseNetworkResult.Success(it))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}