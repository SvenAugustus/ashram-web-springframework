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

package xyz.flysium.service;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.flysium.api.User;

import java.util.Map;

import static org.springframework.http.MediaType.*;

/**
 * @author zeno (Sven Augustus)
 * @version 1.0
 */
@FeignClient("${application.subscribe-provider.serviceId.serviceA}")
@DubboTransported(protocol = "dubbo")
public interface DubboFeignRestService {

    @GetMapping("/param")
    String param(@RequestParam("param") String param);

    @PostMapping("/params")
    String params(@RequestParam("b") String paramB, @RequestParam("a") int paramA);

    @PostMapping(value = "/request/body/map", produces = APPLICATION_JSON_UTF8_VALUE)
    User requestBody(@RequestParam("param") String param,
                     @RequestBody Map<String, Object> data);

    @GetMapping("/headers")
    String headers(@RequestHeader("h2") String header2,
                   @RequestParam("v") Integer value, @RequestHeader("h") String header);

    @GetMapping("/path-variables/{p1}/{p2}")
    String pathVariables(@RequestParam("v") String param,
                         @PathVariable("p2") String path2, @PathVariable("p1") String path1);

}