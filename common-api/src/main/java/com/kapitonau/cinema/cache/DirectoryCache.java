package com.kapitonau.cinema.cache;

public interface DirectoryCache<T> {

    T directoryByTypeAndCode(String directoryType, String directoryCode);

    T directoryById(Long directoryId);

}
