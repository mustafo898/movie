package dark.composer.movie_db.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dark.composer.movie_db.data.remote.ApiService
import dark.composer.movie_db.data.repository.MovieRepositoryImpl
import dark.composer.movie_db.data.repository.PersonRepositoryImpl
import dark.composer.movie_db.data.repository.SearchRepositoryImpl
import dark.composer.movie_db.domain.repository.movie.MovieRepository
import dark.composer.movie_db.domain.repository.person.PersonRepository
import dark.composer.movie_db.domain.repository.search.SearchRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideServiceRepository(mainService: ApiService): MovieRepository {
        return MovieRepositoryImpl(mainService)
    }

    @Provides
    fun provideSearchRepository(mainService: ApiService): SearchRepository {
        return SearchRepositoryImpl(mainService)
    }

    @Provides
    fun providePersonRepository(mainService: ApiService): PersonRepository {
        return PersonRepositoryImpl(mainService)
    }
}