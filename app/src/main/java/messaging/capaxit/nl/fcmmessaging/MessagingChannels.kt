package messaging.capaxit.nl.fcmmessaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object MessagingChannels {
    const val DEFAULT_CHANNEL_ID = "defaultChannel"
    const val DEFAULT_CHANNEL_NAME = "Default messaging channel"

    fun createMessagingChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).let { notificationManager ->
                notificationManager.createNotificationChannel(
                        NotificationChannel(DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
                                .apply {
                                    description = "This is de detault notification channel."
                                }
                )
            }
        }
    }
}