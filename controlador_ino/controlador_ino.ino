uint8_t M[10]={ B10111110,B00100100,
                B11101010,B11100110,
                B01110100,B11010110,
                B11011110,B10100100,
                B11111110,B11110110};

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
  pinMode(led25,OUTPUT);
  pinMode(led50,OUTPUT);
  pinMode(led75,OUTPUT);
  pinMode(led10,OUTPUT);

}

void loop() {
  //sound(300);
  digitalWrite(latch,LOW);
  //shiftOut(data,clock,LSBFIRST, M[9]);
  //shiftOut(data,clock,LSBFIRST, M[8]);
  //shiftOut(data,clock,LSBFIRST, M[7]);
  shiftOut(data,clock,LSBFIRST, M[6]);
  //shiftOut(data,clock,LSBFIRST, M[5]);
  //shiftOut(data,clock,LSBFIRST, M[4]);
  //shiftOut(data,clock,LSBFIRST, M[3]);
  shiftOut(data,clock,LSBFIRST, M[2]); 
  //shiftOut(data,clock,LSBFIRST, M[1]);
  //shiftOut(data,clock,LSBFIRST, M[0]);

  digitalWrite(latch,HIGH);
  
  digitalWrite(led25,HIGH);
  digitalWrite(led50,HIGH);
  digitalWrite(led75,HIGH);
  digitalWrite(led10,HIGH);

  delay(1000);
}

void sound(int n){
  tone(bzzr,n);
  delay(500);
  noTone(bzzr);

}


