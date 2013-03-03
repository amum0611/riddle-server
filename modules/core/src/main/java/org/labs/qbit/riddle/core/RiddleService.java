package org.labs.qbit.riddle.core;

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
public interface RiddleService {

    /**
     * Get the total number of riddles
     * @return String
     */
    String numberOfRiddles();

    /**
     * get a random riddle
     * @return String
     */
    String getRiddle();

    /**
     * Get a random riddle for the given index.
     * @param riddleIndex String
     * @return String
     */
    String getRiddle(String riddleIndex);

    /**
     * Evaluate the riddle by index against the provided answer. Throws runtime exception if riddleIndex is invalid.
     * @param riddleIndex String
     * @param answer String
     * @return boolean
     */
    boolean evaluate(String riddleIndex, String answer);

}
