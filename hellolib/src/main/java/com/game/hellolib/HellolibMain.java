package com.game.hellolib;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;
import android.util.Log;

import com.transsion.pay.paysdk.manager.PaySDKManager;
import com.transsion.pay.paysdk.manager.entity.ConvertPriceInfo;
import com.transsion.pay.paysdk.manager.entity.CountryCurrencyData;
import com.transsion.pay.paysdk.manager.entity.OrderEntity;
import com.transsion.pay.paysdk.manager.entity.StartPayEntity;
import com.transsion.pay.paysdk.manager.entity.SupportPayInfoEntity;
import com.transsion.pay.paysdk.manager.inter.CurrencyConvertCallBack;
import com.transsion.pay.paysdk.manager.inter.InitResultCallBack;
import com.transsion.pay.paysdk.manager.inter.StartPayCallBack;
import com.transsion.pay.paysdk.manager.testmode.SMSTestUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HellolibMain {


    private String TAG = "HellolibMain3";
    private static  HellolibMain _instance = null;
    public static HellolibMain getsInstance() {
        if(HellolibMain._instance == null)
        {
            HellolibMain._instance = new HellolibMain();
        }
        return HellolibMain._instance;
    }
    private HellolibMain() {
        Log.e(TAG,"HellolibMain");
    }

    //国家代码:IQ;  货币:IQD;   mcc:418;
    public void setTestMode()
    {
        SMSTestUtil.setTestMode(true);//设置true时表示开启测试模式，并且是双卡模式，默认关闭
        SMSTestUtil.setTestMCCMNC("41820");//这里设置的是第一张卡的mccmnc
        SMSTestUtil.setTestMCCMNC2("405880");//这里是设置第二张卡的mccmnc
    }

    public void setStrict(boolean var1)
    {
        Log.e(TAG,"setStrict");
        //设置严格模式，默认true，设置true后，经过多次查询后，支付中也会返回失败
        PaySDKManager.getsInstance().setStrict(var1);
    }
    public void initAriesPay(Context var1, String var2, String var3, String var4,ResultCallBack var5) {
        Log.e(TAG,"initAriesPay");
        this.initAriesPay(var1, "com.transsion.pay.ariessdk.demo", var2, var3, var4, 2, true, var5);
    }

    public void initAriesPay(Context var1, String var2, String var3, String var4, boolean var5, ResultCallBack var6) {
        this.initAriesPay(var1, "com.transsion.pay.ariessdk.demo", var2, var3, var4, 2, var5, var6);
    }

    public void initAriesPay(Context var1, String var2, String var3, String var4, int var5, ResultCallBack var6) {
        this.initAriesPay(var1, "com.transsion.pay.ariessdk.demo", var2, var3, var4, var5, true, var6);
    }

    public void initAriesPay(Context var1, String var2, String var3, String var4, String var5,ResultCallBack var6) {

        this.initAriesPay(var1, var5, var2, var3, var4, 2, true, var6);
    }
    public synchronized void initAriesPay(Context var1, String var2, String var3, String var4, String var5, int var6, boolean var7, ResultCallBack var8) {
        Log.e(TAG,"initAriesPay");
        PaySDKManager.getsInstance().initAriesPay(var1, var2, var3, var4,var5,var6, var7,new InitResultCallBack() {
            @Override public void onSuccess(List<SupportPayInfoEntity> list, boolean supportOnlinePay, CountryCurrencyData countryCurrencyData) {

                var8.onSuccess(countryCurrencyData);
            }
            @Override public void onFail(int code) {
                Log.i(TAG,"onFail:"+code);
                var8.onFail(code);
            }
        });
    }
    //美元转本位币
    public void convertUsdToLocal(Context var1, List<ConvertPriceInfo> var2,CurrencyConvertCallBack var3)
    {
        PaySDKManager.getsInstance().convertUsdToLocal(var1,var2,var3);

    }
    //虚拟币转本位币
    public void convertVirtualToLocal(Context var1, List<ConvertPriceInfo> var2, CurrencyConvertCallBack var3)
    {
        PaySDKManager.getsInstance().convertVirtualToLocal(var1,var2,var3);
    }
    //发起在线支付或者短代支付
    public  void startPay(Activity var1, HelloStartPayEntity var2, HelloStartPayCallBack var3) throws Exception  {

        StartPayEntity startPayEntity = new StartPayEntity();
        startPayEntity.amount =  var2.amount;
        startPayEntity.countryCode = var2.countryCode;
        startPayEntity.currency = var2.currency;
        startPayEntity.netPaySp = "card";//指定在线支付的渠道
        startPayEntity.orderNum = var2.orderNum;//order number
        startPayEntity.payMode = StartPayEntity.PAY_MODE_SMS;//在线支付
        startPayEntity.matchDown = true;//支持向下取金额的功能，当支付方式支持的金额小 于发起支付的金额，开启这个功能可以匹配该支付方式。默认为false。
        startPayEntity.adjustMode = BigDecimal.ROUND_UP;//设置adjustMode会根据设置 的mode，结合对应的币种来处理传入的金额各个位数取整，以防传入
        startPayEntity.serviceConfigPriority = true;

         PaySDKManager.getsInstance().startPay(var1, startPayEntity, new StartPayCallBack() {
            @Override
            public void onOrderCreated(OrderEntity orderEntity) {
                var3.onOrderCreated(orderEntity);
            }

            @Override
            public void onPaySuccess(OrderEntity orderEntity) {
                var3.onPaySuccess(orderEntity);
            }

            @Override
            public void onPaying(OrderEntity orderEntity) {
                var3.onPaying(orderEntity);
            }

            @Override
            public void onPayFail(int code, OrderEntity payMode) {
                var3.onPayFail(code,payMode);
            }
        });

    }
}
