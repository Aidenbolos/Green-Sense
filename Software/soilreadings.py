# soilreadings.py
# Read analog values from ADS1115 for soil moisture and output GPIO pin to turn on motor
# Author: Tony DiCola (modified by Daniel Bujold)
# License: Public Domain

import time
import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(22,GPIO.OUT)

# Import the ADS1x15 module.
import Adafruit_ADS1x15

# Create an ADS1115 ADC (16-bit) instance.
adc = Adafruit_ADS1x15.ADS1115()

# Note you can change the I2C address from its default (0x48), and/or the I2C
# bus by passing in these optional parameters:
#adc = Adafruit_ADS1x15.ADS1015(address=0x49, busnum=1)

# Choose a gain of 1 for reading voltages from 0 to 4.09V.
# Or pick a different gain to change the range of voltages that are read:
#  - 2/3 = +/-6.144V
#  -   1 = +/-4.096V
#  -   2 = +/-2.048V
#  -   4 = +/-1.024V
#  -   8 = +/-0.512V
#  -  16 = +/-0.256V
# See table 3 in the ADS1015/ADS1115 datasheet for more info on gain.
GAIN = 1

print('Reading ADS1x15 values, press Ctrl-C to quit...')
# Print nice channel column headers.
print('| {0:>6} | {1:>6} | {2:>6} | {3:>6} |'.format(*range(4)))
print('-' * 37)
# Main loop.
while True:
    # Read all the ADC channel values in a list.
    values = [0]*4
    for i in range(4):
        # Read the specified ADC channel using the previously set gain value.
        values[i] = adc.read_adc(i, gain=GAIN)
        # Note you can also pass in an optional data_rate parameter that controls
        # the ADC conversion time (in samples/second). Each chip has a different
        # set of allowed data rate values, see datasheet Table 9 config register
        # DR bit values.
        #values[i] = adc.read_adc(i, gain=GAIN, data_rate=128)
        # Each value will be a 12 or 16 bit signed integer value depending on the
        # ADC (ADS1015 = 12-bit, ADS1115 = 16-bit).
    # Print the ADC values.
    print('| {0:>6} | {1:>6} | {2:>6} | {3:>6} |'.format(*values))

    # Declare variable for watering level switch
    water_lvl = 0

    # Declare variable for moisture reference value
    ref = 13000

    # Set watering level
    if (values[1] < 1000):
	water_lvl = 0.5
    elif (values[1] > 12000 and values[1] < 14000):
	water_lvl = 1.5
    elif (values[1] > 24000):
	water_lvl = 3
    else:
	water_lvl = 1

    # Turn on motor/watering system if soil moisture is too low
    if (values[0] > ref):
        GPIO.output(22,GPIO.HIGH)
        # Turn motor on for water_lvl seconds
	   time.sleep(water_lvl)
        GPIO.output(22,GPIO.LOW)
        # sleep to allow water absorbtion before recheck
	   time.sleep(10)

    # Pause for 2 seconds.
    time.sleep(2)
