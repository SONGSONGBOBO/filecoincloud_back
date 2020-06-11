package com.songbo.filecoincloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/10 下午7:14
 **/
@Configuration
@EnableSwagger2
public class Swagger {

    /**
     * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.songbo.filecoincloud"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * @Description: 构建 api文档的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("后端api接口文档")
                // 设置联系人
                .contact(new Contact("songbo", "http://www.imsongbo.com", "1127568060@qq.com"))
                // 描述
                .description("file coin 后台管理所有的请求头都需要添加ACCESS_TOKEN和userId"
                       )
                // 定义版本号
                .version("1.0").build();
    }
}
