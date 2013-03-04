package org.labs.qbit.riddle.server;

import org.labs.qbit.riddle.core.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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
public class TCPServer {

    private static final Logger logger = LoggerFactory.getLogger(TCPServer.class);

    public static void main(String[] args) {

        RiddleWrapper riddleWrapper = new RiddleWrapper();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Port.TCP_PORT);
            System.out.println(MessageFormat.format("TCP Server Started at port {0}", Port.TCP_PORT));
            while(true) {
                logger.debug("Waiting for an input");
                Socket connectionSocket = serverSocket.accept();
                logger.debug("Incoming client requests");
                BufferedReader clientReader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream clientWriter = new DataOutputStream(connectionSocket.getOutputStream());
                String clientInput = clientReader.readLine();
                System.out.println("Client: " + clientInput);
                if (clientInput == null) {
                    continue;
                }
                String clientOutput = riddleWrapper.parse(clientInput);
                logger.debug("Client Output: {}", clientOutput);
                clientWriter.writeBytes(clientOutput + '\n');
                clientWriter.flush();
                connectionSocket.close();
                clientReader.close();
                clientWriter.close();
                logger.debug("Client Output: {} sent", clientOutput);
            }

        } catch (IOException e) {
            logger.error("An error occurred", e);
            System.out.println(e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("TCP Server is successfully terminated");
                } catch (IOException e) {
                    logger.debug("An error occurred", e);
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Exit");
    }
}