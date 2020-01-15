/*
 * Copyright 2018-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.flysium.io.bucket.springboot.hello;

import com.github.flysium.io.bucket.springboot.api.HelloService;
import javax.ws.rs.Path;

/**
 * Hello Service
 *
 * @author Sven Augustus
 */
@Path("/sayHello2")
public class HelloServiceImpl2 implements HelloService {

  @Override
  public String sayHello(String a) {
    return "Hello2 " + a + ", Welcome to CXF RS Spring Boot World!!!";
  }

}
