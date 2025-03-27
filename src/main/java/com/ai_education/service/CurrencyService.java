package com.ai_education.service;

import cn.hutool.json.JSONArray;
import com.ai_education.pojo.Currency;

import java.util.ArrayList;

public interface CurrencyService {
    ArrayList<Currency> getbyids(JSONArray labels);
}
