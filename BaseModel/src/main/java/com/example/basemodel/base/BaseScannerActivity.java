package com.example.basemodel.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * @auther: pengwang
 * @date: 2023/5/6
 * @description:
 */
public abstract class BaseScannerActivity extends BaseModelActivity {

    //扫描条码服务广播
    //Scanning barcode service broadcast.
    public static final String SCN_CUST_ACTION_SCODE = "com.android.server.scannerservice.broadcast";
    public static final String SCN_CUST_EX_SCODE = "scannerdata";

    private BroadcastReceiver mScanDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (SCN_CUST_ACTION_SCODE.equals(intent.getAction())) {
                try {
                    String result = intent.getStringExtra(SCN_CUST_EX_SCODE);
                    onScannerData(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SCN_CUST_ACTION_SCODE);
//        intentFilter.addAction("com.rscja.scanner.action.scanner.RFID");
        registerReceiver(mScanDataReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mScanDataReceiver);
    }

    abstract void onScannerData(String data);

}
