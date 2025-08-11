package nekit.corporation.shift_app.di

import dagger.Binds
import dagger.Module
import nekit.corporation.shift_app.data.RepositoryImpl
import nekit.corporation.shift_app.domain.Repository

@Module
interface AppBindModule {
    @Binds
    fun bindRepository(repository: RepositoryImpl) : Repository

}
