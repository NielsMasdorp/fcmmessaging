package messaging.capaxit.nl.fcmmessaging.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import messaging.capaxit.nl.fcmmessaging.R;

/**
 * Created by jamiecraane on 03/04/2017.
 */

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFireBaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        Log.d(TAG, "from = " + remoteMessage.getFrom());
        Log.i(TAG, "token = " + FirebaseInstanceId.getInstance().getToken());

        RemoteMessage.Notification remoteNotification = remoteMessage.getNotification();

        String title = ((remoteNotification == null) || (remoteNotification.getTitle() == null)) ? "Testbericht" : remoteNotification.getTitle();
        String text = ((remoteNotification == null) || (remoteNotification.getBody() == null)) ? "Testinhoud" : remoteNotification.getBody();

        final Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.mipmap.notification_icon)
                .build();
        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
