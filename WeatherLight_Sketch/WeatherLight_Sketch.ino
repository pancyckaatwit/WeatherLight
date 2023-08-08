/**
 * Author:    Brandon Soares , Caleb Ryan Brown
 * Created:   08.01.2023
 *  
 **/

#include <WiFi.h>
#include <WiFiClient.h>
#include <WiFiServer.h>
#include <WiFiUdp.h>
#include <WiFiManager.h> // https://github.com/tzapu/WiFiManager

// Effect Vars
char* RGB = "RGB";
char* CLOUD = "CLD";
char* SUN = "SUN";
char* SNOW = "SNO";
char* STORM = "STR";
char* RAIN = "RAI";

// Pin Vars
int REDPIN = 21;
int GREENPIN = 19;
int BLUEPIN = 18;

// RGB Vars
int redSet = 0;
int greenSet = 0;
int blueSet = 0;

// RGB Tempurature Vars
int redT = 0;
int greenT = 0;
int blueT = 0;

// Network Vars
const char* ssid = "Linksys"; // SSID and password stored here are for debugging only
const char* password = "password";
WiFiServer server(25565);
WiFiUDP udp;

//
bool receivedUDP = false;
IPAddress broadcastIP;

// Initialization
void setup() {

  // put your setup code here, to run once:
  Serial.begin(115200);

  //WiFiManager, Local intialization. Once its business is done, there is no need to keep it around
  WiFiManager wm;

  bool res;
  // Password can be set, defaults to "password"
  res = wm.autoConnect("AutoConnectAP", "password");

  if (!res) {
    Serial.println("Failed to connect");
    // ESP.restart();
  }
  else {
    //if you get here you have connected to the WiFi
    Serial.println("Successfully connected to WiFi");
  }

  IPAddress ip = WiFi.localIP();
  IPAddress subnet = WiFi.subnetMask();
  // Perform a bitwise operation to get the broadcast address for the LAN
  broadcastIP = ip | ~subnet;
  Serial.print("Broadcast Address: ");
  Serial.println(broadcastIP);

  // Initialize RGB LED's
  initializeRGB();
  setColor(0, 255, 200); // Setup Started
  
  server.begin();
  Serial.println("Server started");
  Serial.print("WeatherLight IP address: ");
  Serial.println(WiFi.localIP());

  udp.begin(25565);
  setColor(0, 0, 0); // Setup Complete
}

void loop() {
  // Make available any time the loop is reset
  WiFiClient client = server.available();

  if (client) {
    Serial.println("New client connected");
    setColor(0,0,0); //Set RGB values to zero

    // Check if client connected
    while (client.connected()) {
      //  
      while (client.available()) {
        String data = client.readStringUntil('\n');
        // Convert the input string to a character array
        char charArray[data.length() + 1];
        data.toCharArray(charArray, sizeof(charArray));

        // Split the string using strtok()
        char* token = strtok(charArray, " ");
        int x = 0;
        
        // Iterate through the tokens and print them
        while (token != NULL) {
          Serial.printf("token char* : %s\n" , token);
          // Serial.printf("sizeof(token) : %d\n" , strlen(token));

          // Primary RGB effect, for Tempurature display
          if (cmp3char(token,RGB)) {

            Serial.println("RGB");
            token = strtok(NULL, " ");
            redT = atoi(token);
            Serial.printf("token char* : %s\n" , token);
            token = strtok(NULL, " ");
            greenT = atoi(token);
            Serial.printf("token char* : %s\n" , token);
            token = strtok(NULL, " ");
            blueT = atoi(token);
            Serial.printf("token char* : %s\n" , token);

            // SUN effect
          } else if (cmp3char(token,SUN)) {
            Serial.println("SUN");
            fadeEffect(0, 0, 0, 500);
            sunEffect();
            sunEffect();
            fadeEffect(0, 0, 0, 500);

            // RAIN efect
          } else if (cmp3char(token,RAIN)) {
            Serial.println("RAIN");
            fadeEffect(0, 0, 0, 500);
            rainEffect();
            rainEffect();
            fadeEffect(0, 0, 0, 500);

            // CLOUD effect
          } else if (cmp3char(token,CLOUD)) {
            Serial.println("CLOUD");
            fadeEffect(0, 0, 0, 500);
            cloudEffect();
            cloudEffect();
            fadeEffect(0, 0, 0, 500);

            // SNOW effect
          } else if (cmp3char(token,SNOW)) {
            Serial.println("SNOW");
            fadeEffect(0, 0, 0, 500);
            snowEffect();
            snowEffect();
            fadeEffect(0, 0, 0, 500);

            // STORM effect
          } else if (cmp3char(token,STORM)) {
            Serial.println("STORM");
            fadeEffect(0, 0, 0, 500);
            stormEffect();
            stormEffect();
            fadeEffect(0, 0, 0, 500);

          // Secondary RGB effect
          } else if (x == 0) {
            redSet = atoi(token);
            Serial.println("RED");
            Serial.println(redSet);
            changeRed(redSet);
            x = 1;
          } else if (x == 1) {
            greenSet = atoi(token);
            Serial.println("GREEN");
            Serial.println(greenSet);
            changeGreen(greenSet);
            x = 2;
          } else if (x == 2) {
            blueSet = atoi(token);
            Serial.println("BLUE");
            Serial.println(blueSet);
            changeBlue(blueSet);
            x = 0;
            delay(2000);
          }

          token = strtok(NULL, " ");
        }
        fadeEffect(redT, greenT, blueT, 500);
        delay(500);
      }
    }

    client.stop();
    Serial.println("Client disconnected");
  } else {
    // Broadcasts IP address every second
    // Recommended only to run on a private LAN
      IPAddress ip = WiFi.localIP();

      String ip2 = IpAddress2String(ip);

      udp.beginPacket(broadcastIP, 25565);
      udp.print(ip2);
      udp.endPacket();

      Serial.println("Message sent over UDP: " + ip2);

      delay(1000);
  }

  // Setup loop color display
  fadeEffect(255, 0, 0, 500);
  fadeEffect(0, 255, 0, 500);
  fadeEffect(0, 0, 255, 500);

}




// returns IP address in standard string format
String IpAddress2String(const IPAddress& ipAddress) {
  return String(ipAddress[0]) + String(".") + String(ipAddress[1]) + String(".") + String(ipAddress[2]) + String(".") + String(ipAddress[3]);
}

// compares the first 3 char's in a char* array
bool cmp3char(char* char1, char* char2) {
  if (char1[0] == char2[0] && char1[1] == char2[1] && char1[2] == char2[2]) return true;
  return false;
}

// Starts RGB interface for single LED
void initializeRGB() {
  redSet = 0;
  greenSet = 0;
  blueSet = 0;
  pinMode(REDPIN, OUTPUT);
  pinMode(GREENPIN, OUTPUT);
  pinMode(BLUEPIN, OUTPUT);
  analogWrite(REDPIN, redSet);
  analogWrite(GREENPIN, greenSet);
  analogWrite(BLUEPIN, blueSet);
}

// RGB pins values change
void setColor(int redValue, int greenValue, int blueValue) {
  analogWrite(REDPIN, redValue);
  analogWrite(GREENPIN, greenValue);
  analogWrite(BLUEPIN, blueValue);
  redSet = redValue;
  greenSet = greenValue;
  blueSet = blueValue;
}

// red pin value change
void changeRed(int redValue) {
  analogWrite(REDPIN, redValue);
  redSet = redValue;
}

// green pin value change
void changeGreen(int greenValue) {
  analogWrite(GREENPIN, greenValue);
  greenSet = greenValue;
}

// blue pin value change
void changeBlue(int blueValue) {
  analogWrite(BLUEPIN, blueValue);
  blueSet = blueValue;
}

// Fade's from current RGB value's to new RGB values
void fadeEffect(int newRed, int newGreen, int newBlue, int durationMilliSec) {
  // number of iterations in the duration
  int increment = 8;
  // finds difference between the old and new value
  int redDiff = newRed - redSet;
  int greenDiff = newGreen - greenSet;
  int blueDiff = newBlue - blueSet;
  // floor div creates the size of the iterations step
  int redInc = redDiff / increment;
  int greenInc = greenDiff / increment;
  int blueInc = blueDiff / increment;
  // fades the light using PWM and delay
  for(int i = 0; i < increment; i++) {
    setColor(redSet + redInc, greenSet + greenInc, blueSet + blueInc);
    delay(durationMilliSec / (increment + 1));
  }
  // additional update to account for remainder
  setColor(redSet + (redDiff % increment), greenSet + (greenDiff % increment), blueSet + (blueDiff % increment));
  delay(durationMilliSec / (increment + 1));
}

void cloudEffect() {
  fadeEffect(204,204,204,500);
  fadeEffect(171,171,171,500);
  fadeEffect(116,116,116,250);
  fadeEffect(235,235,235,500);
  fadeEffect(204,204,204,500);
  fadeEffect(144,144,144,500);
  fadeEffect(171,171,171,250);
  fadeEffect(116,116,116,250);
  fadeEffect(171,171,171,500);
  fadeEffect(116,116,116,500);
  fadeEffect(235,235,235,250);
  fadeEffect(171,171,171,500);
}

void sunEffect() {
  fadeEffect(255,204, 10,500);
  fadeEffect(223,143, 10,500);
  fadeEffect(162, 97, 10,250);
  fadeEffect(235,211, 15,500);
  fadeEffect(255,204, 10,500);
  fadeEffect(180,126, 10,500);
  fadeEffect(223,143, 10,250);
  fadeEffect(162, 97, 10,250);
  fadeEffect(223,143, 10,500);
  fadeEffect(162, 97, 10,500);
  fadeEffect(235,211, 20,250);
  fadeEffect(223,143, 10,500);
}

void stormEffect() {
  fadeEffect(204,204,204,500);
  fadeEffect(171,171,171,500);
  fadeEffect(252,192, 30,250);
  fadeEffect(235,235,235,500);
  fadeEffect(204,204,204,500);
  fadeEffect(144,144,144,500);
  fadeEffect(252,192, 30,250);
  fadeEffect(116,116,116,250);
  fadeEffect(171,171,171,500);
  fadeEffect(116,116,116,500);
  fadeEffect(235,235,235,250);
  fadeEffect(252,192, 30,500);
}

void rainEffect() {
  fadeEffect(0, 0, 255, 500);  
  fadeEffect(0, 0, 60, 500);   
  fadeEffect(0, 0, 255, 250);   
  fadeEffect(0, 0, 60, 500);  
  fadeEffect(0, 0, 255, 500);  
  fadeEffect(0, 0, 60, 500);   
  fadeEffect(0, 0, 255, 250);  
  fadeEffect(0, 0, 60, 250);   
  fadeEffect(0, 0, 255, 500);   
  fadeEffect(0, 0, 60, 500);  
  fadeEffect(0, 0, 255, 250);  
  fadeEffect(0, 0, 60, 500);   
}

void snowEffect() {
  fadeEffect(204,204,204,500);
  fadeEffect(171,171,171,500);
  fadeEffect(116,116,116,250);
  fadeEffect(235,235,235,500);
  fadeEffect(204,204,204,500);
  fadeEffect(144,144,144,500);
  fadeEffect(171,171,171,250);
  fadeEffect(116,116,116,250);
  fadeEffect(171,171,171,500);
  fadeEffect(116,116,116,500);
  fadeEffect(235,235,235,250);
  fadeEffect(171,171,171,500);
}