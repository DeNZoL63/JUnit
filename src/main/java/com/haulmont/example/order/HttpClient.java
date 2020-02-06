package com.haulmont.example.order;

public interface HttpClient {
    String executeRequest(String url, Object object);
}
