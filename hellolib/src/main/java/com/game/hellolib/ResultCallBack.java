package com.game.hellolib;

import com.transsion.pay.paysdk.manager.entity.CountryCurrencyData;
import com.transsion.pay.paysdk.manager.entity.SupportPayInfoEntity;

import java.util.List;

public interface ResultCallBack  {
    void onSuccess(CountryCurrencyData var2);
    void onFail(int var1);
}
