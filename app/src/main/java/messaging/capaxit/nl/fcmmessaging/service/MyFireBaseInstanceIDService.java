package messaging.capaxit.nl.fcmmessaging.service;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jamiecraane on 03/04/2017.
 */

public class MyFireBaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFireBaseInstanceIDService.class.getSimpleName();
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.i(TAG, "onTokenRefresh");
    }
}
