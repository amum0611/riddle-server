package org.labs.qbit.riddle.core.impl;

import org.labs.qbit.riddle.core.RiddleService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
public class RiddleServiceImplTest {

    private RiddleServiceImpl riddleService;

    @BeforeClass
    public void setUp() {
        riddleService = new RiddleServiceImpl();
    }

    @Test
    public void testNumberOfRiddles() {
        Assert.assertEquals(riddleService.numberOfRiddles(), "9");
    }

    @Test
    public void testGetRiddleOne() {
        for (int i = 0; i < 20; i++) {
            Assert.assertNotNull(riddleService.getRiddle());
        }
    }

    @Test
    public void testGetRiddleTwo() {
        Assert.assertNotNull(riddleService.getRiddle("1"));
        Assert.assertEquals(riddleService.getRiddle("13"), "No riddles found for the given index");
    }

    @Test
    public void testEvaluateOne() {
        String question = "A box without hinges, key or lid, Yet golden treasure inside is hid.";
        String answer = "Egg";
        String index = "8";
        Assert.assertTrue(riddleService.evaluate(index, answer));
        Assert.assertEquals(riddleService.getRiddle(index), question);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testEvaluateTwo() {
        String answer = "Egg";
        String invalidIndex = "88";
        Assert.assertTrue(riddleService.evaluate(invalidIndex, answer));
    }
}
