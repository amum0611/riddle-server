# Introduction

A Riddle server that returns riddles via TCP and UDP sockets

# License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).

# Prerequisites

* Maven 3

* Java 1.6

# Build the server

    mvn clean install

    Find the riddle-server-1.0.0.jar at modules/server/target

# Simulation

    1) Start the TCP Server (port 8888)
        java -cp riddle-server-1.0.0.jar org.labs.qbit.riddle.server.TCPServer

    2) Start the TCP Client
        java -cp riddle-server-1.0.0.jar org.labs.qbit.riddle.server.TCPClient

        * On "hello" returns number of riddles it has
        * On "riddle" returns a random riddle with its number
        * On "riddle n" return nth riddle
        * On "answer n x" matches x with the answer of nth riddle and return "correct" or "incorrect"
        * On "bye" sends "bye" back and disconnects
        * On anything else, print "unknown message"

    3) Start the UDP Server (port 9999)
        java -cp riddle-server-1.0.0.jar org.labs.qbit.riddle.server.UDPServer

    4) Start the UDP Client
        java -cp riddle-server-1.0.0.jar org.labs.qbit.riddle.server.UDPClient

        * On "hello" returns number of riddles it has
        * On "riddle" returns a random riddle with its number
        * On "riddle n" return nth riddle
        * On "answer n x" matches x with the answer of nth riddle and return "correct" or "incorrect"
        * On "bye" sends "bye" back and disconnects
        * On anything else, print "unknown message"

# Code Reference

James F. Kurose and Keith Ross. 2009. Computer Networking: A Top-Down Approach (5th ed.).
Addison-Wesley Longman Publishing Co., Inc., Boston, MA, USA.