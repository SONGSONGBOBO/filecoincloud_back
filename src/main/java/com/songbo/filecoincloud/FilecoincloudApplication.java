package com.songbo.filecoincloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.songbo.filecoincloud.mapper")
public class FilecoincloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilecoincloudApplication.class, args);
    }

}
