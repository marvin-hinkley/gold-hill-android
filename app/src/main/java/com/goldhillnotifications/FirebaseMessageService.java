package com.goldhillnotifications;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessageService extends com.google.firebase.messaging.FirebaseMessagingService {
    public FirebaseMessageService() {
    }

    @Override
    public void onNewToken(String token) {
        Log.d("tag", "Refreshed token: " + token);
        sendMessageToActivity(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage message) {
        Log.d("tag", "From: " + message.getFrom());

        if (message.getData().size() > 0) {
            Log.d("tag", "Message payload: " + message.getData());
        }

        if (message.getNotification() != null) {
            String messageContent = message.getNotification().getBody();
            Log.d("tag", "Message notification: " + messageContent);
            sendMessageToActivity(messageContent);
        }
    }

    private void sendMessageToActivity(String message) {
        Intent intent = new Intent();
        intent.putExtra("message", message);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
