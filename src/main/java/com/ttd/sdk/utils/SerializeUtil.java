package com.ttd.sdk.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * author wt
 * createDate 2018/7/23
 */

public class SerializeUtil {
    public static Object deepCopy(Object o){
        Object obj = null;
//      //先序列化，写入到流里
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(o);
            //然后反序列化，从流里读取出来，即完成复制
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
