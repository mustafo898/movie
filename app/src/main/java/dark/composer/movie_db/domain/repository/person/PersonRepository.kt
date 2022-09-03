package dark.composer.movie_db.domain.repository.person

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.remote.models.response.detail.person.PersonDetail
import dark.composer.movie_db.data.remote.models.response.pagination.person.PersonPaginationResponse
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPopularPerson(page: Int): Flow<BaseNetworkResult<PersonPaginationResponse>>
    fun getPopularPersonOfWeek(page: Int): Flow<BaseNetworkResult<PersonPaginationResponse>>
    fun getDetail(id: Long): Flow<BaseNetworkResult<PersonDetail>>
}