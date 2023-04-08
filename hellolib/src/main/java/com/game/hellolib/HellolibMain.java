package com.game.hellolib;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;
import android.util.Log;

//import com.transsion.pay.paysdk.manager.PaySDKManager;
//import com.transsion.pay.paysdk.manager.entity.ConvertPriceInfo;
//import com.transsion.pay.paysdk.manager.entity.StartPayEntity;
//import com.transsion.pay.paysdk.manager.inter.CurrencyConvertCallBack;
//import com.transsion.pay.paysdk.manager.inter.InitResultCallBack;
//import com.transsion.pay.paysdk.manager.inter.StartPayCallBack;

import com.transsion.pay.paysdk.manager.PaySDKManager;
import com.transsion.pay.paysdk.manager.entity.ConvertPriceInfo;
import com.transsion.pay.paysdk.manager.entity.CountryCurrencyData;
import com.transsion.pay.paysdk.manager.entity.StartPayEntity;
import com.transsion.pay.paysdk.manager.entity.SupportPayInfoEntity;
import com.transsion.pay.paysdk.manager.inter.CurrencyConvertCallBack;
import com.transsion.pay.paysdk.manager.inter.InitResultCallBack;
import com.transsion.pay.paysdk.manager.inter.StartPayCallBack;

import java.util.ArrayList;
import java.util.List;

public class HellolibMain {
    private static final String AP_ID = "1340001";
    private static final String CP_ID = "2940027";
    private static final String API_KEY = "7970032";
    private static Activity m_activity = null;
    private static Context _activity = null;

    public static CountryCurrencyData m_countryCurrencyData = null;

    private String TAG = "HellolibMain3";
    private static  HellolibMain _instance = null;
    public static HellolibMain getsInstance(Activity activity) {
        if(HellolibMain._instance == null)
        {
            HellolibMain._instance = new HellolibMain();
        }
        m_activity = activity;
        return HellolibMain._instance;
    }
    private HellolibMain() {
        Log.e(TAG,"HellolibMain");
    }

    public void setStrict(boolean var1)
    {
        Log.e(TAG,"setStrict");
        //设置严格模式，默认true，设置true后，经过多次查询后，支付中也会返回失败
        PaySDKManager.getsInstance().setStrict(var1);
    }
    public void in()
    {
        PaySDKManager.getsInstance().initAriesPay(m_activity, AP_ID, CP_ID, API_KEY,true, new InitResultCallBack() {
            @Override public void onSuccess(List<SupportPayInfoEntity> list, boolean supportOnlinePay, CountryCurrencyData countryCurrencyData) {

                Log.i(TAG,"onSuccess:2:"+list.size());
//                Log.i(TAG,"onSuccess:3:"+list.get(0).mcc);
//                Log.i(TAG,"onSuccess:4:"+list.get(0).mnc);

//                Log.i(TAG,"onSuccess:6:"+list.get(0).priceEntities.size());
//                Log.i(TAG,"onSuccess:7:"+list.get(0).priceEntities.get(0).price);
//                Log.i(TAG,"onSuccess:8:"+list.get(0).priceEntities.get(0).currency);

                Log.i(TAG,"onSuccess:"+supportOnlinePay);
                Log.i(TAG,"onSuccess:"+countryCurrencyData.countryCode + "	"+countryCurrencyData.currency + "		"+countryCurrencyData.mcc);
//                instance.m_countryCurrencyData = countryCurrencyData;

                //获取有效最小资费
                if(list.size() > 0 && list.get(0).priceEntities.size() > 0)
                {
//                    instance.getMinToPrice(list.get(0).priceEntities);
                }


            }
            @Override public void onFail(int code) {
                Log.i(TAG,"onFail:"+code);
                if(code == 100)
                {
//					AdView.this.loadAd("");
                }
            }
        });
    }

    public void initAriesPay(Context var1, String var2, String var3, String var4, InitResultCallBack var5) {
        Log.e(TAG,"initAriesPay");
        this.initAriesPay(var1, "com.transsion.pay.ariessdk.demo", var2, var3, var4, 2, true, var5);
    }

    public void initAriesPay(Context var1, String var2, String var3, String var4, boolean var5, InitResultCallBack var6) {
        this.initAriesPay(var1, "com.transsion.pay.ariessdk.demo", var2, var3, var4, 2, var5, var6);
    }

    public void initAriesPay(Context var1, String var2, String var3, String var4, int var5, InitResultCallBack var6) {
        this.initAriesPay(var1, "com.transsion.pay.ariessdk.demo", var2, var3, var4, var5, true, var6);
    }

    public void initAriesPay(Context var1, String var2, String var3, String var4, String var5, InitResultCallBack var6) {

        this.initAriesPay(var1, var5, var2, var3, var4, 2, true, var6);
    }
    public synchronized void initAriesPay(Context var1, String var2, String var3, String var4, String var5, int var6, boolean var7, InitResultCallBack var8) {
        Log.e(TAG,"initAriesPay");
        PaySDKManager.getsInstance().initAriesPay(var1, var2, var3, var4, var5, var6, var7, var8);
    }
    //美元转本位币
    public void convertUsdToLocal(Context var1, List<ConvertPriceInfo> var2, CurrencyConvertCallBack var3)
    {
        PaySDKManager.getsInstance().convertUsdToLocal(var1,var2,var3);

    }
    //虚拟币转本位币
    public void convertVirtualToLocal(Context var1, List<ConvertPriceInfo> var2, CurrencyConvertCallBack var3)
    {
        PaySDKManager.getsInstance().convertVirtualToLocal(var1,var2,var3);
    }
    //发起在线支付或者短代支付
    public String startPay(Activity var1, StartPayEntity var2, StartPayCallBack var3) throws Exception {
        return PaySDKManager.getsInstance().startPay(var1,var2,var3);
    }
}
