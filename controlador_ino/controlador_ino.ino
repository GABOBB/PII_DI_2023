bool M[10][8]={ {1,1,1,0,1,1,1,0},{0,0,1,0,0,1,0,0},
                {1,0,1,1,1,0,1,0},{1,0,1,1,0,1,1,0},
                {0,1,1,1,0,1,0,0},{1,1,0,1,0,1,1,0},
                {1,1,0,1,1,1,1,0},{1,0,1,0,0,1,0,0},
                {1,1,1,1,1,1,1,0},{1,1,1,1,0,1,1,0}};

const int sndP;
const int led25;
const int led50;
const int led75;
const int led100;

int d_num = 0;
const int dataP = 0;
const int clock = 0;
const int latch = 0;

void setup() {
  pinMode(sndP,OUTPUT);
  pinMode(led25,OUTPUT);
  pinMode(led50,OUTPUT);
  pinMode(led75,OUTPUT);
  pinMode(led100,OUTPUT);

  pinMode(dataP,OUTPUT);
  pinMode(clock,OUTPUT);
  pinMode(latch,OUTPUT);

}

void loop() {
  s_r();
  delay(500);

}

void s_r(){
  if(Serial.available()>0){ //leer si tiene datos en el monitor serial
    String data = Serial.readString();
    Serial.print(" I receive: ");
    Serial.println(data);
    compareIncoming(data);
    }
  }


void compareIncoming(String data){
  if(data == "r"){
    digitalWrite(led100,HIGH);
    d_num--;
    int p = d_num / 10;
    int s = d_num % 10;
    digitalWrite(latch, LOW);
    shiftOut(dataP, clock, LSBFIRST, translater(M[p]));
    shiftOut(dataP, clock, LSBFIRST, translater(M[s]));
    digitalWrite(latch,HIGH);
    tone(sndP,120);
    delay(300);
    noTone(sndP);
    delay(80);
    digitalWrite(led25,LOW);
    digitalWrite(led50,LOW);
    digitalWrite(led75,LOW);
    digitalWrite(led100,LOW);
  }else if(data == "a"){
    
    d_num++;
    int p = d_num / 10;
    int s = d_num % 10;
    digitalWrite(latch, LOW);
    shiftOut(dataP, clock, LSBFIRST, translater(M[p]));
    shiftOut(dataP, clock, LSBFIRST, translater(M[s]));
    digitalWrite(latch,HIGH);
    tone(sndP,220);
    delay(300);
    noTone(sndP);
    
  }else if(data == "25"){

    digitalWrite(led25,HIGH);

  }else if(data == "50"){

    digitalWrite(led50,HIGH);

  }else if(data == "75"){

    digitalWrite(led75,HIGH);
  }
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
