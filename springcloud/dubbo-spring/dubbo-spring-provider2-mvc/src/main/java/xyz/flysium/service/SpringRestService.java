/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.flysium.service;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.api.RestService;
import xyz.flysium.api.User;
import xyz.flysium.util.LoggerUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring MVC {@link RestService}.
 *
 * @author zeno (Sven Augustus)
 */
@Service(version = "1.0.0")
@RestController
public class SpringRestService implements RestService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @GetMapping("/param")
    public String param(@RequestParam String param) {
        LoggerUtils.log("/param", param);
        return param;
    }

    @Override
    @PostMapping("/params")
    public String params(@RequestParam int a, @RequestParam String b) {
        LoggerUtils.log("/params", a + b);
        return a + b;
    }

    @Override
    @GetMapping("/headers")
    public String headers(@RequestHeader("h") String header,
                          @RequestHeader("h2") String header2, @RequestParam("v") Integer param) {
        String result = header + " , " + header2 + " , " + param;
        LoggerUtils.log("/headers", result);
        return result;
    }

    @Override
    @GetMapping("/path-variables/{p1}/{p2}")
    public String pathVariables(@PathVariable("p1") String path1,
                                @PathVariable("p2") String path2, @RequestParam("v") String param) {
        String result = path1 + " , " + path2 + " , " + param;
        LoggerUtils.log("/path-variables", result);
        return result;
    }

    @Override
    @PostMapping("/form")
    public String form(@RequestParam("f") String form) {
        return String.valueOf(form);
    }

    @Override
    @PostMapping(value = "/request/body/map",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User requestBodyMap(@RequestBody Map<String, Object> data,
                               @RequestParam("param") String param) {
        User user = new User();
        user.setId(((Integer) data.get("id")).longValue());
        user.setName((String) data.get("name"));
        user.setAge((Integer) data.get("age"));
        LoggerUtils.log("/request/body/map", param);
        return user;
    }

    @PostMapping(value = "/request/body/user",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Map<String, Object> requestBodyUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("age", user.getAge());
        return map;
    }

}
