package messaging.capaxit.nl.fcmmessaging

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by jamiecraane on 03/05/2017.
 */
class MessageDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val holder = LinearLayout(this)
        holder.orientation = LinearLayout.VERTICAL
        val label = TextView(this)
        val type = intent.getStringExtra(INTENT_NOTIFICATION_TYPE)
        label.text = "Notification clicked with type $type"
        holder.addView(label)
        setContentView(holder)
    }

    companion object {
        const val INTENT_NOTIFICATION_TYPE = "message.capaxit.nl.fcmmessaging.NOTIFICATION_TYPE"
    }
}
