package nekit.corporation.shift_app.data.remote_data_source

import nekit.corporation.shift_app.data.remote_data_source.dto.UserDto
import retrofit2.http.GET

interface UserApi {

    @GET("/api")
    suspend fun getUser(): UserDto
}