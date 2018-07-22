package com.example.coursehelper.net;

import android.util.Log;

import com.example.coursehelper.utils.GsonUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

public abstract class CommonCallBack<T> extends StringCallback {

    Type mType;
    public CommonCallBack(){
        Class<? extends  CommonCallBack> clazz = getClass();
        Type genericSuperclass = clazz.getGenericSuperclass();

        if(genericSuperclass instanceof Class){
            throw new RuntimeException("Miss Type Params");
        }

        ParameterizedType parameterizedType =
                (ParameterizedType) genericSuperclass;
        mType = parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onError(e);
    }

    @Override
    public void onResponse(String response, int id) {

        try {
            JSONObject resp = new JSONObject(response);
            int resultCode = resp.getInt("resultCode");

            if(resultCode == 1){ //成功
                String data = resp.getString("data");

                Log.i("TOAST",mType.toString());
                Log.i("TOAST",data);
                T result = GsonUtil.getGson().fromJson(data,mType);

                onSuccess(result);

            }else{
                onError(new RuntimeException(resp.getString("resultMessage")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onError(e);
        }
    }
    public abstract void onError(Exception e);
    public abstract void onSuccess(T response);
}
