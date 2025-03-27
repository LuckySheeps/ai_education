package com.ai_education.service.Impl;

import cn.hutool.json.JSONArray;
import com.ai_education.mapper.CurrencyMapper;
import com.ai_education.pojo.Currency;
import com.ai_education.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Value("${xunfei.appid}")
    String APPID;
    // 接口密钥
    @Value(("${xunfei.currencyApikey}"))
    String API_KEY;
    @Autowired
    CurrencyMapper currencyMapper;
    public ArrayList<Currency> getbyids(JSONArray labels) {


        ArrayList<Currency> currencies = new ArrayList<>();


                for (Object o : labels) {
                    Currency currency = currencyMapper.selectById(o.toString());
                    currencies.add(currency);
        }
        return currencies;
    }
}
