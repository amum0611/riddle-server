package org.labs.qbit.riddle.server;

import org.labs.qbit.riddle.core.RiddleService;
import org.labs.qbit.riddle.core.impl.RiddleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * Copyright (c) 2013, QBit-Labs Inc. (http://qbit-labs.org) All Rights Reserved.
 *
 * QBit-Labs Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
public class RiddleWrapper {

    private static final Logger logger = LoggerFactory.getLogger(RiddleWrapper.class);

    private RiddleService riddleService;

    public RiddleWrapper() {
        riddleService = new RiddleServiceImpl();
    }

    public String parse(String clientInput) {
        logger.debug("Client Input: {}", clientInput);
        if (clientInput == null) {
            return "unknown message - 0";
        }
        String[] split = clientInput.split(" ");
        if (split == null) {
            return "unknown message - 1";
        }
        if (split[0].trim().equalsIgnoreCase("hello")) {
            return MessageFormat.format("Number of Riddles: {0}", riddleService.numberOfRiddles());
        }
        if (split.length == 2 && split[0].trim().equalsIgnoreCase("riddle") && split[1] != null) {
            return riddleService.getRiddle(split[1].trim());
        }
        if (split[0].trim().equalsIgnoreCase("riddle")) {
            return riddleService.getRiddle();
        }
        if (split.length == 3 && split[0].trim().equalsIgnoreCase("answer") && split[1] != null && split[2] != null) {
            try {
                return String.valueOf(riddleService.evaluate(split[1].trim(), split[2].trim()));
            } catch (RuntimeException ex) {
                logger.error(ex.getMessage(), ex);
                return ex.getMessage();
            }
        }
        return "unknown message - 2";
    }
}
