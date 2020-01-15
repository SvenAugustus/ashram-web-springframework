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

package com.github.flysium.io.bucket.springboot.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Kryo} Util.
 *
 * @author Sven Augustus
 */
public final class KryoUtil {

  private static final Logger logger = LoggerFactory.getLogger(KryoUtil.class);
  public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

  private KryoUtil() {
  }

  /**
   * 序列化
   */
  public static <T> byte[] toBytes(T object) {
    if (object == null) {
      return null;
    }

    Kryo kryo = new Kryo();
    ByteArrayOutputStream out = null;
    Output output = null;
    try {
      out = new ByteArrayOutputStream();
      output = new Output(out);

      kryo.writeClassAndObject(output, object);
      // 必须在kryo.writeClassAndObject之后主动刷新输入流，否则Kryo框架会由于底层机制，导致返回空字节数组
      // 同样问题不存在kryo.readClassAndObject中
      output.flush();
      return out.toByteArray();
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          // ignore
          logger.warn("Closing Kryo Output failed", e);
        }
      }

      if (output != null) {
        output.close();
      }
    }
  }

  /**
   * 反序列化
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromBytes(byte[] bytes) {
    if (bytes == null) {
      return null;
    }

    Kryo kryo = new Kryo();
    InputStream in = null;
    Input input = null;

    try {
      in = new ByteArrayInputStream(bytes);
      input = new Input(in);

      return (T) kryo.readClassAndObject(input);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          //ignore
          logger.warn("Closing Kryo Output failed", e);
        }
      }

      if (input != null) {
        input.close();
      }
    }
  }

  /**
   * 序列化对象为字符串
   */
  public static <T> String toString(T object) {
    byte[] buf = toBytes(object);
    if (buf == null) {
      return null;
    }

    return (new String(buf, DEFAULT_CHARSET));
  }

  /**
   * fromString:反序列化. <br/>
   *
   * @author denghankun
   * @since JDK 1.7
   */
  public static <T> T fromString(String str) {
    if (str == null) {
      return null;
    }

    return fromBytes(str.getBytes(DEFAULT_CHARSET));
  }

}