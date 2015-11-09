# emocapture
Discreet Emotion recognition tool using EmoVu for persons with facial expression and emotional recognition deficits. This is 
a project for the 2015 Center for Sensorimotor Neural Engineering Hackathon, and will not be maintained past the API license expiriration in mid November. If you would like to replicate this work for yourself, support can be provided, please email!

## Hardware
An adafruit FLORA was loaded with an arduino sketch controlling vibration motor patterns on 4 vibrotactile motors, used as the output of discreet messages. An integer value was passed (in string form) via serial line to the adafruit, which interpretted the integer and chose a vibration pattern based on the results. An integer passed represents a present emotional state interpretted by EmoVu.

## Software
Our Project is implemented as a Java package with maven. It makes use of the EmoVu web API, jssc serial library, among others.

### Usage
The main method in the App.Java class runs the function of the Emotional interpretter.
