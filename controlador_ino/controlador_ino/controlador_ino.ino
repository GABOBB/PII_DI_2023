int bzzr = A0;

int data =12;//red
int latch = 11;//rallado
int clock = 10;//blue

int led25 = 2;
int led50 = 3;
int led75 = 4;
int led10 = 5;
//int 

void setup() {
  pinMode(data,OUTPUT);
  pinMode(latch, OUTPUT);
  pinMode(clock,OUTPUT);
  pinMode(bzzr, OUTPUT);
  pintMode(led25,OUTPUT);
  pintMode(led50,OUTPUT);
  pintMode(led75,OUTPUT);
  pintMode(led,OUTPUT);


}

void loop() {
  //sound(300);
  digitalWrite(latch,LOW);
  
  shiftOut(data,clock,LSBFIRST, B11011110);
  shiftOut(data,clock,LSBFIRST, B11101010);
  
  digitalWrite(latch,HIGH);
  delay(1000);
}

void sound(int n){
  tone(bzzr,n);
  delay(500);
  noTone(bzzr);

}