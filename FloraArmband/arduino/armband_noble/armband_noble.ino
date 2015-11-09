#include "armband.h"

//
// For printing errors
//
void error(const __FlashStringHelper*err) {
  Serial.println(err);  
  while (1);
}

//
// Setup, to be run only once at beginning of program
//
void setup() {
  pinMode(VibPins[0],OUTPUT);
  pinMode(VibPins[1],OUTPUT);
  pinMode(VibPins[2],OUTPUT);
  pinMode(VibPins[3],OUTPUT);
  pinMode(ledpin,OUTPUT);


  //while (!Serial);  // required for Flora & Micro
  delay(500);
  Serial.begin(19200);
  sendPattern(1);
}

void loop() {
  // put your main code here, to run repeatedly:
  static char  stringIn[17];  // Enough space for 16 characters
  static char  *pStringIn; // Used for taking 
  static int   compInput = 0;

  //
  // Controller variable storage
  //
  pStringIn = stringIn;
  while (Serial.available())
  {
    noInterrupts();
    *pStringIn++ = Serial.read();
    interrupts();
  }

  //
  // Take action based on input from PC
  //
  if (pStringIn > stringIn)
  {
        compInput = atoi(stringIn);
        Serial.println(compInput);
  }

   if (compInput == 1)
   {
     sendPattern(1);
     Serial.println(F("Sending pattern 1"));
   }
   if (compInput == 2)
   {
     sendPattern(2);
     Serial.println(F("Sending pattern 2"));
   }
   if (compInput == 3)
   {
     sendPattern(3);
     Serial.println(F("Sending pattern 3"));
   }
   if (compInput == 4)
   {
     sendPattern(4);
     Serial.println(F("Sending pattern 4"));
   }
   memset(stringIn, '\0',sizeof(compInput));
   compInput = 0;
}

//
// Send patterns to vibrators
// Pattern 1: buzz consecutively each 0-3 for 0.5 sec
// Pattern 2: buzz consecutively each 3-0 for 0.5 sec
// Pattern 3: buzz #0 and #2 alternating twice
// Pattern 4: buzz #1 and #3 alternating twice
void sendPattern(int pattern){
  if (pattern == 1)
  {
    digitalWrite(VibPins[0],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[0],LOW);
    digitalWrite(VibPins[1],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[1],LOW);
    digitalWrite(VibPins[2],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[2],LOW);
    digitalWrite(VibPins[3],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[3],LOW);
  }
  if (pattern == 2)
  {
    digitalWrite(VibPins[0],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[0],LOW);
    digitalWrite(VibPins[3],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[3],LOW);
    digitalWrite(VibPins[2],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[2],LOW);
    digitalWrite(VibPins[1],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[1],LOW);
  }
  
  if (pattern == 3)
  {
    digitalWrite(VibPins[0],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[0],LOW);
    digitalWrite(VibPins[2],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[2],LOW);
    digitalWrite(VibPins[0],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[0],LOW);
    digitalWrite(VibPins[2],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[2],LOW);
  }
  
  if (pattern == 3)
  {
    digitalWrite(VibPins[1],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[1],LOW);
    digitalWrite(VibPins[3],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[3],LOW);
    digitalWrite(VibPins[1],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[1],LOW);
    digitalWrite(VibPins[3],HIGH);
    delay(500); // delay for 0.5 sec
    digitalWrite(VibPins[3],LOW);
  }
}






