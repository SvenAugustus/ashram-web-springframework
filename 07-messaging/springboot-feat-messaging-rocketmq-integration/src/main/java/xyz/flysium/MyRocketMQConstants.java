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

/**
 * RocketMQ Constants.
 *
 * @author Sven Augustus
 */
public class MyRocketMQConstants {

  public static final String TOPIC = "TopicTest";
  public static final String TOPIC_2 = "TopicTest2";

  public static final String CONSUMER_GROUP1 = "consumerGroup1";
  public static final String CONSUMER_GROUP2 = "consumerGroup2";
  public static final String TRANSACTION_PRODUCER_GROUP = "transactionProducerGroup1";

  /**
   * order id of OrderInfo
   */
  public static final String HEADERS_C_ORDERID = "C_ORDERID";

}
