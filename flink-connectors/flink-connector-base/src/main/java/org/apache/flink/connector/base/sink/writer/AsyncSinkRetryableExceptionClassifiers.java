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

package org.apache.flink.connector.base.sink.writer;

import org.apache.flink.annotation.Internal;
import org.apache.flink.connector.base.sink.util.RetryableExceptionClassifier;
import org.apache.flink.util.FlinkException;

/** Common retry exception classifiers needed for common errors. */
@Internal
public class AsyncSinkRetryableExceptionClassifiers {
    public static RetryableExceptionClassifier getInterruptedExceptionClassifier() {
        return RetryableExceptionClassifier.withRootCauseOfType(
                InterruptedException.class, err -> new FlinkException("Thread was interrupted"));
    }

    public static RetryableExceptionClassifier getGeneralExceptionClassifier() {
        return RetryableExceptionClassifier.withRootCauseOfType(
                Error.class,
                err -> new RuntimeException("Encountered non-recoverable exception", err));
    }
}