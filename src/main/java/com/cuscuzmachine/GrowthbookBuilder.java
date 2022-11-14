package com.cuscuzmachine;

import com.squareup.okhttp.*;

import java.io.IOException;

public class GrowthbookBuilder {

    private String HOST ;
    private String ENVIROMENT_KEY;
    private String PROJECT_KEY;

    public GrowthbookBuilder(String hostGB, String enviromentKey){
        this.HOST = hostGB;
        this.ENVIROMENT_KEY = enviromentKey;
    }

    public GrowthbookBuilder(String hostGB, String enviromentKey, String projectKey){
        this.HOST = hostGB;
        this.ENVIROMENT_KEY = enviromentKey;
        this.PROJECT_KEY = projectKey;
    }

    private String buildURL(){
        String url = this.HOST + "/api/features/" + this.ENVIROMENT_KEY;

        if(this.PROJECT_KEY != null && !this.PROJECT_KEY.equals("") && !this.PROJECT_KEY.equals(""))
            url = url + "?project=" + this.PROJECT_KEY;

        return url;
    }

    public Growthbook initialize(){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(buildURL())
                .build();

        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            return OMUtils.readValue(response.body().string(), Growthbook.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Growthbook();

    }

}
