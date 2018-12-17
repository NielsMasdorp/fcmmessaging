package messaging.capaxit.nl.fcmmessaging.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import messaging.capaxit.nl.fcmmessaging.MessageDetailsActivity
import messaging.capaxit.nl.fcmmessaging.MessagingChannels
import messaging.capaxit.nl.fcmmessaging.R

/**
 * Created by jamiecraane on 03/04/2017.
 */

class MyFireBaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "from = " + remoteMessage!!.from!!)
        Log.i(TAG, "token = " + FirebaseInstanceId.getInstance().token!!)

        val remoteNotification = remoteMessage.notification
        val title = if (remoteNotification == null || remoteNotification.title == null) "Testbericht" else remoteNotification.title
        val text = if (remoteNotification == null || remoteNotification.body == null) "Testinhoud" else remoteNotification.body

        val messageDetailsIntent = Intent(this, MessageDetailsActivity::class.java)
        if (remoteMessage.data != null) {
            messageDetailsIntent.putExtra(MessageDetailsActivity.INTENT_NOTIFICATION_TYPE, remoteMessage.data["type"])
        }

        val notification = NotificationCompat.Builder(this, MessagingChannels.DEFAULT_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this, 0, messageDetailsIntent, PendingIntent.FLAG_CANCEL_CURRENT))
                .setSmallIcon(R.mipmap.star)
                .build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    override fun onNewToken(token: String) {
        Log.i(TAG, "onTokenRefresh")
        Log.i(TAG, "token = $token")
    }

    companion object {
        private val TAG = MyFireBaseMessagingService::class.java.simpleName
    }
}
