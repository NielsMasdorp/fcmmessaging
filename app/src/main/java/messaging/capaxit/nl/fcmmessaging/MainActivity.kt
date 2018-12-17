package messaging.capaxit.nl.fcmmessaging

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import rx.Completable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val unregisterObservable: Completable
        get() = Completable.create { s ->
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId()
                s.onCompleted()
            } catch (e: IOException) {
                s.onError(e)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MessagingChannels.createMessagingChannels(this)
        setContentView(R.layout.activity_main)
        subsribeToTopic.setOnClickListener { subscribe() }
        unsubscribeFromTopic.setOnClickListener { unsubscribe() }
        registerToFcm.setOnClickListener { registerToFcm() }
        unregisterFromFcm.setOnClickListener { unregisterFromFcm() }
        logFcmToken.setOnClickListener { Log.i(TAG, FirebaseInstanceId.getInstance().token) }
    }

    private fun unregisterFromFcm() {
        unregisterObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ throwable -> Log.e(TAG, "Error", throwable) }, { Log.i(TAG, "Unregistered") })
    }

    private fun registerToFcm() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.w(TAG, it.token)
        }
    }

    private fun unsubscribe() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topicName.text.toString())
    }

    private fun subscribe() {
        FirebaseMessaging.getInstance().subscribeToTopic(topicName.text.toString())
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
