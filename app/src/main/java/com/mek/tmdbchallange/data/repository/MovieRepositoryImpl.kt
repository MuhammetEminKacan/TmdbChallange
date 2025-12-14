package com.mek.tmdbchallange.data.repository

import android.util.Log
import com.mek.tmdbchallange.data.mapper.toDomain
import com.mek.tmdbchallange.data.remote.MovieApi
import com.mek.tmdbchallange.domain.model.Movie
import com.mek.tmdbchallange.domain.repository.MovieRepository
import com.mek.tmdbchallange.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : MovieApi
) : MovieRepository {

    override  fun getNowPlaying(
        language: String,
        page: Int,
        region: String?
    ): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
           val result =  api.getNowPlaying(language = language, page = page, region = region)
            emit(Resource.Success(result.results.map { it.toDomain() }))
        }catch (e: HttpException) {
            Log.e("getNowPlaying", e.toString())
            emit(Resource.Error("Bilinmeyen bir hata oluştu"))
        } catch (e: IOException) {
            Log.e("getNowPlaying", e.toString())
            emit(Resource.Error("Sunucuya ulaşılamıyor. İnternet bağlantınızı kontrol edin."))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPopular(
        language: String,
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val result =  api.getPopular(language = language, page = page, region = region)
            emit(Resource.Success(result.results.map { it.toDomain() }))
        }catch (e: HttpException) {
            Log.e("getPopular", e.toString())
            emit(Resource.Error("Bilinmeyen bir hata oluştu"))
        } catch (e: IOException) {
            Log.e("getPopular", e.toString())
            emit(Resource.Error("Sunucuya ulaşılamıyor. İnternet bağlantınızı kontrol edin."))
        }
    }.flowOn(Dispatchers.IO)

    override fun getTopRated(
        language: String,
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val result =  api.getTopRated(language = language, page = page, region = region)
            emit(Resource.Success(result.results.map { it.toDomain() }))
        }catch (e: HttpException) {
            Log.e("getTopRated", e.toString())
            emit(Resource.Error("Bilinmeyen bir hata oluştu"))
        } catch (e: IOException) {
            Log.e("getTopRated", e.toString())
            emit(Resource.Error("Sunucuya ulaşılamıyor. İnternet bağlantınızı kontrol edin."))
        }
    }.flowOn(Dispatchers.IO)

    override fun getUpcoming(
        language: String,
        page: Int,
        region: String?
    ) : Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val result =  api.getUpcoming(language = language, page = page, region = region)
            emit(Resource.Success(result.results.map { it.toDomain() }))
        }catch (e: HttpException) {
            Log.e("getTopRated", e.toString())
            emit(Resource.Error("Bilinmeyen bir hata oluştu"))
        } catch (e: IOException) {
            Log.e("getTopRated", e.toString())
            emit(Resource.Error("Sunucuya ulaşılamıyor. İnternet bağlantınızı kontrol edin."))
        }
    }.flowOn(Dispatchers.IO)
}