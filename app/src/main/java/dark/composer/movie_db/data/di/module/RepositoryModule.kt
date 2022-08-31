package dark.composer.movie_db.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dark.composer.movie_db.data.models.kino.KinoRepositoryImpl
import dark.composer.movie_db.data.models.main.MainRepositoryImpl
import dark.composer.movie_db.data.retrofit.ApiService
import dark.composer.movie_db.domain.kino.KinoRepository
import dark.composer.movie_db.domain.main.MainRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideServiceRepository(mainService: ApiService): MainRepository {
        return MainRepositoryImpl(mainService)
    }

    @Provides
    fun provideUssdCodesRepository(mainService: ApiService): KinoRepository {
        return KinoRepositoryImpl(mainService)
    }
}