package edu.slcc.daniellorenzo.colors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        final Bundle bundle = intent.getExtras();

        try {
            if(bundle != null){
                Log.d("MYLOG", "Bundle: " + bundle);

                // Get pdu from bundle.
                final Object[] pdus = (Object[]) bundle.get("pdus");
                // Get format of bundle.
                String format = bundle.getString("format");

                for (int i = 0; i < pdus.length; i++){
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    // SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[0]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    // String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.d("MYLOG", "phone: " + phoneNumber + "; message: " + message);

                    // Show Alert.
                    Toast.makeText(context, "Text Recieved from " + phoneNumber, Toast.LENGTH_LONG).show();

                    // SET up to store preferences * add context because getSharedPreferences() needs...

                    SharedPreferences preferences = context.getSharedPreferences("TEXT_MSGS", Context.MODE_PRIVATE);
                    // Start the preference editor.
                    SharedPreferences.Editor editor = preferences.edit();
                    // Get previous scores USING THE KEY!
                    Log.d("MYLOG", "TextedWord: " + message);
                    editor.putString("TextedWord", message);
                    editor.putString("TexterPhone", phoneNumber);
                    // Saves buffer.
                    editor.commit();
                }
            }

        } catch (Exception e){
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }
    }
}
