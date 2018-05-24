package com.lepus.cang2.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2018/5/24.
 */

public class VolleyHttp {

    public static RequestQueue requestQueue;

    public static synchronized RequestQueue getRequestQueue(Context context){
        if(context == null)
            throw new RuntimeException("context 不能为空");
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
        return requestQueue;
    }

    public static VolleyHttpConfigure configure(Context context){
        if(context == null)
            throw new RuntimeException("context 不能为空");
        VolleyHttpConfigure configure = new VolleyHttpConfigure();
        configure.context = context;
        return configure;
    }

    public static class VolleyHttpConfigure {

        Context context;
        String url;
        RequestParams params = new RequestParams();
        Map<String, String> headers = new HashMap<>();
        Response.Listener listener;
        Response.ErrorListener errorListener;

        private VolleyHttpConfigure(){

        }
        public VolleyHttpConfigure url(String url){
            this.url = url;
            return this;
        }
        public VolleyHttpConfigure params(RequestParams params){
            if(params != null)
                this.params = params;
            return this;
        }
        public VolleyHttpConfigure addCookie(String cookie){
            return addHeader("Cookie", cookie);
        }
        public VolleyHttpConfigure addHeader(String key, String val){
            headers.put(key, val);
            return this;
        }
        public VolleyHttpConfigure listener(Response.Listener listener){
            this.listener = listener;
            return this;
        }
        public VolleyHttpConfigure errorListener(Response.ErrorListener errorListener){
            this.errorListener = errorListener;
            return this;
        }
        public void execute(){
            PostStringHttpRequest request = new PostStringHttpRequest(url, params, headers, listener, errorListener);
            getRequestQueue(context).add(request);
        }
    }

    public static class RequestParams {

        Map<String, Object> params = new HashMap<>();

        private RequestParams(){

        }
        public static RequestParams newInstance(){
            return new RequestParams();
        }
        public static RequestParams init(Map<?, ?> params){
            return RequestParams.newInstance().add(params);
        }
        public RequestParams add(String key, Object value){
            params.put(key, value);
            return this;
        }
        public RequestParams add(Map<?, ?> params){
            if(params != null) {
                for(Map.Entry<?, ?> entry : params.entrySet()){
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    this.params.put(key.toString(), val);
                }
            }
            return this;
        }
        public Map<String, String> getParams() {
            Map<String, String> map = new HashMap<>();
            if(params != null && !params.isEmpty()){
                for(Map.Entry<String, Object> entry : params.entrySet()){
                    map.put(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            return map;
        }
    }

    public interface StringHttpRequestListener extends Response.Listener<String> {

    }

    public interface StringHttpRequestErrorListener extends Response.ErrorListener {

    }

    public static class PostStringHttpRequest extends Request<String> {

        RequestParams params;
        Map<String, String> headers;
        Response.Listener listener;

        public PostStringHttpRequest(String url, RequestParams params, Map<String, String> headers, Response.Listener listener, Response.ErrorListener errorListener){
            super(Method.POST, url, errorListener);
            this.params = params;
            this.listener = listener;
            this.headers = headers;
        }

        @Override
        public Map<String, String> getParams() {
            return params.getParams();
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return headers;
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            if(response.statusCode == 200){
                try {
                    String resp = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(resp, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                }
            }else{
                return Response.error(new ParseError(response));
            }
        }

        @Override
        protected void deliverResponse(String response) {
            listener.onResponse(response);
        }
    }

}
