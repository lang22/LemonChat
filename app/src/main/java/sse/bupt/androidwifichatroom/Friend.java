package sse.bupt.androidwifichatroom;

import android.content.Context;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lang22 on 2019/7/10.
 */

public class Friend implements Serializable {
    private String name;

//    static class TxView extends android.support.v7.widget.AppCompatImageView implements Serializable{
//
//        public TxView(Context context) {
//            super(context);
//        }
//    }
//    private TxView tx;
    private List<MsgQ> msgList = new ArrayList<MsgQ>();

    public Friend(String name){
        this.name = name;

//        this.tx = (TxView) tx;
    }

    public Friend(String name,List<MsgQ> newList){
        this.name = name;
        if (newList!=null)
            this.msgList.addAll(newList);
    }
    public String getName(){
        return name;
    }

//    public ImageView getTx(){
//        return tx;
//    }

    public void setName(String newName){
        name = newName;
    }

//    public void setTx(ImageView newTx){
//        tx = (TxView)newTx;
//    }

    public List<MsgQ> getMsgList(){ return msgList; }

    public void setMsgList(List<MsgQ> newMsgList){
        if (newMsgList!=null)
            msgList.addAll(newMsgList);
    }

    private String Id = name+System.currentTimeMillis();

    public String getId(){ return Id; }
}
