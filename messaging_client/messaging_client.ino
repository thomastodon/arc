#include <SPI.h>
#include <Ethernet.h>
#include <PubSubClient.h>

// Update these with values suitable for your network.
byte mac[] = { 0xDE, 0xED, 0xBA, 0xFE, 0xFE, 0xED };
IPAddress messageQueueIP(10, 0, 1, 4);
int messageQueuePort = 1883;
char messageQueueUsername[] = "noah";
char messageQueuePassword[] = "herron";

EthernetClient ethClient;
PubSubClient client(ethClient);

void reconnect() {
  while (!client.connected()) {
    Serial.print("Attempting connection with message queue... ");
    if (client.connect("arduinoClient", messageQueueUsername, messageQueuePassword)) {
      Serial.println("connected");
      client.publish("outTopic","hello world");
      Serial.println("Successfully published message");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void setup()
{
  Serial.begin(57600);

  Serial.print("Attempting ethernet connection... ");
  Ethernet.begin(mac);
  Serial.print("connected with local IP ");
  Serial.println(Ethernet.localIP());
  
  client.setServer(messageQueueIP, messageQueuePort);
  Serial.print("Arduino configured to consume message queue at ");
  Serial.print(messageQueueIP);
  Serial.print(":");
  Serial.println(messageQueuePort);
  
  // Allow the hardware to sort itself out
  delay(1500);
}

void loop()
{
  if (!client.connected()) {
    reconnect();
  }
  client.loop();
}
