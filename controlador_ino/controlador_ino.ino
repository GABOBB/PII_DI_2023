uint8_t M[10] = {
  B10111110, B00100100,
  B11101010, B11100110,
  B01110100, B11010110,
  B11011110, B10100100,
  B11111110, B11110110
};

int bzzr = A0;
int dataPin = 12;
int latchPin = 11;
int clockPin = 10;
int led25 = 2;
int led50 = 3;
int led75 = 4;
int led100 = 5;

void setup() {
  pinMode(dataPin, OUTPUT);
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
  pinMode(bzzr, OUTPUT);
  pinMode(led25, OUTPUT);
  pinMode(led50, OUTPUT);
  pinMode(led75, OUTPUT);
  pinMode(led100, OUTPUT);

  digitalWrite(latchPin, LOW);
  shiftOut(dataPin, clockPin, LSBFIRST, M[0]);
  shiftOut(dataPin, clockPin, LSBFIRST, M[0]);
  digitalWrite(latchPin, HIGH);

  Serial.begin(9600);
}

void loop() {
  if (Serial.available()) {
    String data = Serial.readString();
    Serial.println(data);
    compareIncoming(data);
  }

  delay(100);
}

void compareIncoming(String data) {
  if (data.startsWith("N")) {
    int x = data.charAt(1) - '0';
    int y = data.charAt(2) - '0';
    set_num(x, y);
  } else if (data == "s1\n") {
    Serial.println("s1");
    sound(300);
  } else if (data == "s2\n") {
    Serial.println("s2");
    sound(500);
  } else if (data == "0\n"){
    digitalWrite(led25, LOW);
    digitalWrite(led50, LOW);
    digitalWrite(led75, LOW);
    digitalWrite(led100, LOW);
  } else if (data == "25\n") {
    digitalWrite(led25, HIGH);
    digitalWrite(led50, LOW);
    digitalWrite(led75, LOW);
    digitalWrite(led100, LOW);
  } else if (data == "50\n") {
    digitalWrite(led25, HIGH);
    digitalWrite(led50, HIGH);
    digitalWrite(led75, LOW);
    digitalWrite(led100, LOW);
  } else if (data == "75\n") {
    digitalWrite(led25, HIGH);
    digitalWrite(led50, HIGH);
    digitalWrite(led75, HIGH);
    digitalWrite(led100, LOW);
  } else if (data == "100\n") {
    digitalWrite(led25, HIGH);
    digitalWrite(led50, HIGH);
    digitalWrite(led75, HIGH);
    digitalWrite(led100, HIGH);
  } else{
    Serial.println("no llega nada");

  }
}

void set_num(int x, int y) {
  digitalWrite(latchPin, LOW);
  shiftOut(dataPin, clockPin, LSBFIRST, M[y]);
  shiftOut(dataPin, clockPin, LSBFIRST, M[x]);
  digitalWrite(latchPin, HIGH);
}

void sound(int n) {
  tone(bzzr, n);
  delay(500);
  noTone(bzzr);
}
