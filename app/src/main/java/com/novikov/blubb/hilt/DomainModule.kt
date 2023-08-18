package com.novikov.blubb.hilt

import androidx.transition.Visibility.Mode
import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository
import com.novikov.blubb.domain.usecases.AuthentificationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideAuthentificationUseCase(firebaseAuthentificationRepository: FirebaseAuthentificationRepository): AuthentificationUseCase{
        return AuthentificationUseCase(firebaseAuthentificationRepository)
    }

}