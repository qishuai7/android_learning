package com.alibaba.uilearning.activity.boardcase;

import com.alibaba.uilearning.activity.service.MyBaseService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, MyBaseService.class));
    }

}
