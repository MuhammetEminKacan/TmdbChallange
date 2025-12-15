package com.mek.tmdbchallange.domain.usecase

import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.domain.repository.MovieRepository
import com.mek.tmdbchallange.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        query: String,
        page: Int,
        includeAdult: Boolean
    ) : Flow<Resource<List<Movie>>> {
        return repository.searchMovies(query = query,page = page, includeAdult = includeAdult)
    }
}