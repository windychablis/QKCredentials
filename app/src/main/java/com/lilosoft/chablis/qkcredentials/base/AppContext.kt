package com.lilosoft.chablis.qkcredentials.base

/**
 * Created by chablis on 2016/11/7.
 */

import android.app.Application
import android.graphics.Bitmap

import com.lilosoft.chablis.qkcredentials.model.User
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.assist.QueueProcessingType

class AppContext : Application() {
    var user: User? = null

    /**
     * 是否启动检查更新
     *
     * @return
     */
    val isCheckUp: Boolean
        get() = true

    val isPlayAnim: Boolean
        get() = false


    override fun onCreate() {
        super.onCreate()
        sInstance = this
        val options = DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).build()
        val config = ImageLoaderConfiguration.Builder(
                applicationContext)
                .defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(Md5FileNameGenerator())
                .diskCacheFileCount(500)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build()
        ImageLoader.getInstance().init(config)
        //        Fresco.initialize(this);
    }

    companion object {

        private var sInstance: Application? = null

        fun get(): Application? {
            return sInstance
        }
    }

}
