# Messaging Client

## running things locally

Mount the [W5100 Ethernet Shield](https://www.sunfounder.com/ethernet-shield-w5100-for-arduino.html) onto the [Arduino UNO](https://www.arduino.cc/en/Main/ArduinoBoardUno)

Tell the sketch whether or not you are `connectedToARouter`

### if connecting the arduino to a router:

Connect the [W5100 Ethernet Shield](https://www.sunfounder.com/ethernet-shield-w5100-for-arduino.html) to the router with a patch cable

The router should be configured to assign IP addresses dynamically with DHCP

Get the IP address of your local device, which is hosting the message queue. Update the `messageQueueIP` in the sketch with what outputs from the below.
```bash
$ ipconfig getifaddr en1
```

### if connecting the arduino to another computer:

Connect the [W5100 Ethernet Shield](https://www.sunfounder.com/ethernet-shield-w5100-for-arduino.html) to the other computer computer with a cross-over cable

Configure the **static IP address** and **subnet mask** of the other computer as `192.168.0.1` and `255.255.255.0`, respectively.

The **static IP address** and **subnet mask** of the arduino are configured as `192.168.0.2` and `255.255.255.0`, respectively.

### if things aren't working:

If you have difficulty connecting to your local network, confirm that the MAC address of your arduino ethernet shield is unique. You should be able to see the MAC address and its corresponding IP address listed amongst other devices on your local network
```bash
$ arp -a
```
```bash
$ sudo arp-scan --interface=en1 --localnet
```