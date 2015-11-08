#include "armband.h"
#include <Adafruit_BLE.h>
#include <Adafruit_BluefruitLE_SPI.h>
#include <Adafruit_BluefruitLE_UART.h>
#include "BluefruitConfig.h"

const int USING_BATTERY = 1;
const int TEST_CMD_MODE = 0;

//
// Create the bluefruit object
//
Adafruit_BluefruitLE_UART ble(BLUEFRUIT_HWSERIAL_NAME, BLUEFRUIT_UART_MODE_PIN);

//
// For printing errors
//
void error(const __FlashStringHelper*err) {
  if (USING_BATTERY)
  {
    while(1)
    {
      digitalWrite(ledpin,HIGH);
      delay(200);
      digitalWrite(ledpin,LOW);
      delay(200);
    }
  }
  else
  {
    Serial.println(err);  
    while (1);
  }
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

  if(USING_BATTERY == 0)
  {
    while (!Serial);  // required for Flora & Micro
  }
  delay(500);

  if(USING_BATTERY == 0)
  {
    Serial.begin(19200);
    Serial.println(F("Adafruit Bluefruit App Controller Example"));
    Serial.println(F("-----------------------------------------"));

    /* Initialise the module */
    Serial.print(F("Initialising the Bluefruit LE module: "));
  }
  
  if ( !ble.begin(VERBOSE_MODE) )
  {
    error(F("Couldn't find Bluefruit, make sure it's in CoMmanD mode & check wiring?"));
  }
  if(USING_BATTERY == 0)
  {
    Serial.println( F("OK!") );
  }
//  if ( FACTORYRESET_ENABLE )
//  {
//    /* Perform a factory reset to make sure everything is in a known state */
//    Serial.println(F("Performing a factory reset: "));
//    if ( ! ble.factoryReset() ){
//      error(F("Couldn't factory reset"));
//    }
//  }


  /* Disable command echo from Bluefruit */
  ble.echo(false);

  if(USING_BATTERY == 0)
  {
    Serial.println("Requesting Bluefruit info:");
  }
//  /* Print Bluefruit information */
  ble.info();
//
//  Serial.println(F("Please use Adafruit Bluefruit LE app to connect in Controller mode"));
//  Serial.println(F("Then activate/use the sensors, color picker, game controller, etc!"));
//  Serial.println();
//
  ble.verbose(false);  // debug info is a little annoying after this point!

  if(TEST_CMD_MODE)
  {
    /* Wait for connection */
    while (! ble.isConnected()) {
        delay(500);
        if(USING_BATTERY)
        {
          digitalWrite(ledpin,HIGH);
          delay(1000);
          digitalWrite(ledpin,LOW);
          delay(500);
        }
        else
        {
          Serial.print(F("*")); 
        }
    }
  }

  digitalWrite(ledpin,HIGH);
  delay(200);
  digitalWrite(ledpin,LOW);

  if(USING_BATTERY == 0)
  {
    Serial.println(F("******************************"));
  }
//  // LED Activity command is only supported from 0.6.6
//  if ( ble.isVersionAtLeast(MINIMUM_FIRMWARE_VERSION) )
//  {
//    // Change Mode LED Activity
//    Serial.println(F("Change LED activity to " MODE_LED_BEHAVIOUR));
//    ble.sendCommandCheckOK("AT+HWModeLED=" MODE_LED_BEHAVIOUR);
//  }
//
//  // Set Bluefruit to DATA mode
//  Serial.println( F("Switching to DATA mode!") );
//  ble.setMode(BLUEFRUIT_MODE_DATA);
  if(USING_BATTERY == 0)
  {
    Serial.println(F("******************************"));
  }
  sendPattern(1);
}

void loop() {
  // put your main code here, to run repeatedly:
   int newData = ble.read();
   if (newData != -1)
   {
     Serial.println(newData);
     digitalWrite(ledpin,HIGH);
     delay(200);
    digitalWrite(ledpin,LOW);
    delay(200);
    digitalWrite(ledpin,HIGH);
    delay(200);
    digitalWrite(ledpin,LOW);
   }
   if (newData == 51)
   {
     sendPattern(1);
     if(USING_BATTERY == 0)
     {
       Serial.println(F("Sending pattern 1"));
     }
   }
   if (newData == 52)
   {
     sendPattern(2);
     Serial.println(F("Sending pattern 2"));
   }
   if (newData == 53)
   {
     sendPattern(3);
     Serial.println(F("Sending pattern 3"));
   }
   if (newData == 54)
   {
     sendPattern(4);
     Serial.println(F("Sending pattern 4"));
   }
   
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






