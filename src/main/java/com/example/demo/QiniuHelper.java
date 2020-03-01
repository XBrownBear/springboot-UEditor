package com.example.demo;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;
import java.util.UUID;

/**
 * @author brown
 * @create 2020-02-27  18:17
 */
public class QiniuHelper {
    /**
     * 空间名
     */
    private static String Scope = "hexo-next-xc";

    /**
     * 域名
     */
    private static String Url = "images.xiongchang.vip";

    /**
     * 公钥
     */
    private static String ACCESS_KEY = "Ek96H4HJY42werQLtEhVN1sbJOv18S9qrTb-XlLs";

    private static String SECRET_KEY = "8zaIuYdgR_26489UB2Rcb43m9FMR3J-8cGM61Lmz";

    /**
     * 上传附件
     * @throws QiniuException
     */
    public static String UploadFile(File file) throws QiniuException {

        String key = UUID.randomUUID().toString().replaceAll("-", "");

        Configuration cfg = new Configuration(Zone.zone2());

        try {
            //创建上传对象
            UploadManager uploadManager = new UploadManager(cfg);

            //密钥配置
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

            //上传文件
            Response res = uploadManager.put(file, key, auth.uploadToken(Scope, key));
        } catch (Exception e) {
            return "";
        }
        return key;
    }

    /**
     * 获得url地址
     */
    public static String GetUrl(String key)
    {
        return String.format ("http://%s/%s", Url, key);
    }
}
