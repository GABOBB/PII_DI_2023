bool M[10][8]={ {1,1,1,0,1,1,1,0},{0,0,1,0,0,1,0,0},
                {1,0,1,1,1,0,1,0},{1,0,1,1,0,1,1,0},
                {0,1,1,1,0,1,0,0},{1,1,0,1,0,1,1,0},
                {1,1,0,1,1,1,1,0},{1,0,1,0,0,1,0,0},
                {1,1,1,1,1,1,1,0},{1,1,1,1,0,1,1,0}};

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

uint8_t translater(bool ary[]){
  uint8_t val = B00000000;
  for(int x=0; x<8; x++){
    val = val<<1;
    if(ary[x] == HIGH){
      val= val+B1;
    }
  }
  return val;
}
