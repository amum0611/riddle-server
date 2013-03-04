package org.labs.qbit.riddle.server;

import org.labs.qbit.riddle.core.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
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
public class TCPClient {

    private static final Logger logger = LoggerFactory.getLogger(TCPClient.class);

    public static void main(String[] args) {
        Socket clientSocket;
        while (true) {
            System.out.print("User: ");
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String userInput = clientReader.readLine();
                clientSocket = new Socket("localhost", Port.TCP_PORT);
                if (userInput != null && userInput.trim().equalsIgnoreCase("bye")) {
                    System.out.println("bye");
                    break;
                }
                DataOutputStream serverWriter = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                logger.debug("User Input: {}", userInput);
                serverWriter.writeBytes(userInput + '\n');
                serverWriter.flush();
                logger.debug("User Input: {} is sent to the server", userInput);
                String serverResponse = serverReader.readLine();
                System.out.println(MessageFormat.format("Server: {0}", serverResponse));
                serverWriter.close();
                serverReader.close();
                clientSocket.close();
            } catch (IOException e) {
                logger.error("An error occurred", e);
                System.out.println(MessageFormat.format("An error occurred: {0}", e.getMessage()));
                System.exit(1);
            }
        }
        try {
            clientSocket.close();
            System.out.println("TCP Server connection is successfully terminated");
        } catch (IOException e) {
            logger.error("An error occurred", e);
            System.out.println(MessageFormat.format("An error occurred: {0}", e.getMessage()));
        }
        System.out.println("Exit");
    }
}
