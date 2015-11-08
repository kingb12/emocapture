//-----------------------------------------------------------------------------
//
// FILE:        armband.h
// DESCRIPTION: Main header file for arduino (Flora) armband with BLE connection
//
//-----------------------------------------------------------------------------
//

#ifndef __ARMBAND_H__
#define __ARMBAND_H__

//const int NumVibrators = 4;
const int VibPins[] = {12,6,9,10,3,2}; 
const int ledpin = 7;

void sendPattern(int pattern);

#endif // __ARMBAND_H__
