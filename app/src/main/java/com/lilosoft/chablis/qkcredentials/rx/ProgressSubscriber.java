package com.lilosoft.chablis.qkcredentials.rx;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by chablis on 2017/10/26.
 */

public abstract class ProgressSubscriber<T> implements ProgressCancelListener, Observer<HttpResult<T>> {
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    private Disposable disposable;



    @Override
    public void onCancelProgress() {
        Log.d("MainActivity","quxiao");
        disposable.dispose();
    }

    public ProgressSubscriber(Context context) {
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    public void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable=d;
    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {

    }

    @Override
    public void onError(Throwable e) {

    }
    //    @Override
//    public void onCancelProgress() {
//        if (!this.isUnsubscribed()) {
//            this.unsubscribe();
//        }
//    }
}
