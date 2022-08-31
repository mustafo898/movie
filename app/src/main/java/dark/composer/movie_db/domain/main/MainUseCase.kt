package dark.composer.movie_db.domain.main

import dark.composer.movie_db.data.base.BaseNetworkResult
import dark.composer.movie_db.data.models.actor.response.Result
import dark.composer.movie_db.data.models.kino.genres.response.Genre
import dark.composer.movie_db.data.models.kino.popular.response.ResultX
import dark.composer.movie_db.domain.main.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend fun getAllNewMovies(): Flow<BaseNetworkResult<List<ResultX>>> {
        return mainRepository.getAllNewMovies()
    }

    suspend fun getPopularMovies(): Flow<BaseNetworkResult<List<ResultX>>> {
        return mainRepository.getPopularMovies()
    }

    suspend fun getActors(): Flow<BaseNetworkResult<List<Result>>> {
        return mainRepository.getActors()
    }

    suspend fun getUpcomingMovies(): Flow<BaseNetworkResult<List<ResultX>>> {
        return mainRepository.getUpcoming()
    }

    suspend fun getTopRatedMovies(): Flow<BaseNetworkResult<List<ResultX>>> {
        return mainRepository.getTopRated()
    }

    suspend fun getMoviesByGenre(id:Int): Flow<BaseNetworkResult<List<ResultX>>> {
        return mainRepository.getMoviesByGenres(id)
    }

    suspend fun getMoviesGenre(): Flow<BaseNetworkResult<List<Genre>>> {
        return mainRepository.getMoviesGenres()
    }
}