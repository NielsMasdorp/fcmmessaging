package messaging.capaxit.nl.fcmmessaging;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

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
    }

    private void unsubscribe() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topicName.getText().toString());
    }

    private void subscribe() {
        FirebaseMessaging.getInstance().subscribeToTopic(topicName.getText().toString());
    }
}
