#include <SPI.h>
#include <Ethernet.h>
#include <PubSubClient.h>

// toggle this
bool connectedToARouter = false;

byte mac[] = { 0xDE, 0xED, 0xBA, 0xFE, 0xFE, 0xED };
IPAddress messageQueueIP(10, 0, 1, 4);
IPAddress messageQueueStaticIP(192, 168, 0, 1);
IPAddress arduinoStaticIP(192, 168, 0, 2);
int messageQueuePort = 1883;
char messageQueueUsername[] = "noah";
char messageQueuePassword[] = "herron";

EthernetClient ethernetClient;
PubSubClient pubSubClient(ethernetClient);

void reconnect() {
  while (!pubSubClient.connected()) {
    Serial.print("Attempting connection with message queue... ");
    if (pubSubClient.connect("arduinoClient", messageQueueUsername, messageQueuePassword)) {
      Serial.println("connected");
      pubSubClient.publish("outTopic","hello world");
      Serial.println("Successfully published message");
    } else {
      Serial.print("failed, rc=");
      Serial.print(pubSubClient.state());
      Serial.println(" try again in 5 seconds");
      delay(5000);
    }
  }
}

void setup()
{
  Serial.begin(57600);

  Serial.print("Attempting ethernet connection... ");
  if (connectedToARouter) {
    Ethernet.begin(mac);
  } else {
    Ethernet.begin(mac, arduinoStaticIP);
  }
  Serial.print("connected with local IP ");
  Serial.println(Ethernet.localIP());

  if (connectedToARouter) {
    pubSubClient.setServer(messageQueueIP, messageQueuePort);
  } else {
    pubSubClient.setServer(messageQueueStaticIP, messageQueuePort);
  }
  Serial.print("Arduino configured to consume message queue at ");
  Serial.print(messageQueueIP);
  Serial.print(":");
  Serial.println(messageQueuePort);
}

void loop()
{
  if (!pubSubClient.connected()) {
    reconnect();
  }
  pubSubClient.loop();
}
