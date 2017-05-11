package messaging.capaxit.nl.fcmmessaging;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import rx.Completable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText topicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topicName = (EditText) findViewById(R.id.topicName);
        findViewById(R.id.subsribeToTopic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                subscribe();
            }
        });
        findViewById(R.id.unsubscribeFromTopic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                unsubscribe();
            }
        });
        findViewById(R.id.registerToFcm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                registerToFcm();
            }
        });
        findViewById(R.id.unregisterFromFcm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                unregisterFromFcm();
            }
        });
        findViewById(R.id.logFcmToken).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i(TAG, FirebaseInstanceId.getInstance().getToken());
            }
        });
    }

    private void unregisterFromFcm() {
        getUnregisterObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(throwable -> Log.e(TAG, "Error", throwable), () -> Log.i(TAG, "Unregistered"));
    }

    @NonNull
    private Completable getUnregisterObservable() {
        return Completable.create(s -> {
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId();
                s.onCompleted();
            } catch (IOException e) {
                s.onError(e);
            }
        });
    }

    private void registerToFcm() {
        FirebaseInstanceId.getInstance().getToken();
    }

    private void unsubscribe() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topicName.getText().toString());
    }

    private void subscribe() {
        FirebaseMessaging.getInstance().subscribeToTopic(topicName.getText().toString());
    }
}
