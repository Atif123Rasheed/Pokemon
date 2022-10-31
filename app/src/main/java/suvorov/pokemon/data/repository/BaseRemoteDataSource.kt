package suvorov.pokemon.data.repository

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
import suvorov.pokemon.data.api.GenericResponse
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.utils.Constants.UNEXPECTED_ERROR

abstract class BaseRemoteDataSource {
    suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            } else if (response.errorBody() != null) {
                val errorBody = getErrorBody(response.errorBody())
                return error(errorBody?.message ?: UNEXPECTED_ERROR)
            }
            return error(UNEXPECTED_ERROR)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    private fun error(message: String): Result.Error = Result.Error(message)

    private fun getErrorBody(responseBody: ResponseBody?): GenericResponse? {
        return try {
            return Gson().fromJson(responseBody?.charStream(), GenericResponse::class.java)
        } catch (e: Exception) {
            null
        }
    }
}