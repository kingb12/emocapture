int vib1 = 12;
int vib2 = 6;
int vib3 = 9;
int vib4 = 10;


void setup() {
  // put your setup code here, to run once:
  pinMode(vib1,OUTPUT);
  pinMode(vib2,OUTPUT);
  pinMode(vib3,OUTPUT);
  pinMode(vib4,OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(vib1,HIGH);
  delay(1000);
  digitalWrite(vib1,LOW);
  digitalWrite(vib2,HIGH);
  delay(1000);
  digitalWrite(vib2,LOW);
  digitalWrite(vib3,HIGH);
  delay(1000);
  digitalWrite(vib3,LOW);
  digitalWrite(vib4,HIGH);
  delay(1000);
  digitalWrite(vib4,LOW);
}
