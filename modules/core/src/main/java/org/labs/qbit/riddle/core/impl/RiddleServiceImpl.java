package org.labs.qbit.riddle.core.impl;

import org.labs.qbit.riddle.core.RiddleParser;
import org.labs.qbit.riddle.core.RiddleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
public class RiddleServiceImpl implements RiddleService {

    private static final Logger logger = LoggerFactory.getLogger(RiddleServiceImpl.class);
    public static final String RIDDLE = "riddle";
    public static final String ANSWER = "answer";

    private Map<String, Object> riddleMap;
    private Random random;

    public RiddleServiceImpl() {
        super();
        random = new Random();
        try {
            RiddleParser riddleParser = new RiddleParser();
            riddleMap = riddleParser.getMap();
        } catch (IOException e) {
            logger.error("An exception occurred while parsing the json to map", e);
            riddleMap = new HashMap<String, Object>();
        }
    }

    @Override
    public String numberOfRiddles() {
        return String.valueOf(riddleMap.size());
    }

    @Override
    public String getRiddle() {
        Object[] values = riddleMap.values().toArray();
        Map<String, String> aRiddle = (Map<String, String>) values[random.nextInt(values.length)];
        return aRiddle.get(RIDDLE);
    }

    @Override
    public String getRiddle(String riddleIndex) {
        Map<String, String> aRiddle = (Map<String, String>) riddleMap.get(riddleIndex);
        if (aRiddle == null) {
            return "No riddles found for the given index";
        }
        return aRiddle.get(RIDDLE);
    }

    @Override
    public boolean evaluate(String riddleIndex, String answer) {
        Map<String, String> aRiddle = (Map<String, String>) riddleMap.get(riddleIndex);
        if (aRiddle == null) {
            throw new RuntimeException("No riddles found for the given index");
        }
        return aRiddle.get(ANSWER).equalsIgnoreCase(answer);
    }

    public Map<String, Object> getRiddleMap() {
        return riddleMap;
    }
}
