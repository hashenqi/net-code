package com.czy.study.io.aio;

public class TimeServer {
    public static void main(String[] args) {
        AsyncTimeServerHandler handler = new AsyncTimeServerHandler(8080);
        new Thread(handler,"AIO-AsyncTimeServerHandler-001").start();
    }
}
