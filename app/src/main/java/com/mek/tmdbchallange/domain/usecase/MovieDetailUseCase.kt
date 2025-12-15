package com.mek.tmdbchallange.domain.usecase

import com.mek.tmdbchallange.domain.model.MovieDetail
import com.mek.tmdbchallange.domain.repository.MovieRepository
import com.mek.tmdbchallange.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        movieId: Int,
    ) : Flow<Resource<MovieDetail>> {
        return repository.getMovieDetail(movieId = movieId)
    }
}