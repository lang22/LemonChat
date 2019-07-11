package sse.bupt.androidwifichatroom;

import android.widget.EditText;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Littledva on 7/7/2019.
 */

public class ServerSocketHelper {
    private ChatActivity chatActivity;
    private int port;

    ServerSocketHelper(ChatActivity chatActivity, int port){
        this.chatActivity = chatActivity;
        this.port = port;
    }

    private void broadcast(String msg, int port) throws IOException{
        DatagramSocket datagramSocket = new DatagramSocket();
        String address = "";
        if(address.isEmpty()){
            address = "255.255.255.255";
        }
        if(chatActivity!=null) {
            chatActivity.log("Broadcast to address: " + address);
        }
        DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
                InetAddress.getByName(address), port);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }

    void broadcastMessage(final String msg) {
        new Thread() {
            public void run() {
                try {
                    broadcast(msg, port);
                    if(chatActivity!=null) {
                        chatActivity.log("Broadcast: " + msg);
                    }
                } catch (Exception e) {
                    if(chatActivity!=null) {
                        chatActivity.log("Broadcast exception: " + e.toString());
                    }
                }
            }
        }.start();
    }

    public void sendReply(String msg, String ip){
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            String address = ip;
            if(chatActivity!=null) {
                chatActivity.log("Send reply to address: " + address);
            }
            DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
                    InetAddress.getByName(address), port);
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
        } catch (Exception e) {
            if(chatActivity!=null) {
                chatActivity.log("Reply exception: " + e.toString());
            }
        }
    }

    public void sendInit(String msg){
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            String address = "255.255.255.255";
            if(chatActivity!=null) {
                chatActivity.log("Send init to address: " + address);
            }
            DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
                    InetAddress.getByName(address), port);
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
        } catch (Exception e) {
            if(chatActivity!=null) {
                chatActivity.log("Init exception: " + e.toString());
            }
        }
    }
}
