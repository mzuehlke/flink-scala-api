/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flink.api.function

import org.apache.flink.annotation.Public
import org.apache.flink.api.common.functions.Function
import org.apache.flink.streaming.api.windowing.windows.Window
import org.apache.flink.util.Collector

import java.io.Serializable

/** Base interface for functions that are evaluated over non-grouped windows,
  * i.e., windows over all stream partitions.
  *
  * @tparam IN
  *   The type of the input value.
  * @tparam OUT
  *   The type of the output value.
  */
@Public
trait AllWindowFunction[IN, OUT, W <: Window] extends Function with Serializable {

  /** Evaluates the window and outputs none or several elements.
    *
    * @param window
    *   The window that is being evaluated.
    * @param input
    *   The elements in the window being evaluated.
    * @param out
    *   A collector for emitting elements.
    * @throws Exception
    *   The function may throw exceptions to fail the program and trigger recovery.
    */
  def apply(window: W, input: Iterable[IN], out: Collector[OUT]): Unit
}
