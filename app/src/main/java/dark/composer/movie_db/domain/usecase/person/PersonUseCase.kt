package dark.composer.movie_db.domain.usecase.person

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.detail.person.PersonDetail
import dark.composer.movie_db.data.remote.models.response.pagination.person.PersonPaginationResponse
import dark.composer.movie_db.domain.repository.person.PersonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val repo: PersonRepository
) {
    suspend fun getPopular(page: Int): Flow<BaseNetworkResult<PersonPaginationResponse>> {
        return repo.getPopularPerson(page = page)
    }

    suspend fun getPopularPersonsOfWeek(page: Int): Flow<BaseNetworkResult<PersonPaginationResponse>> {
        return repo.getPopularPersonOfWeek(page = page)
    }

    suspend fun getDetail(id: Long): Flow<BaseNetworkResult<PersonDetail>> {
        return repo.getDetail(id = id)
    }
}