package com.example.webservice;

public interface WebServiceHandler {
    void onDataArrived(String message, String status,Object data);

}
