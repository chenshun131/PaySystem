package com.chenshun.test;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;

/**
 * User: chenshun131 <p />
 * Time: 18/2/25 22:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class HttpClientTest {

    public static void main(String[] args) {
        // jdk 9 中 使用 HttpClient替换原有的HttpURLConnection
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder(URI.create("http://www.atguigu.com")).GET().build();
            HttpResponse<String> response = null;
            response = client.send(req, HttpResponse.BodyHandler.asString());
            System.out.println(response.statusCode());
            System.out.println(response.version().name());
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
