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

package com.github.flysium.io.bucket.springboot.controller;

import com.github.flysium.io.bucket.springboot.annotation.UserAccess;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo Controller.
 *
 * @author Sven Augustus
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class DemoController {

  @RequestMapping("/")
  public Object func1() {
    return "hello, func1";
  }

  @RequestMapping("doError")
  public Object error() {
    throw new UnsupportedOperationException("do not support！");
  }

  @RequestMapping("func2")
  @UserAccess(desc = "welcome to func2！")
  public Object func2() {
    return "hello, func2";
  }

}
