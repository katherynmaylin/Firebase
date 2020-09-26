package android.lsi.firebasecloudmessagingdemo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseIntanceIdServiceDemo extends FirebaseMessagingService {

    private NotificationManager notificationManager;

    String channelId = "com.example.firebasecloundmessagingdemo";


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("NEW_TOKEN", s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("FirebaseIntanceIdServiceDemo", remoteMessage.getNotification().getBody());

        sendNotification(remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody());

    }

    private void sendNotification(String titulo, String contenido){
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int id = 100;

        Intent intent = new Intent(this, ResultActiivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Mi titulo")
                .setContentText("Esto es una notificacion local creada por la misma app")
                .setChannelId(channelId)
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.ic_dialog_info, "Abrir", pendingIntent)
                .build();

        notificationManager.notify(id, notification);

    }
}
