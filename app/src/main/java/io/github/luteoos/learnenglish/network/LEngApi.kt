package io.github.luteoos.learnenglish.network

import io.github.luteoos.learnenglish.network.response.ResponsePicture
import io.github.luteoos.learnenglish.network.response.ResponseTask
import io.github.luteoos.learnenglish.network.response.ResponseUnit
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LEngApi {

    @GET("api/units/read.php")
    fun getUntList() : Single<Response<MutableList<ResponseUnit>>>

    @GET("api/tasks/read_unit.php?unit_id={id}")
    fun getTaskList(@Path("id") id : String) : Single<Response<MutableList<ResponseTask>>>

    @GET("api/pictures/read_task.php?task_id={id}")
    fun getPictureList(@Path("id") id : String) : Single<Response<MutableList<ResponsePicture>>>
}