package com.example.coursehelper.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

public class SPUtils {

    private static SPUtils instance;
    private Context mCtx;
    //保存在手机里面的文件名
    public String mFileName = "share_data";

    private SPUtils(Context context, String filename){
        mCtx = context;
        this.mFileName = filename;
    }

    public static SPUtils init(Context context, String mFileName){
        if(instance == null){
            synchronized (SPUtils.class){
                if(instance == null){
                    instance = new SPUtils(context,mFileName);
                }
            }
        }
        return instance;
    }

    public static SPUtils getInstance(){
        if(instance == null){
            throw new IllegalStateException(
                    "you should can getInstance(Context context, String fileName)");
        }
        return instance;
    }

    //保存数据的方法，我们需要拿到保持数据的类型，
    // 然后根据类型调用不同的保存方法
    //TODO 后台接口？
    public void put(String key, Object object){
        SharedPreferences sp = mCtx.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if(object instanceof String){
            editor.putString(key, (String) object);
        }else if(object instanceof Integer){
            editor.putInt(key, (Integer)object);
        }else if(object instanceof Boolean){
            editor.putBoolean(key, (Boolean)object);
        }else if(object instanceof Float){
            editor.putFloat(key, (Float)object);
        }else if(object instanceof Long){
            editor.putLong(key, (Long)object);
        }else{
            editor.putString(key, object.toString());
        }
    }
}
