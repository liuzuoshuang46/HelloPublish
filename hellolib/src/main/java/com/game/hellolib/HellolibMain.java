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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HellolibMain {
    private static final String AP_ID = "1340001";
    private static final String CP_ID = "2940027";
    private static final String API_KEY = "7970032";
    private static Activity m_activity = null;
    private static Context _activity = null;


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
//    public void in()
//    {
//        SDKManager.getsInstance().initAriesPay(m_activity, AP_ID, CP_ID, API_KEY,true, new HelloInitResultCallBack() {
//            @Override public void onSuccess(List<HelloSupportPayInfoEntity> list, boolean supportOnlinePay, HelloCountryCurrencyData countryCurrencyData) {
//
//                Log.i(TAG,"onSuccess:2:"+list.size());
////                Log.i(TAG,"onSuccess:3:"+list.get(0).mcc);
////                Log.i(TAG,"onSuccess:4:"+list.get(0).mnc);
//
////                Log.i(TAG,"onSuccess:6:"+list.get(0).priceEntities.size());
////                Log.i(TAG,"onSuccess:7:"+list.get(0).priceEntities.get(0).price);
////                Log.i(TAG,"onSuccess:8:"+list.get(0).priceEntities.get(0).currency);
//
//                Log.i(TAG,"onSuccess:"+supportOnlinePay);
//                Log.i(TAG,"onSuccess:"+countryCurrencyData.countryCode + "	"+countryCurrencyData.currency + "		"+countryCurrencyData.mcc);
////                instance.m_countryCurrencyData = countryCurrencyData;
//
//                //获取有效最小资费
//                if(list.size() > 0 && list.get(0).priceEntities.size() > 0)
//                {
////                    instance.getMinToPrice(list.get(0).priceEntities);
//                }
//
//
//            }
//            @Override public void onFail(int code) {
//                Log.i(TAG,"onFail:"+code);
//                if(code == 100)
//                {
////					AdView.this.loadAd("");
//                }
//            }
//        });
//
//
//
//        HelloStartPayEntity startPayEntity = new HelloStartPayEntity();
////        startPayEntity.amount =  instance.costPrice;//1.0;//price amount
////        startPayEntity.countryCode = instance.m_countryCurrencyData.countryCode;//"CN";
////        startPayEntity.currency = instance.m_countryCurrencyData.currency;//"CNY";
////        startPayEntity.netPaySp = "card";//指定在线支付的渠道
////        startPayEntity.orderNum = "1";//order number
////        startPayEntity.payMode = payModeValue;//在线支付
////        startPayEntity.matchDown = true;//支持向下取金额的功能，当支付方式支持的金额小 于发起支付的金额，开启这个功能可以匹配该支付方式。默认为false。
////        startPayEntity.adjustMode = BigDecimal.ROUND_UP;//设置adjustMode会根据设置 的mode，结合对应的币种来处理传入的金额各个位数取整，以防传入
////        startPayEntity.serviceConfigPriority = true;
//
//
//
////        Log.i(TAG,"下单amount:"+instance.costPrice);
////        Log.i(TAG,"下单countryCode:"+instance.m_countryCurrencyData.countryCode);
////        Log.i(TAG,"下单currency:"+instance.m_countryCurrencyData.currency);
//        try{
//            SDKManager.getsInstance().startPay(_activity, startPayEntity, new HelloStartPayCallBack() {
//                @Override
//                public void onOrderCreated(HelloOrderEntity orderEntity) {
//                    //订单创建成功，进入开始支付，如果后续因为崩溃等原因没有收到结果，可以根据这 里的订单号查询订单结果
//
//                    Log.i(TAG,"onOrderCreated:平台的订单号:"+orderEntity.orderNum);
//                    Log.i(TAG,"onOrderCreated:商户的订单号:"+orderEntity.cpOrderNum);
//                    //				_activity.showT("订单创建成功");
//                    //					_activity.changeOrderNum(orderEntity.orderNum);
//                }
//
//                @Override
//                public void onPaySuccess(HelloOrderEntity orderEntity) {
//                    //支付成功
//                    Log.i(TAG,"onPaySuccess:"+orderEntity);
//                    //				_activity.showT("支付成功");
////                    _activity.adCallBack(false);
//                    //				_activity.changeOrderNum(orderEntity.orderNum);
//                }
//
//                @Override
//                public void onPaying(HelloOrderEntity orderEntity) {
//                    //本地查询超时，后续游戏去服务端确认
//                    Log.i(TAG,"onPaying:"+orderEntity);
//                    //				_activity.showT("本地查询超时");
////                    if(payModeValue == HelloStartPayEntity.PAY_MODE_SMS)
////                    {
////                        startPay(HelloStartPayEntity.PAY_MODE_ALL);
////                    }
////                    else
////                    {
////                        _activity.adCallBack(true);
////                    }
//
//                }
//
//                @Override
//                public void onPayFail(int code, HelloOrderEntity payMode) {
//                    //支付失败，code码说明见下方码表
//                    Log.i(TAG,"onPayFail:"+code);
//                    //				_activity.showT("onPayFail:"+code);
////                    if(payModeValue == HelloStartPayEntity.PAY_MODE_SMS)
////                    {
////                        startPay(HelloStartPayEntity.PAY_MODE_ALL);
////                    }
////                    else
////                    {
////                        _activity.adCallBack(true);
////                    }
//                }
//            });
//        }
//        catch (Exception e){
//        }
//
//    }

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
                var8.onSuccess(list,supportOnlinePay,countryCurrencyData);
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
    public  void startPay(Activity var1, HelloStartPayEntity var2, HelloStartPayCallBack var3)  {

        StartPayEntity startPayEntity = new StartPayEntity();
        startPayEntity.amount =  var2.amount;//instance.costPrice;//1.0;//price amount
        startPayEntity.countryCode = var2.countryCode;//instance.m_countryCurrencyData.countryCode;//"CN";
        startPayEntity.currency = var2.currency;//instance.m_countryCurrencyData.currency;//"CNY";
        startPayEntity.netPaySp = "card";//指定在线支付的渠道
        startPayEntity.orderNum = "1";//order number
        startPayEntity.payMode = StartPayEntity.PAY_MODE_SMS;//在线支付
        startPayEntity.matchDown = true;//支持向下取金额的功能，当支付方式支持的金额小 于发起支付的金额，开启这个功能可以匹配该支付方式。默认为false。
        startPayEntity.adjustMode = BigDecimal.ROUND_UP;//设置adjustMode会根据设置 的mode，结合对应的币种来处理传入的金额各个位数取整，以防传入
        startPayEntity.serviceConfigPriority = true;
        try{
             PaySDKManager.getsInstance().startPay(var1, startPayEntity, new StartPayCallBack() {
                @Override
                public void onOrderCreated(OrderEntity orderEntity) {
                    //订单创建成功，进入开始支付，如果后续因为崩溃等原因没有收到结果，可以根据这 里的订单号查询订单结果
                    Log.i(TAG,"onOrderCreated:平台的订单号:"+orderEntity.orderNum);
                    Log.i(TAG,"onOrderCreated:商户的订单号:"+orderEntity.cpOrderNum);
                    var3.onOrderCreated(orderEntity);
                }

                @Override
                public void onPaySuccess(OrderEntity orderEntity) {
                    //支付成功
                    Log.i(TAG,"onPaySuccess:"+orderEntity);
                    var3.onPaySuccess(orderEntity);
                }

                @Override
                public void onPaying(OrderEntity orderEntity) {
                    //本地查询超时，后续游戏去服务端确认
                    Log.i(TAG,"onPaying:"+orderEntity);
                    var3.onPaying(orderEntity);
                }

                @Override
                public void onPayFail(int code, OrderEntity payMode) {
                    //支付失败，code码说明见下方码表
                    Log.i(TAG,"onPayFail:"+code);
                    var3.onPayFail(code,payMode);
                }
            });
        }
        catch (Exception e){

        }
    }
}
