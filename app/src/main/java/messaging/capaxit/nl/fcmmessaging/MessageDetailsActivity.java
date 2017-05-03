package messaging.capaxit.nl.fcmmessaging;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jamiecraane on 03/05/2017.
 */

public class MessageDetailsActivity extends AppCompatActivity {
    public static final String INTENT_NOTIFICATION_TYPE = "message.capaxit.nl.fcmmessaging.NOTIFICATION_TYPE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout holder = new LinearLayout(this);
        holder.setOrientation(LinearLayout.VERTICAL);
        final TextView label = new TextView(this);
        final String type = getIntent().getStringExtra(INTENT_NOTIFICATION_TYPE);
        label.setText("Notification clicked with type " + type);
        holder.addView(label);
        setContentView(holder);
    }
}
