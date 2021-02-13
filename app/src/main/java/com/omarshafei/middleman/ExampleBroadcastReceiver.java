package com.omarshafei.middleman;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ExampleBroadcastReceiver extends BroadcastReceiver {
    ArrayList<String> arrayList = new ArrayList<>();
    private OnReceivingInformation onReceivingInformation;
    @Override
    public void onReceive(Context context, Intent intent) {

        if ("com.omarshafei.EXAMPLE_ACTION".equals(intent.getAction())) {
            //this is the data received in arraylist
            arrayList = intent.getStringArrayListExtra("com.omarshafei.EXTRA_TEXT");

            onReceivingInformation.onReceivingInformation(arrayList.get(2));
            /////////////////////////////////////////
        }
        try{
            Socket s=new Socket("localhost",6666);
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Server");
            dout.flush();
            dout.close();
            s.close();
        } catch(Exception e){System.out.println(e.toString());}
    }

    public interface OnReceivingInformation {
        void onReceivingInformation(String text);
    }

    public void registerView(OnReceivingInformation onReceivingInformation) {
        this.onReceivingInformation = onReceivingInformation;

    }
}
