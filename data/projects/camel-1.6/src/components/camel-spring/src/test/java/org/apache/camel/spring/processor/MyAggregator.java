/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.spring.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A simple aggregator which just appends the message bodies together
 *
 * @version $Revision: 641676 $
 */
public class MyAggregator implements AggregationStrategy {
    private static final transient Log LOG = LogFactory.getLog(MyAggregator.class);

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        // lets append the old body to the new body
        String body = oldExchange.getIn().getBody(String.class);
        if (body != null) {
            Message newIn = newExchange.getIn();
            String newBody = newIn.getBody(String.class);
            if (newBody != null) {
                body += " " + newBody;
            }
            LOG.debug("**** invoked my strategy with result: " + body);

            newIn.setBody(body);
        }
        return newExchange;
    }
}