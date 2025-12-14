package com.mek.tmdbchallange.domain.usecase

import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.domain.repository.MovieRepository
import com.mek.tmdbchallange.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        language: String,
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>> {
        return repository.getUpcoming(language = language, page = page, region = region)
    }
}