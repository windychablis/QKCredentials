package com.lilosoft.chablis.qkcredentials.rx.sub;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lilosoft.chablis.qkcredentials.rx.HttpResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by chablis on 2017/10/27.
 */

public abstract class BaseObserver<T> implements Observer<HttpResult<T>> {

    private static final String TAG = "BaseObserver";
    private Context mContext;

    protected BaseObserver(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        Log.d(TAG, "tHttpResult:" + tHttpResult);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        Log.d(TAG, "ooo");
    }

    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
