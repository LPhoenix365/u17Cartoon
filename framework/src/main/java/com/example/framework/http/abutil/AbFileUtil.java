/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.framework.http.abutil;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import com.example.framework.http.global.AbAppConfig;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc

/**
 * © 2012 amsoft.cn
 * 名称：AbFileUtil.java
 * 描述：文件操作类.
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-01-18 下午11:52:13
 */
public class AbFileUtil {

    /**
     * 默认APP根目录.
     */
    private static String downloadRootDir = null;

    /**
     * 默认下载图片文件目录.
     */
    private static String imageDownloadDir = null;

    /**
     * 默认下载文件目录.
     */
    private static String fileDownloadDir = null;

    /**
     * 默认缓存目录.
     */
    private static String cacheDownloadDir = null;

    /**
     * 默认下载数据库文件的目录.
     */
    private static String dbDownloadDir = null;

    /**
     * 剩余空间大于200M才使用SD缓存.
     */
    private static int freeSdSpaceNeededToCache = 200 * 1024 * 1024;

    /**
     * 描述：通过文件的网络地址从SD卡中读取图片，如果SD中没有则自动下载并保存.
     *
     * @param url           文件的网络地址
     * @param type          图片的处理类型（剪切或者缩放到指定大小，参考AbImageUtil类）
     *                      如果设置为原图，则后边参数无效，得到原图
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getBitmapFromSD(String url, int type, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = null;
        try {
            if (TextUtils.isEmpty(url)) {
                return null;
            }

            //SD卡不存在 或者剩余空间不足了就不缓存到SD卡了
            if (!isCanUseSD() || freeSdSpaceNeededToCache < freeSpaceOnSD()) {
                bitmap = getBitmapFromURL(url, type, desiredWidth, desiredHeight);
                return bitmap;
            }
            //下载文件，如果不存在就下载，存在直接返回地址
            String downFilePath = downloadFile(url, imageDownloadDir);
            if (downFilePath != null) {
                //获取图片
                return getBitmapFromSD(new File(downFilePath), type, desiredWidth, desiredHeight);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 描述：通过文件的本地地址从SD卡读取图片.
     *
     * @param file          the file
     * @param type          图片的处理类型（剪切或者缩放到指定大小，参考AbConstant类）
     *                      如果设置为原图，则后边参数无效，得到原图
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getBitmapFromSD(File file, int type, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = null;
        try {
            //SD卡是否存在
            if (!isCanUseSD()) {
                return null;
            }

            //文件是否存在
            if (!file.exists()) {
                return null;
            }

            //文件存在
            if (type == AbImageUtil.CUTIMG) {
                bitmap = AbImageUtil.getCutBitmap(file, desiredWidth, desiredHeight);
            } else if (type == AbImageUtil.SCALEIMG) {
                bitmap = AbImageUtil.getScaleBitmap(file, desiredWidth, desiredHeight);
            } else {
                bitmap = AbImageUtil.getBitmap(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 描述：通过文件的本地地址从SD卡读取图片.
     *
     * @param file the file
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromSD(File file) {
        Bitmap bitmap = null;
        try {
            //SD卡是否存在
            if (!isCanUseSD()) {
                return null;
            }
            //文件是否存在
            if (!file.exists()) {
                return null;
            }
            //文件存在
            bitmap = AbImageUtil.getBitmap(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 将Bitmap保存
     *
     * @param path   保存图片路径
     * @param bitmap Bitmap
     */

    public static void savaFile(String path, Bitmap bitmap) {
        File file = null;
        if (path != null) {
            file = new File(path);//将要保存图片的路径
        }
        try {
            if (file != null) {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 描述：将图片的byte[]写入本地文件.
     *
     * @param imgByte       图片的byte[]形势
     * @param fileName      文件名称，需要包含后缀，如.jpg
     * @param type          图片的处理类型（剪切或者缩放到指定大小，参考AbConstant类）
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getBitmapFromByte(byte[] imgByte, String fileName, int type, int desiredWidth, int desiredHeight) {
        FileOutputStream fos = null;
        DataInputStream dis = null;
        ByteArrayInputStream bis = null;
        Bitmap bitmap = null;
        File file = null;
        try {
            if (imgByte != null) {

                file = new File(imageDownloadDir + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                int readLength = 0;
                bis = new ByteArrayInputStream(imgByte);
                dis = new DataInputStream(bis);
                byte[] buffer = new byte[1024];

                while ((readLength = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, readLength);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                }
                fos.flush();
                bitmap = getBitmapFromSD(file, type, desiredWidth, desiredHeight);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (Exception e) {
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
        return bitmap;
    }

    /**
     * 描述：根据URL从互连网获取图片.
     *
     * @param url           要下载文件的网络地址
     * @param type          图片的处理类型（剪切或者缩放到指定大小，参考AbConstant类）
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getBitmapFromURL(String url, int type, int desiredWidth, int desiredHeight) {
        Bitmap bit = null;
        try {
            bit = AbImageUtil.getBitmap(url, type, desiredWidth, desiredHeight);
        } catch (Exception e) {
            AbLogUtil.d(AbFileUtil.class, "下载图片异常：" + e.getMessage());
        }
        return bit;
    }

    /**
     * 描述：获取src中的图片资源.
     *
     * @param src 图片的src路径，如（“image/arrow.png”）
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromSrc(String src) {
        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream(AbFileUtil.class.getResourceAsStream(src));
        } catch (Exception e) {
            AbLogUtil.d(AbFileUtil.class, "获取图片异常：" + e.getMessage());
        }
        return bit;
    }

    /**
     * 描述：获取Asset中的图片资源.
     *
     * @param context  the context
     * @param fileName the file name
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromAsset(Context context, String fileName) {
        Bitmap bit = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            bit = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            AbLogUtil.d(AbFileUtil.class, "获取图片异常：" + e.getMessage());
        }
        return bit;
    }

    /**
     * 描述：获取Asset中的图片资源.
     *
     * @param context  the context
     * @param fileName the file name
     * @return Drawable 图片
     */
    public static Drawable getDrawableFromAsset(Context context, String fileName) {
        Drawable drawable = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            drawable = Drawable.createFromStream(is, null);
        } catch (Exception e) {
            AbLogUtil.d(AbFileUtil.class, "获取图片异常：" + e.getMessage());
        }
        return drawable;
    }

    public static String downFile (String urlString){
        System.out.println("我点击了按钮");
        String path = null;
//            String urlString = "http://public.dhe.ibm.com/common/ssi/ecm/en/wsd14109usen/WSD14109USEN.PDF";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)
                    url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //实现连接
            connection.connect();

            System.out.println("connection.getResponseCode()=" + connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                //以下为下载操作
                byte[] arr = new byte[1];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(baos);
                int n = is.read(arr);
                while (n > 0) {
                    bos.write(arr);
                    n = is.read(arr);
                }
                bos.close();
                path = Environment.getExternalStorageDirectory()
                        + "/download/";
                String[] name = urlString.split("/");
//                    path = path + name[name.length - 1];
                path = path + "helloworld";
                System.out.println("name=" + name);
                System.out.println("path=" + path);
                File file = new File(path);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(baos.toByteArray());
                fos.close();
                //关闭网络连接
                connection.disconnect();
                System.out.println("下载完成");

            }
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return path;
    }
    /**
     * 下载网络文件到SD卡中.如果SD中存在同名文件将不再下载
     *
     * @param url     要下载文件的网络地址
     * @param dirPath the dir path
     * @return 下载好的本地文件地址
     */
    public static String downloadFile(String url, String dirPath) {
        InputStream in = null;
        FileOutputStream fileOutputStream = null;
        HttpURLConnection connection = null;
        String downFilePath = null;
        File file = null;
        try {
            if (!isCanUseSD()) {
                return null;
            }
            //先判断SD卡中有没有这个文件，不比较后缀部分比较
            String fileNameNoMIME = getCacheFileNameFromUrl(url);
            File parentFile = new File(imageDownloadDir);
            File[] files = parentFile.listFiles();
            for (int i = 0; i < files.length; ++i) {
                String fileName = files[i].getName();
                String name = fileName.substring(0, fileName.lastIndexOf("."));
                if (name.equals(fileNameNoMIME)) {
                    //文件已存在
                    return files[i].getPath();
                }
            }

            URL mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.connect();
            //获取文件名，下载文件
            String fileName = getCacheFileNameFromUrl(url, connection);

            file = new File(imageDownloadDir, fileName);
            downFilePath = file.getPath();
            if (!file.exists()) {
                file.createNewFile();
            } else {
                //文件已存在
                return file.getPath();
            }
            in = connection.getInputStream();
            fileOutputStream = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int temp = 0;
            while ((temp = in.read(b)) != -1) {
                fileOutputStream.write(b, 0, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AbLogUtil.e(AbFileUtil.class, "有文件下载出错了,已删除");
            //检查文件大小,如果文件为0B说明网络不好没有下载成功，要将建立的空文件删除
            if (file != null) {
                file.delete();
            }
            file = null;
            downFilePath = null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return downFilePath;
    }

    /**
     * 描述：获取网络文件的大小.
     *
     * @param Url 图片的网络路径
     * @return int 网络文件的大小
     */
    public static int getContentLengthFromUrl(String Url) {
        int mContentLength = 0;
        try {
            URL url = new URL(Url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) url.openConnection();
            mHttpURLConnection.setConnectTimeout(5 * 1000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            mHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            mHttpURLConnection.setRequestProperty("Referer", Url);
            mHttpURLConnection.setRequestProperty("Charset", "UTF-8");
            mHttpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.connect();
            if (mHttpURLConnection.getResponseCode() == 200) {
                // 根据响应获取文件大小
                mContentLength = mHttpURLConnection.getContentLength();
            }
        } catch (Exception e) {
            e.printStackTrace();
            AbLogUtil.d(AbFileUtil.class, "获取长度异常：" + e.getMessage());
        }
        return mContentLength;
    }

    /**
     * 获取文件名，通过网络获取.
     *
     * @param url 文件地址
     * @return 文件名
     */
    public static String getRealFileNameFromUrl(String url) {
        String name = null;
        try {
            if (TextUtils.isEmpty(url)) {
                return name;
            }

            URL mUrl = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(5 * 1000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            mHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            mHttpURLConnection.setRequestProperty("Referer", url);
            mHttpURLConnection.setRequestProperty("Charset", "UTF-8");
            mHttpURLConnection.setRequestProperty("User-Agent", "");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.connect();
            if (mHttpURLConnection.getResponseCode() == 200) {
                for (int i = 0; ; i++) {
                    String mine = mHttpURLConnection.getHeaderField(i);
                    if (mine == null) {
                        break;
                    }
                    if ("content-disposition".equals(mHttpURLConnection.getHeaderFieldKey(i).toLowerCase())) {
                        Matcher m = Pattern.compile(".*filename=(.*)").matcher(mine.toLowerCase());
                        if (m.find())
                            return m.group(1).replace("\"", "");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
        }
        return name;
    }


    /**
     * 获取真实文件名（xx.后缀），通过网络获取.
     * @param connection 连接
     * @return 文件名
     */
//	public static String getRealFileName(HttpURLConnection connection){
//		String name = null;
//		try {
//			if(connection == null){
//				return name;
//			}
//			if (connection.getResponseCode() == 200){
//				for (int i = 0;; i++) {
//						String mime = connection.getHeaderField(i);
//						if (mime == null){
//							break;
//						}
//						// "Content-Disposition","attachment; filename=1.txt"
//						// Content-Length
//						if ("content-disposition".equals(connection.getHeaderFieldKey(i).toLowerCase())) {
//							Matcher m = Pattern.compile(".*filename=(.*)").matcher(mime.toLowerCase());
//							if (m.find()){
//								return m.group(1).replace("\"", "");
//							}
//						}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
//		}
//		return name;
//    }

    /**
     * 获取真实文件名（xx.后缀），通过网络获取.
     *
     * @param response the response
     * @return 文件名
     */
    public static String getRealFileName(HttpResponse response) {
        String name = null;
        try {
            if (response == null) {
                return name;
            }
            //获取文件名
            Header[] headers = response.getHeaders("content-disposition");
            for (int i = 0; i < headers.length; i++) {
                Matcher m = Pattern.compile(".*filename=(.*)").matcher(headers[i].getValue());
                if (m.find()) {
                    name = m.group(1).replace("\"", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
        }
        return name;
    }

    /**
     * 获取文件名（不含后缀）.
     *
     * @param url 文件地址
     * @return 文件名
     */
    public static String getCacheFileNameFromUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String name = null;
        try {
            name = AbMd5.MD5(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * 获取文件名（.后缀），外链模式和通过网络获取.
     *
     * @param url      文件地址
     * @param response the response
     * @return 文件名
     */
    public static String getCacheFileNameFromUrl(String url, HttpResponse response) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String name = null;
        try {
            //获取后缀
            String suffix = getMIMEFromUrl(url, response);
            if (TextUtils.isEmpty(suffix)) {
                suffix = ".ab";
            }
            name = AbMd5.MD5(url) + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * 获取文件名（.后缀），外链模式和通过网络获取.
     *
     * @param url        文件地址
     * @param connection the connection
     * @return 文件名
     */
    public static String getCacheFileNameFromUrl(String url, HttpURLConnection connection) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String name = null;
        try {
            //获取后缀
            String suffix = getMIMEFromUrl(url, connection);
            if (TextUtils.isEmpty(suffix)) {
                suffix = ".ab";
            }
            name = AbMd5.MD5(url) + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * 获取文件后缀，本地.
     *
     * @param url        文件地址
     * @param connection the connection
     * @return 文件后缀
     */
    public static String getMIMEFromUrl(String url, HttpURLConnection connection) {

        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String suffix = null;
        try {
            //获取后缀
            if (url.lastIndexOf(".") != -1) {
                suffix = url.substring(url.lastIndexOf("."));
                if (suffix.indexOf("/") != -1 || suffix.indexOf("?") != -1 || suffix.indexOf("&") != -1) {
                    suffix = null;
                }
            }
//			if(TextUtils.isEmpty(suffix)){
//				 //获取文件名  这个效率不高
//				 String fileName = getRealFileName(connection);
//				 if(fileName!=null && fileName.lastIndexOf(".")!=-1){
//					 suffix = fileName.substring(fileName.lastIndexOf("."));
//				 }
//			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suffix;
    }

    /**
     * 获取文件后缀，本地和网络.
     *
     * @param url      文件地址
     * @param response the response
     * @return 文件后缀
     */
    public static String getMIMEFromUrl(String url, HttpResponse response) {

        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String mime = null;
        try {
            //获取后缀
            if (url.lastIndexOf(".") != -1) {
                mime = url.substring(url.lastIndexOf("."));
                if (mime.indexOf("/") != -1 || mime.indexOf("?") != -1 || mime.indexOf("&") != -1) {
                    mime = null;
                }
            }
            if (TextUtils.isEmpty(mime)) {
                //获取文件名  这个效率不高
                String fileName = getRealFileName(response);
                if (fileName != null && fileName.lastIndexOf(".") != -1) {
                    mime = fileName.substring(fileName.lastIndexOf("."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mime;
    }

    /**
     * 描述：从sd卡中的文件读取到byte[].
     *
     * @param path sd卡中文件路径
     * @return byte[]
     */
    public static byte[] getByteArrayFromSD(String path) {
        byte[] bytes = null;
        ByteArrayOutputStream out = null;
        try {
            File file = new File(path);
            //SD卡是否存在
            if (!isCanUseSD()) {
                return null;
            }
            //文件是否存在
            if (!file.exists()) {
                return null;
            }

            long fileSize = file.length();
            if (fileSize > Integer.MAX_VALUE) {
                return null;
            }

            FileInputStream in = new FileInputStream(path);
            out = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = in.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }
            in.close();
            bytes = out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
        return bytes;
    }

    /**
     * 描述：将byte数组写入文件.
     *
     * @param path    the path
     * @param content the content
     * @param create  the create
     */
    public static void writeByteArrayToSD(String path, byte[] content, boolean create) {

        FileOutputStream fos = null;
        try {
            File file = new File(path);
            //SD卡是否存在
            if (!isCanUseSD()) {
                return;
            }
            //文件是否存在
            if (!file.exists()) {
                if (create) {
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                        file.createNewFile();
                    }
                } else {
                    return;
                }
            }
            fos = new FileOutputStream(path);
            fos.write(content);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 描述：SD卡是否能用.
     *
     * @return true 可用,false不可用
     */
    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 描述：初始化存储目录.
     *
     * @param context the context
     */
    public static void initFileDir(Context context) {

        PackageInfo info = AbAppUtil.getPackageInfo(context);

        //默认下载文件根目录.
        String downloadRootPath = File.separator + AbAppConfig.DOWNLOAD_ROOT_DIR + File.separator + info.packageName + File.separator;

        //默认下载图片文件目录.
        String imageDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_IMAGE_DIR + File.separator;

        //默认下载文件目录.
        String fileDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_FILE_DIR + File.separator;

        //默认缓存目录.
        String cacheDownloadPath = downloadRootPath + AbAppConfig.CACHE_DIR + File.separator;

        //默认DB目录.
        String dbDownloadPath = downloadRootPath + AbAppConfig.DB_DIR + File.separator;

        try {
            if (!isCanUseSD()) {
                return;
            } else {

                File root = Environment.getExternalStorageDirectory();
                File downloadDir = new File(root.getAbsolutePath() + downloadRootPath);
                if (!downloadDir.exists()) {
                    downloadDir.mkdirs();
                }
                downloadRootDir = downloadDir.getPath();

                File cacheDownloadDirFile = new File(root.getAbsolutePath() + cacheDownloadPath);
                if (!cacheDownloadDirFile.exists()) {
                    cacheDownloadDirFile.mkdirs();
                }
                cacheDownloadDir = cacheDownloadDirFile.getPath();

                File imageDownloadDirFile = new File(root.getAbsolutePath() + imageDownloadPath);
                if (!imageDownloadDirFile.exists()) {
                    imageDownloadDirFile.mkdirs();
                }
                imageDownloadDir = imageDownloadDirFile.getPath();

                File fileDownloadDirFile = new File(root.getAbsolutePath() + fileDownloadPath);
                if (!fileDownloadDirFile.exists()) {
                    fileDownloadDirFile.mkdirs();
                }
                fileDownloadDir = fileDownloadDirFile.getPath();

                File dbDownloadDirFile = new File(root.getAbsolutePath() + dbDownloadPath);
                if (!dbDownloadDirFile.exists()) {
                    dbDownloadDirFile.mkdirs();
                }
                dbDownloadDir = dbDownloadDirFile.getPath();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 计算sdcard上的剩余空间.
     *
     * @return the int
     */
    public static int freeSpaceOnSD() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / 1024 * 1024;
        return (int) sdFreeMB;
    }

    /**
     * 根据文件的最后修改时间进行排序.
     */
    public static class FileLastModifSort implements Comparator<File> {

        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(File arg0, File arg1) {
            if (arg0.lastModified() > arg1.lastModified()) {
                return 1;
            } else if (arg0.lastModified() == arg1.lastModified()) {
                return 0;
            } else {
                return -1;
            }
        }
    }


    /**
     * 删除所有缓存文件.
     *
     * @return true, if successful
     */
    public static boolean clearDownloadFile() {
        try {
            File fileDirectory = new File(downloadRootDir);
            deleteFile(fileDirectory);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除文件.
     *
     * @return true, if successful
     */
    public static boolean deleteFile(File file) {

        try {
            if (!isCanUseSD()) {
                return false;
            }
            if (file == null) {
                return true;
            }
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            } else {
                file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 删除文件. 处理在不同rom下不能正常删除文件
     *
     * @return true, if successful
     */
    public static void deleteDirectory(File file) {
        if( file.exists() ) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
            file.delete();
        }
    }


    /**
     * 描述：读取Assets目录的文件内容.
     *
     * @param context  the context
     * @param name     the name
     * @param encoding the encoding
     * @return the string
     */
    public static String readAssetsByName(Context context, String name, String encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getAssets().open(name));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
            text = new String(buffer.toString().getBytes(), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text;
    }


    /**
     * 描述：读取Assets目录的中的json字符串.将其转换为String
     *
     * @param context  the context
     * @param name     the name
     * @param encoding the encoding
     * @return the string
     */
    public static String readAssetsJSONToStr(Context context, String name, String encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getAssets().open(name));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
            text = new String(buffer.toString().getBytes(), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        List<Object> list = new ArrayList<>();
//        try {
//            JSONObject obj = new JSONObject(text);
//            JSONArray array = obj.getJSONArray("body");
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject jsonObj = (JSONObject) array.get(i);
//                Object io = new Object(jsonObj.optString("sccode"), jsonObj.optString("scvaluename"));
//                list.add(io);
//            }
//        } catch (JSONException e) {
//            Log.i("TAG", "json转换异常");
//            e.printStackTrace();
//        }
        return text.toString();
    }

    /**
     * 描述：读取Assets目录的中的json字符串.将其转换为List
     *
     * @param context  the context
     * @param name     the name
     * @param encoding the encoding
     * @return the string
     */
    /*public static List<InjuryOccpuation> readAssetsJSON(Context context, String name, String encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getAssets().open(name));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
            text = new String(buffer.toString().getBytes(), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<InjuryOccpuation> list = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(text);
            JSONArray array = obj.getJSONArray("body");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObj = (JSONObject) array.get(i);
                InjuryOccpuation io = new InjuryOccpuation(jsonObj.optString("sccode"), jsonObj.optString("scvaluename"));
                list.add(io);
            }
        } catch (JSONException e) {
            Log.i("TAG", "json转换异常");
            e.printStackTrace();
        }
        return list;
    }*/

    /**
     * 描述：读取Assets目录的中的json字符串.将其转换为Map
     *
     * @param context  the context
     * @param name     the name
     * @param encoding the encoding
     * @return the string
     */

    public static Map<String,String> readAssetsJSONChangeMap(Context context, String name, String encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getAssets().open(name));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
            text = new String(buffer.toString().getBytes(), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String,String> map = new HashMap<>();
        try {
            JSONObject obj = new JSONObject(text);
            JSONArray array = obj.getJSONArray("body");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObj = (JSONObject) array.get(i);
                map.put("sccode",jsonObj.optString("sccode"));
                map.put("scvaluename",jsonObj.optString("scvaluename"));
            }
        } catch (JSONException e) {
            Log.i("TAG", "json转换异常");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 描述：读取Raw目录的文件内容.
     *
     * @param context  the context
     * @param id       the id
     * @param encoding the encoding
     * @return the string
     */
    public static String readRawByName(Context context, int id, String encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().openRawResource(id));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
            text = new String(buffer.toString().getBytes(), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text;
    }


    /**
     * Gets the download root dir.
     *
     * @param context the context
     * @return the download root dir
     */
    public static String getDownloadRootDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }
        return downloadRootDir;
    }


    /**
     * Gets the image download dir.
     *
     * @param context the context
     * @return the image download dir
     */
    public static String getImageDownloadDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }
        return imageDownloadDir;
    }


    /**
     * Gets the file download dir.
     *
     * @param context the context
     * @return the file download dir
     */
    public static String getFileDownloadDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }
        return fileDownloadDir;
    }


    /**
     * Gets the cache download dir.
     *
     * @param context the context
     * @return the cache download dir
     */
    public static String getCacheDownloadDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }
        return cacheDownloadDir;
    }


    /**
     * Gets the db download dir.
     *
     * @param context the context
     * @return the db download dir
     */
    public static String getDbDownloadDir(Context context) {
        if (downloadRootDir == null) {
            initFileDir(context);
        }
        return dbDownloadDir;
    }


    /**
     * Gets the free sd space needed to cache.
     *
     * @return the free sd space needed to cache
     */
    public static int getFreeSdSpaceNeededToCache() {
        return freeSdSpaceNeededToCache;
    }

    /**
     * h5资源解压后存储根路径
     * @param context
     * @return
     */
    public static String getWebroot(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/webroot";
    }

    /**
     * 拼接路径
     *
     * @author EX-LIJINHUA001
     * @date 2013-8-12
     * @param prePath
     *            http://www.demo.com/wwwroot 或 http://www.demo.com/wwwroot/
     * @param fileNamePath
     *            index.html 或 /index.html
     * @return http://www.demo.com/wwwroot/index.html
     */
    public static String splitJointPath(String prePath, String fileNamePath) {
        if (TextUtils.isEmpty(prePath) && TextUtils.isEmpty(fileNamePath)) {
            return "";
        }else if (TextUtils.isEmpty(prePath) && !TextUtils.isEmpty(fileNamePath)) {
            return fileNamePath;
        }else if (!TextUtils.isEmpty(prePath) && TextUtils.isEmpty(fileNamePath)) {
            return prePath;
        }
        String path = "";
        if (prePath.endsWith("/")) {
            if (fileNamePath.startsWith("/")) {
                path = prePath + fileNamePath.substring(1);
            } else {
                path = prePath + fileNamePath;
            }
        } else {
            if (fileNamePath.startsWith("/")) {
                path = prePath + fileNamePath;
            } else {
                path = prePath + "/" + fileNamePath;
            }
        }
        return path;
    }
    public static String getSDCardWebroot(Context context) {
        File f = context.getExternalFilesDir("webroot");
        if (f != null) {
            return f.getAbsolutePath();
        } else {
            return "";
        }

    }
    /**
     * 从assets中读取所有字符串
     *
     * @param filePath
     * @return
     */
    public static String readFromAssets(Context context, String filePath) {
        StringBuilder builder = new StringBuilder();
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(context.getAssets().open(filePath));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                builder.append(lineTxt);
            }
        } catch (Exception e) {
            AbLogUtil.e(AbFileUtil.class.getSimpleName(), "读取文件内容出错");
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
    /**
     * 从文件中读取所有字符串
     *
     * @param filePath
     * @return
     */
    public static String readFromFile(String filePath) {
        StringBuilder builder = new StringBuilder();
        InputStreamReader read = null;
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    builder.append(lineTxt);
                }
            } else {
                AbLogUtil.e(AbFileUtil.class.getSimpleName(), "找不到指定文件");
            }
        } catch (Exception e) {
            AbLogUtil.e(AbFileUtil.class.getSimpleName(), "读取文件内容出错");
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
    /**
     * 删除目录下所有文件及子目录
     */
    public static void doDeleteDirFile(String path) {
//        String path = initPath();
        File f = new File(path);//定义文件路径
        if (f.exists() && f.isDirectory()) {//判断是文件还是目录
            if (f.listFiles().length > 0) {//若有则把文件放进数组，并判断是否有下级目录
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;
                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                        doDeleteDirFile(delFile[j].getAbsolutePath());//递归调用del方法并取得子目录路径
                    }
                    delFile[j].delete();//删除文件
                }
            }
        }
    }
   /* *//**
     * 初始化保存路径
     * @return
     *//*
    public static String initPath(){
        String packagePath = "";
        if (Environment.getExternalStorageState() != null && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断SD卡是否存在
            packagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + SiappApplication.getInstance().getPackageName() + "/files/photo/";//得到图片绝对路径
            File packageFile = new File(packagePath);
            if (!packageFile.exists()) { //路径不存在就创建
                packageFile.mkdirs();
            }

        } else {
            ToastUtil.showToast("SD卡异常,请检查SD卡是否插入");
        }
        return packagePath;
    }*/
    /**
     * 保存Bitmap到sdcard
     * @param b
     * @return
     */
    /*public static String saveBitmap(Bitmap b){

        String path = initPath();
        long dataTake = System.currentTimeMillis();
        String jpegName = path + "/" + dataTake +".png";

//        Bitmap resizeBitmap = AbImageUtil.getScaleBitmap(b,width,height);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        b.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//        System.out.println(baos.toByteArray().length);
//        while (baos.toByteArray().length > 40 * 1024) {
//            baos.reset();
//            resizeBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//            quality -= 10;
//            System.out.println(baos.toByteArray().length);
//        }
        try {
            baos.writeTo(new FileOutputStream(jpegName));
            AbLogUtil.d("saveBitmap成功");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            AbLogUtil.d("saveBitmap:失败");
            e.printStackTrace();
        }finally {
            try {
                baos.flush();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jpegName;
    }*/
    /**
     * 压缩图片并保存Bitmap到sdcard
     * @param b
     * @return
     */
   /* public static String scaleAndSaveBitmap(Bitmap b,int width,int height,int size){

        String path = initPath();
        long dataTake = System.currentTimeMillis();
        String jpegName = path + "/" + dataTake +".png";
//        int width = 358;
//        int height = 441;

        Bitmap resizeBitmap = AbImageUtil.getScaleBitmap(b,width,height);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        resizeBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        System.out.println(baos.toByteArray().length);
        while (baos.toByteArray().length > size * 1024) {
            baos.reset();
            resizeBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            quality -= 10;
            System.out.println(baos.toByteArray().length);
        }
        try {
            baos.writeTo(new FileOutputStream(jpegName));
            AbLogUtil.d("saveBitmap成功");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            AbLogUtil.d("saveBitmap:失败");
            e.printStackTrace();
        }finally {
            try {
                baos.flush();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jpegName;
    }*/
}
