/*
 * MIT License
 *
 * Copyright (c) 2020 SvenAugustus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package xyz.flysium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zeno
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LoadBalanceRibbonForRestTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadBalanceRibbonForRestTemplateApplication.class, args);
    }

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${application.subscribe-provider.serviceId.serviceA}")
    private String providerApplicationName;

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            /**
             * 从所有服务中选择一个服务（轮询）
             */
            System.out.println("loadBalancer->" + loadBalancer.choose(providerApplicationName).getUri().toString());
            System.out.println("loadBalancer->" + loadBalancer.choose(providerApplicationName).getUri().toString());
            System.out.println("loadBalancer->" + loadBalancer.choose(providerApplicationName).getUri().toString());
            /**
             * 获取所有服务实例
             */
            System.out.println("discoveryClient->" + discoveryClient.getInstances(providerApplicationName));
            System.out.println("discoveryClient->" + discoveryClient.getInstances(providerApplicationName));
            System.out.println("discoveryClient->" + discoveryClient.getInstances(providerApplicationName));
            /**
             * 使用普通的 RestTemplate 方法访问服务
             */
            System.out.println("restTemplate->" + restTemplate
                    .getForObject("http://" + providerApplicationName + "/echo?message=s1", String.class));
            System.out.println("restTemplate->" + restTemplate
                    .getForObject("http://" + providerApplicationName + "/echo?message=s2", String.class));
            System.out.println("restTemplate->" + restTemplate
                    .getForObject("http://" + providerApplicationName + "/echo?message=s3",
                                  String.class));
        };
    }

}
