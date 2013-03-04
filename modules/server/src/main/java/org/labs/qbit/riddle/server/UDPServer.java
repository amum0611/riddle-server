package org.labs.qbit.riddle.server;

import org.labs.qbit.riddle.core.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
public class UDPServer {

    private static final Logger logger = LoggerFactory.getLogger(UDPServer.class);

    public static void main(String[] args) {
        RiddleWrapper riddleWrapper = new RiddleWrapper();
        try {
            DatagramSocket serverSocket = new DatagramSocket(Port.UDP_PORT);
            System.out.println(MessageFormat.format("UDP Server Started at port {0}", Port.UDP_PORT));
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(new byte[1024], new byte[1024].length);
                logger.debug("Waiting for an input");
                serverSocket.receive(receivePacket);
                logger.debug("Incoming client UDP requests");
                String clientInput = new String(receivePacket.getData()).trim();
                System.out.println(MessageFormat.format("Client: {0}", clientInput));
                InetAddress inetAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String clientOutput = riddleWrapper.parse(clientInput);
                logger.debug("Client Output: {}", clientOutput);
                DatagramPacket sendPacket = new DatagramPacket(clientOutput.getBytes(), clientOutput.getBytes().length, inetAddress, port);
                serverSocket.send(sendPacket);
                logger.debug("Client Output: {} sent", clientOutput);
            }
        } catch (SocketException e) {
            logger.error("An error occurred", e);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            logger.error("An error occurred", e);
            System.out.println(e.getMessage());
        }
        System.out.println("Exit");
    }

}