package com.example.movieapi.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mahdi Sharifi
 * @since 10/7/22
 */
public enum Singleton {
    INSTANCE;

    public static Map<Integer,String> ErrorCodeToClientMessage= new ConcurrentHashMap<>();
}
