bool s_0[7] = {1,1,1,0,1,1,1};
bool s_1[7] = {0,0,1,0,0,1,0};
bool s_2[7] = {1,0,1,1,1,0,1};
bool s_3[7] = {1,0,1,1,0,1,1};
bool s_4[7] = {0,1,1,1,0,1,0};
bool s_5[7] = {1,1,0,1,0,1,1};
bool s_6[7] = {1,1,0,1,1,1,1};
bool s_7[7] = {1,0,1,0,0,1,0};
bool s_8[7] = {1,1,1,1,1,1,1};
bool s_9[7] = {1,1,1,1,0,1,1};

void setup() {
  // put your setup code here, to run once:

}

void loop() {
  

}

void s_r(){
  if(Serial.available()>0){ //leer si tiene datos en el monitor serial
    String data = Serial.readString();
    Serial.print(" I receive: ");
    Serial.println(data);
    compareIncoming(data);
    }
  }
}

