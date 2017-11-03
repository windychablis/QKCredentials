package com.lilosoft.chablis.qkcredentials.rx

import com.lilosoft.chablis.qkcredentials.model.Subject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.function.Function

/**
 * Created by chablis on 2017/10/26.
 */

class HttpMethod private constructor() {
    private var retrofit: Retrofit
    private var retrofitService: RetrofitService? = null

    init {
        //手动创建一个OkHttpClient并设置超时时间
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        retrofit = Retrofit.Builder()
                .client(httpClientBuilder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        retrofitService=retrofit.create<RetrofitService>(RetrofitService::class.java)
    }

    private object SingletonHodler {
        val INSTANCE = HttpMethod()
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param subscriber 由调用者传过来的观察者对象
     */
    fun getTopMovie(sub: ProgressSubscriber<List<Subject>>) {
        retrofitService!!.getTopMovie(0, 10).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe { sub.showProgressDialog() }
                .subscribe(sub)

    }

    /**
     *
     */
    fun getCJZ(sub: ProgressSubscriber<List<Subject>>) {
        retrofitService!!.getTopMovie(0, 10).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe { sub.showProgressDialog() }
                .subscribe(sub)

    }

    private inner class HttpResultFunc<T> : Function<HttpResult<T>,T> {
        override fun apply(p0: HttpResult<T>): T {
            if (p0.getCount() == 0) {
                throw  ApiException(100);
            }
            return p0.getSubjects();        }


    }

    companion object {

//        val BASE_URL = "https://api.douban.com/v2/movie/"
        val BASE_URL = "http://219.140.178.216:8085/ZZQD/user/getInterfaceData"
        private val DEFAULT_TIMEOUT = 5

        val instance: HttpMethod
            get() = SingletonHodler.INSTANCE
    }
}
