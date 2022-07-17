package com.kapitonau.cinema.cache.feign.client;

import com.kapitonau.cinema.directory.api.DirectoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "directory-service")
public interface DirectoryApiClient extends DirectoryApi {
}
