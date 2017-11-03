package com.lilosoft.chablis.qkcredentials.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bigkoo.pickerview.OptionsPickerView
import com.google.gson.Gson
import com.lilosoft.chablis.qkcredentials.R
import com.lilosoft.chablis.qkcredentials.base.BaseActivity
import com.lilosoft.chablis.qkcredentials.base.RequestConstant
import com.lilosoft.chablis.qkcredentials.model.CJZ
import com.lilosoft.chablis.qkcredentials.model.Subject
import com.lilosoft.chablis.qkcredentials.rx.HttpMethod
import java.net.URL
import com.lilosoft.chablis.qkcredentials.rx.HttpResult
import com.lilosoft.chablis.qkcredentials.rx.ProgressSubscriber
import com.lilosoft.chablis.qkcredentials.rx.sub.BaseObserver
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread


class MainActivity : BaseActivity() {
    private val url ="http://10.41.110.210:8080/Platform-apiweb/main/getInterfaceData"
    private var pickerView: OptionsPickerView<*>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
//        tv.text = "测试接口"
//        var params= listOf(
//                "api_key" to "a4e3a832-7cd8-4569-8a6d-0302fef30004",
//                "api_secret" to "954288",
//                "method" to "com.fgj.GetFcxx",
//                "version" to 1.0,
//                "paramStr" to "username=whgsj@password=whgsj20170308@Cqzh=420111018006GB00017F00040002@XM=石文建")
//        Fuel.get(url, params).responseString{request, response, result ->
//            Log.d(TAG,request.toString())
//            Log.d(TAG,result.get())
//            Log.d(TAG,result.getAs())
//            tv.text = result.get()
//        }
        doAsync {
            var a=getCJZ("1.0.1","sfzh=420102195506280812","com.canlian.query.QueryCJRXX")
            uiThread {
                var gson=Gson()
                var b=gson.fromJson<CJZ>(a,CJZ::class.java)
                Log.d(TAG,b.returnBody)
            }
        }
//        var sub= object : BaseObserver<List<Subject>>(mActivity){
//            override fun onHandleSuccess(t: List<Subject>?) {
//                Log.d(TAG,t.toString())
//            }
//
//        }
//        initView()
//        var sub2= object : ProgressSubscriber<List<Subject>>(mActivity){
//            override fun onNext(t: HttpResult<List<Subject>>) {
//                Log.d(TAG,t.subjects.toString())
//            }
//
//            override fun onCancelProgress() {
//            }
//
//        }
//        HttpMethod.instance.getTopMovie(sub2)
//
//        tvCard.setOnClickListener{
//            pickerView?.show()
//        }
//        btn_check.setOnClickListener {
//            startActivity(Intent(mActivity,CredentialDetailActivity::class.java))
//        }
    }

    fun initView(){
//        var list= listOf<String>("1","2","3")
        var list=listOf<Int>(1,2,3)
        pickerView = OptionsPickerView.Builder(mActivity, OptionsPickerView.OnOptionsSelectListener { options1, options2, options3, v ->

        }).setSelectOptions(0)
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .build()
        pickerView?.setPicker(list)
    }

    fun getCJZ(version: String,paramStr: String,method:String): String {
        var str = URL("http://219.140.178.216:8085/ZZQD/user/getInterfaceData?paramStr=$paramStr&api_key=${RequestConstant.api_key}&api_secret=${RequestConstant.api_secret}&method=$method&version=$version").readText()
        return str
    }
}
