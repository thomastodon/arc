# Messaging Client

## spinning things up locally

Mount the [W5100 Ethernet Shield](https://www.sunfounder.com/ethernet-shield-w5100-for-arduino.html) onto the [Arduino UNO](https://www.arduino.cc/en/Main/ArduinoBoardUno)

Connect the [W5100 Ethernet Shield](https://www.sunfounder.com/ethernet-shield-w5100-for-arduino.html) to your [router](http://www.apple.com/airport-time-capsule/) with a patch cable

Your router should be configured to assign IP addresses dynamically with DHCP

Get the IP address of your local device, which is hosting the message queue. Update the `messageQueueIP` in the sketch with what outputs from the below.
```bash
$ ipconfig getifaddr en1
```

If you have difficulty connecting to your local network, confirm that the MAC address of your arduino ethernet shield is unique. You should be able to see the MAC address and its corresponding IP address listed amongst other devices on your local network
```bash
$ arp -a
```