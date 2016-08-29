package com.tesis.galeria.galeria.db;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.Gson;
import com.google.gson.internal.UnsafeAllocator;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by danie on 17/5/2016.
 */
public class ConexionDB {

    private static OkHttpClient httpClient;

    public static void getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trusAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trusAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.protocols(Arrays.asList(Protocol.HTTP_1_1));
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            httpClient = builder
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .build();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public static void getUnsafeOkHttpClient(AppCompatActivity context) {
        try {
            final TrustManager[] trusAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trusAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.protocols(Arrays.asList(Protocol.HTTP_1_1));
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            httpClient = builder
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .build();

            try {
                Picasso.Builder picassoBuilder = new Picasso.Builder(context)
                        .downloader(new OkHttp3Downloader(httpClient));
                Picasso picasso = picassoBuilder.build();
                Picasso.setSingletonInstance(picasso);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

            ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                    .newBuilder(context, httpClient)
                    .build();
            Fresco.initialize(context, config);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public static String get(String url, String token) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Bearer " + token)
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public static String post(String url, String postBody) throws IOException {
        final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody body = RequestBody.create(mediaType, postBody);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public static Response postResponse(String url, String postBody) throws IOException {
        final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody body = RequestBody.create(mediaType, postBody);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        return response;
    }

    public static String put(String url, String postBody) throws IOException {
        final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody body = RequestBody.create(mediaType, postBody);

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public static String delete(String url, String postBody) throws IOException {
        final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody body = RequestBody.create(mediaType, postBody);

        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public static Boolean uploadFileProcesarAvaluo(String url, File file, String mediaType, String idAvaluo, String precio) {
        try {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("documentoAvaluo", file.getName(),
                            RequestBody.create(MediaType.parse(mediaType), file))
                    .addFormDataPart("idAvaluo", idAvaluo)
                    .addFormDataPart("precio", precio)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            httpClient.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Log.d("RESPUESTA", response.body().string());
                        Log.d("RESPUESTA 2", String.valueOf(response.code()));

                    } else {
                        Log.d("RESPUESTA", response.body().string());
                        Log.d("RESPUESTA 2", String.valueOf(response.code()));
                    }

                }
            });

            return true;
        } catch (Exception ex) {
            // Handle the error
        }
        return false;
    }

}