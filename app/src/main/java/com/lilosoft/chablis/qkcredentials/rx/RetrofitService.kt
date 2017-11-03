package com.lilosoft.chablis.qkcredentials.rx

import com.lilosoft.chablis.qkcredentials.model.DBCZ
import com.lilosoft.chablis.qkcredentials.model.Subject
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.GET



/**
 * Created by chablis on 2017/10/25.
 */

interface RetrofitService {
    @FormUrlEncoded
    @POST("account/login")
    fun budongchan(
            @Field("api_key") api_key: String,
            @Field("api_secret") api_secret: String,
            @Field("method") dbcxx: String,
            @Field("version") version: String,
            @Field("paramStr") paramStr: String
    ): Observable<HttpResult<DBCZ>>

    @GET("getIpInfo.php")
    fun test(@Query("ip") ip: String): Observable<BaseEntity>

    @GET("top250")
    fun getTopMovie(@Query("start") start: Int, @Query("count") count: Int): Observable<HttpResult<List<Subject>>>

    @GET("top250")
    fun getCJZ(@Query("paramStr") paramStr: String, @Query("api_key") api_key: String, @Query("api_secret") api_secret: String, @Query("method") method: String, @Query("version") version :String): Observable<HttpResult<List<Subject>>>
}
