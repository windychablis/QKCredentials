package com.lilosoft.chablis.qkcredentials.base

import android.app.FragmentManager
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

import com.kaopiz.kprogresshud.KProgressHUD

import java.io.Serializable

open class BaseActivity : AppCompatActivity() {
    protected var appContext: AppContext? = null
    protected var mActivity: BaseActivity? = null
    protected var mFragmentManager: FragmentManager? = null
    protected var hud: KProgressHUD? = null
    protected var TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        appContext = AppContext.get() as? AppContext
        mFragmentManager = fragmentManager
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onResume() {
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        super.onResume()
    }


    fun nextActivity(clazz: Class<*>, isPlayAnim: Boolean, name: String?, s: Serializable?) {
        val intent = Intent()
        intent.setClass(this, clazz)
        if (null != name && name.trim { it <= ' ' } != "") {
            intent.putExtra(name, s)
        }
        startActivity(intent)
        if (isPlayAnim) {
            //            overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        }
    }

    @JvmOverloads
    fun nextActivity(clazz: Class<*>, isPlayAnim: Boolean = true) {
        nextActivity(clazz, true, null, null)
    }

    fun nextActivity(clazz: Class<*>, name: String, s: Serializable) {
        nextActivity(clazz, true, name, s)
    }


}
