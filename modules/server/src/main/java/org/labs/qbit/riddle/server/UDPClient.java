package org.labs.qbit.riddle.server;

import org.labs.qbit.riddle.core.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
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
public class UDPClient {

    private static final Logger logger = LoggerFactory.getLogger(UDPClient.class);

    public static void main(String[] args) {
        while (true) {
            System.out.print("UDP Client: ");
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                DatagramSocket clientSocket = new DatagramSocket();
                InetAddress inetAddress = InetAddress.getByName("localhost");
                String userInput = clientReader.readLine();
                logger.debug("User Input: {}", userInput);
                if (userInput == null) {
                    continue;
                } else if (userInput.trim().equalsIgnoreCase("bye")) {
                    System.out.println("bye");
                    break;
                }
                DatagramPacket sendPacket = new DatagramPacket(userInput.getBytes(), userInput.getBytes().length,
                        inetAddress, Port.UDP_PORT);
                clientSocket.send(sendPacket);
                logger.debug("User Input: {} sent", userInput);
                DatagramPacket receivePacket = new DatagramPacket(new byte[1024], new byte[1024].length);
                clientSocket.receive(receivePacket);
                String serverResponse = new String(receivePacket.getData()).trim();
                logger.debug("Server Output received: {}", serverResponse);
                System.out.println(MessageFormat.format("Server: {0}", serverResponse));
                clientSocket.close();
            } catch (UnknownHostException e) {
                logger.error("An error occurred", e);
                System.out.println(MessageFormat.format("An error occurred: {0}", e.getMessage()));
            } catch (SocketException e) {
                logger.error("An error occurred", e);
                System.out.println(MessageFormat.format("An error occurred: {0}", e.getMessage()));
            } catch (IOException e) {
                logger.error("An error occurred", e);
                System.out.println(MessageFormat.format("An error occurred: {0}", e.getMessage()));
            }
        }
        System.out.println("Exit");
    }

}
