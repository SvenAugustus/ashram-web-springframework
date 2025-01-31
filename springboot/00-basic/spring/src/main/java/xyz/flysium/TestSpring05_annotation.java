/*
 * Copyright 2020 SvenAugustus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.flysium;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.flysium.annotation.controller.MainController;

/**
 * @author Sven Augustus
 * @version 1.0
 */
public class TestSpring05_annotation {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "t5_annotation.xml");

        MainController controller = context.getBean(MainController.class);
        System.out.println(controller.login("zhangsan", "123"));
        System.out.println(controller.login("zhangsan", "456"));
    }

}
