package io.github.luteoos.learnenglish.network

import io.github.luteoos.learnenglish.network.response.ResponsePicture
import io.github.luteoos.learnenglish.network.response.ResponseTask
import io.github.luteoos.learnenglish.network.response.ResponseUnit
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LEngApi {

    @GET("api/units/read.php")
    fun getUntList() : Single<Response<MutableList<ResponseUnit>>>

    @GET("api/tasks/read_unit.php")
    fun getTaskList(@Query("unit_id") id : String) : Single<Response<MutableList<ResponseTask>>>

    @GET("api/pictures/read_task.php")
    fun getPictureList(@Query("task_id") id : String) : Single<Response<MutableList<ResponsePicture>>>
}