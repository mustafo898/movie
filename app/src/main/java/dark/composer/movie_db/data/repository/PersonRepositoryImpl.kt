package dark.composer.movie_db.data.repository

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.ApiService
import dark.composer.movie_db.data.remote.models.response.detail.person.PersonDetail
import dark.composer.movie_db.data.remote.models.response.pagination.person.PersonPaginationResponse
import dark.composer.movie_db.domain.repository.person.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(private val service: ApiService):PersonRepository {
    override fun getPopularPerson(page: Int): Flow<BaseNetworkResult<PersonPaginationResponse>> = flow {
        val response = service.getFamousPersons(page = page)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getPopularPersonOfWeek(page: Int): Flow<BaseNetworkResult<PersonPaginationResponse>> = flow {
        val response = service.getPopularPersonOfWeek(page = page)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }

    override fun getDetail(id: Long): Flow<BaseNetworkResult<PersonDetail>> = flow {
        val response = service.getPersonDetailById(person_id = id)
        emit(BaseNetworkResult.Loading(true))
        if (response.isSuccessful) {
            emit(BaseNetworkResult.Loading(false))
            response.body()?.let {
                emit(BaseNetworkResult.Success(it))
            }
        } else {
            emit(BaseNetworkResult.Loading(false))
            emit(BaseNetworkResult.Error(response.message()))
        }
    }
}