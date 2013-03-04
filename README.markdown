# Introduction

A Riddle server that returns riddles via TCP and UDP sockets

# License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
    Developer: Azeem Mumtaz - 138218R

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

# Riddles

    Riddles are stored in riddles.json

     {
         "1" : {
             "riddle" : "This thing all things devours: Birds, beasts, trees, flowers; Gnaws iron, bites steel; Grinds hard stones to meal; Slays king, ruins town, And beats high mountain down",
             "answer" : "Time"
         },
         "2" : {
             "riddle" : "What has roots as nobody sees, Is taller than trees Up, up it goes, And yet never grows?",
             "answer" : "Mountain"
         },
         "3" : {
             "riddle" : "No-legs lay on one-leg, two legs sat near on three legs, four legs got some.",
             "answer" : "Fish on a little one-legged table, man at table sitting on a three-legged stool, the cat gets the bones"
         },
         "4" : {
             "riddle" : "An eye in a blue face Saw an eye in a green face. 'That eye is like to this eye' Said the first eye, 'But in low place Not in high place.'",
             "answer" : "Sun shining on daisies which are growing in a field"
         },
         "5" : {
             "riddle" : "Alive without breath, As cold as death; Never thirsty, ever drinking, All in mail never clinking",
             "answer" : "Fish"
         },
         "6" : {
             "riddle" : "It cannot be seen, cannot be felt, Cannot be heard, cannot be smelt. It lies behind stars and under hills, And empty holes it fills. It comes first and follows after, Ends life, kills laughter",
             "answer" : "Dark"
         },
         "7" : {
             "riddle" : "Thirty white horses on a red hill, First they champ, Then they stamp, Then they stand still. ",
             "answer" : "Teeth"
         },
         "8" : {
             "riddle" : "A box without hinges, key or lid, Yet golden treasure inside is hid.",
             "answer" : "Egg"
         },
         "9" : {
             "riddle" : "Voiceless it cries, Wingless flutters, Toothless bites, Mouthless mutters.",
             "answer" : "Wind"
         }
     }

# Code Reference

James F. Kurose and Keith Ross. 2009. Computer Networking: A Top-Down Approach (5th ed.).
Addison-Wesley Longman Publishing Co., Inc., Boston, MA, USA.