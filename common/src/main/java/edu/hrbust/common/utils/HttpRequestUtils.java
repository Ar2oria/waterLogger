package edu.hrbust.common.utils;

import com.squareup.okhttp.*;
import edu.hrbust.common.enums.DownloadStatusEnum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class HttpRequestUtils {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static OkHttpClient get(){
        return okHttpClient;
    }

    public static String doGet(String url) {
        Request request = new Request.Builder().url(url).build();
        String responseStr = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            responseStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseStr;
    }

    public static String getFileNameFromUrl(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }

    public static String getFileExtensionNameFromUrl(String url){
        int beginIndex = url.lastIndexOf(".");
        int endIndex = url.lastIndexOf("?");
        if (beginIndex <= 0){
            return null;
        }
        if (endIndex > 0){
            return url.substring(beginIndex, endIndex);
        }else {
            return url.substring(beginIndex, beginIndex + 4);
        }
    }

    public static DownloadStatusEnum fileDownloadSyn (String url, String savePath, String rename, Boolean extension){
        DownloadStatusEnum statusEnum = DownloadStatusEnum.OK;
        String sourceFileName = getFileNameFromUrl(url);
        String extensionName = getFileExtensionNameFromUrl(url);
        Request request = new Request.Builder().url(url).build();

        InputStream is = null;
        byte[] buffer = new byte[2*1024];
        FileOutputStream fos = null;

        File saveDir = new File(savePath);
        if (!saveDir.exists()){
            saveDir.mkdirs();
        }

        try {
            Response response = okHttpClient.newCall(request).execute();
            is = response.body().byteStream();

            String fileName = rename!=null?(extension?rename+extensionName:rename):sourceFileName;
            File f = new File(saveDir, fileName);
            fos = new FileOutputStream(f);

            int len = 0;
            while ((len = is.read(buffer)) > 0){
                fos.write(buffer, 0 ,len);
            }
            fos.flush();

        } catch (IOException e) {
            statusEnum = DownloadStatusEnum.ERROR;
        }
        finally {
            try {
                fos.close();
                is.close();
            }catch (Exception e){
                statusEnum = DownloadStatusEnum.FILE_HANDLE_ERROR;
            }
        }
        return statusEnum;
    }

    public static void fileDownloadAsyn (String url, String savePath, String rename, boolean extension,final DownloadListener downloadListener){
        String sourceFileName = getFileNameFromUrl(url);
        String extensionName = getFileExtensionNameFromUrl(url);
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                downloadListener.failed(e.getMessage());
            }

            @Override
            public void onResponse(Response response) {
                InputStream is = null;
                byte[] buffer = new byte[2*1024];
                FileOutputStream fos = null;
                long total = 0;

                File saveDir = new File(savePath);
                if (!saveDir.exists()){
                    saveDir.mkdirs();
                }

                try{
                    is = response.body().byteStream();
                    total = response.body().contentLength();

                    String fileName = rename!=null?(extension?rename+extensionName:rename):sourceFileName;
                    File f = new File(saveDir, fileName);
                    fos = new FileOutputStream(f);

                    long sum = 0;
                    int len = 0;
                    while ((len = is.read(buffer)) > 0){
                        fos.write(buffer, 0, len);
                        sum+=len;
                        double process = sum * 1.0f / total * 100;

                        downloadListener.downloading(process);
                    }

                    fos.flush();
                    downloadListener.success();
                }catch (Exception e){
                    downloadListener.failed(e.getMessage());
                }finally {
                    try {
                        fos.close();
                        is.close();
                    }catch (Exception e){}
                }
            }
        });

    }

    public static String doPostWithForm(String url, String postBody){
        RequestBody body = RequestBody.create(FORM, postBody);
        return doPostWithRequestBody(url, body);
    }

    public static String doPostWithJson(String url, String postBody){
        RequestBody body = RequestBody.create(JSON, postBody);
        return doPostWithRequestBody(url, body);
    }

    private static String doPostWithRequestBody(String url, RequestBody body){
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        String responseStr = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response!=null && response.isSuccessful()) {
                responseStr = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseStr;
    }

}
