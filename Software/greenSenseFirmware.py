import time
import board
from busio import I2C
import adafruit_bme680
import RPi.GPIO as GPIO
import Adafruit_ADS1x15
import os
import glob
import firebase_admin
import google.cloud
from firebase_admin import credentials
from firebase_admin import firestore

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

GPIO.cleanup()

#water pump setup
waterPIN = 27
GPIO.setup(waterPIN,GPIO.OUT)

#servo setup
servoPIN = 5
GPIO.setup(servoPIN, GPIO.OUT)

p = GPIO.PWM(servoPIN, 50) # GPIO servoPIN PWM with 50Hz

#fan setup
fanPIN = 17
GPIO.setup(fanPIN,GPIO.OUT)

#stepper setup
control_pins = [6,13,19,26]

for pin in control_pins:
    GPIO.setup(pin, GPIO.OUT)
    GPIO.output(pin, 0)
  
#clockwise winding sequence
halfstep_seq_CW = [
  [1,0,0,0],
  [1,1,0,0],
  [0,1,0,0],
  [0,1,1,0],
  [0,0,1,0],
  [0,0,1,1],
  [0,0,0,1],
  [1,0,0,1]
]
#counterclockwise winding sequence
halfstep_seq_CCW = [
  [0,0,0,1],
  [0,0,1,1],
  [0,0,1,0],
  [0,1,1,0],
  [0,1,0,0],
  [1,1,0,0],
  [1,0,0,0],
  [1,0,0,1]
]

def shadeOPEN():
    #clockwise loop (OPEN)
    for i in range(1025):
        for halfstep in range(8):
            for pin in range(4):
                GPIO.output(control_pins[pin], halfstep_seq_CW[halfstep][pin])
            time.sleep(0.001)


def shadeCLOSE():
    #counterclockwise loop (CLOSE) 
    for i in range(1025):
        for halfstep in range(8):
            for pin in range(4):
                GPIO.output(control_pins[pin], halfstep_seq_CCW[halfstep][pin])
            time.sleep(0.001)

def waterON():
    GPIO.output(waterPIN,GPIO.HIGH)
    
def waterOFF():
    GPIO.output(waterPIN,GPIO.LOW)

def shutterOPEN():
    GPIO.setup(servoPIN, GPIO.OUT)
    p.start(3)
    #open cycle starts
    p.ChangeDutyCycle(3)
    time.sleep(0.1)
    p.ChangeDutyCycle(3.25)
    time.sleep(0.1)
    p.ChangeDutyCycle(3.5)
    time.sleep(0.1)
    p.ChangeDutyCycle(3.75)
    time.sleep(0.1)
    p.ChangeDutyCycle(4)
    time.sleep(0.1)
    p.ChangeDutyCycle(4.25)
    time.sleep(0.1)
    p.ChangeDutyCycle(4.5)
    time.sleep(0.1)
    p.ChangeDutyCycle(4.75)
    time.sleep(0.1)
    p.ChangeDutyCycle(5)
    time.sleep(0.1)
    p.ChangeDutyCycle(5.25)
    time.sleep(0.1)
    p.ChangeDutyCycle(5.5)
    time.sleep(0.1)
    p.ChangeDutyCycle(5.75)
    time.sleep(0.1)
    p.ChangeDutyCycle(6)
    time.sleep(0.1)
    p.ChangeDutyCycle(6.25)
    time.sleep(0.1)
    p.ChangeDutyCycle(6.5)
    time.sleep(0.1)
    p.ChangeDutyCycle(6.75)
    time.sleep(0.1)
    p.ChangeDutyCycle(7)
    time.sleep(1)
    GPIO.setup(servoPIN, GPIO.IN)
def shutterCLOSE():
    GPIO.setup(servoPIN, GPIO.OUT)
    p.start(7)
    #close cycle starts
    time.sleep(1)
    p.ChangeDutyCycle(6.75)
    time.sleep(0.1)
    p.ChangeDutyCycle(6.5)
    time.sleep(0.1)
    p.ChangeDutyCycle(6.25)
    time.sleep(0.1)
    p.ChangeDutyCycle(6)
    time.sleep(0.1)
    p.ChangeDutyCycle(5.75)
    time.sleep(0.1)
    p.ChangeDutyCycle(5.5)
    time.sleep(0.1)
    p.ChangeDutyCycle(5.25)
    time.sleep(0.1)
    p.ChangeDutyCycle(5)
    time.sleep(0.1)
    p.ChangeDutyCycle(4.75)
    time.sleep(0.1)
    p.ChangeDutyCycle(4.5)
    time.sleep(0.1)
    p.ChangeDutyCycle(4.25)
    time.sleep(0.1)
    p.ChangeDutyCycle(4)
    time.sleep(0.1)
    p.ChangeDutyCycle(3.75)
    time.sleep(0.1)
    p.ChangeDutyCycle(3.5)
    time.sleep(0.1)
    p.ChangeDutyCycle(3.25)
    time.sleep(0.1)
    p.ChangeDutyCycle(3)
    time.sleep(0.1)
    GPIO.setup(servoPIN, GPIO.IN)
    
def fanON():
    #turn fan on
    GPIO.output(fanPIN,GPIO.HIGH)
    
def fanOFF():
    #turn fan off
    GPIO.output(fanPIN,GPIO.LOW)

# Use a service account
cred = credentials.Certificate('ServiceAccountKey.json')
app = firebase_admin.initialize_app(cred)

db = firestore.client()
doc_ref = db.collection(u'Readings').document(u'Values').collection(u'Data').document(u'Sensors')
doc_ref1 = db.collection(u'Control').document(u'Pump')
# Create a callback on_snapshot function to capture changes
def on_snapshot(col_snapshot, changes, read_time):
    for change in changes:
        if change.document.id == 'Fan' and change.type.name == 'ADDED':
            print("Fan is now ON")
            shutterOPEN()
            time.sleep(1)
            fanON() 
        if change.document.id == 'Fan' and change.type.name == 'REMOVED':
            print("Fan is now OFF")
            fanOFF()
            time.sleep(1)
            shutterCLOSE()
            
        if change.document.id == 'Pump' and change.type.name == 'ADDED':
            print("Pump is now ON")
            waterON()
            time.sleep(2) #turn on for 2 seconds
            doc_ref1.set({
                u'Active': 'OFF'
            })
            waterOFF() 
        if change.document.id == 'Pump' and change.type.name == 'ADDED':
            print("Pump is now OFF")

        if change.document.id == 'Shade' and change.type.name == 'ADDED':
            print("Shade is now CLOSED")
            time.sleep(1)
            shadeCLOSE() 
        if change.document.id == 'Shade' and change.type.name == 'REMOVED':
            print("Shade is now OPEN")
            time.sleep(1)
            shadeOPEN()
            
col_query = db.collection(u'Control').where(u'Active', u'==', u'ON')
query_watch = col_query.on_snapshot(on_snapshot)

# Create an ADS1115 ADC (16-bit) instance.
adc = Adafruit_ADS1x15.ADS1115()
GAIN = 1
# Create library object using our Bus I2C port
i2c = I2C(board.SCL, board.SDA)
bme680 = adafruit_bme680.Adafruit_BME680_I2C(i2c, debug=False)

# change this to match the location's pressure (hPa) at sea level
bme680.sea_level_pressure = 1013.25

os.system('modprobe w1-gpio')
os.system('modprobe w1-therm')
 
base_dir = '/sys/bus/w1/devices/'
device_folder = glob.glob(base_dir + '28*')[0]
device_file = device_folder + '/w1_slave'
 
def read_temp_raw():
    f = open(device_file, 'r')
    lines = f.readlines()
    f.close()
    return lines
 
def read_temp():
    lines = read_temp_raw()
    while lines[0].strip()[-3:] != 'YES':
        time.sleep(0.2)
        lines = read_temp_raw()
    equals_pos = lines[1].find('t=')
    if equals_pos != -1:
        temp_string = lines[1][equals_pos+2:]
        temp_c = float(temp_string) / 1000.0
        temp_f = temp_c * 9.0 / 5.0 + 32.0
        return temp_c
    
def read_gas():
    gas_baseline = bme680.gas

    hum_baseline = 40.0
    hum_weighting = 0.25
    
    gas_offset = gas_baseline

    hum = bme680.humidity
    hum_offset = hum - hum_baseline
    
    if hum_offset > 0:
        hum_score = (100 - hum_baseline - hum_offset)
        hum_score /= (100 - hum_baseline)
        hum_score *= (hum_weighting * 100)

    else:
        hum_score = (hum_baseline + hum_offset)
        hum_score /= hum_baseline
        hum_score *= (hum_weighting * 100)

    if gas_offset > 0:
        gas_score = (bme680.gas / gas_baseline)
        gas_score *= (100 - (hum_weighting * 100))

    else:
        gas_score = 100 - (hum_weighting * 100)

    air_quality_score = hum_score + gas_score
    
    #print('Air Quality: %0.1f' %air_quality_score)
    return air_quality_score

def soil_to_percent(read):
    numMIN = 23000  #higher number means lower moisture content
    numMAX = 11500  #lower number means higher moisture content

    #converts analog reading to percentage
    soilPercent = (read - numMIN)/(numMAX - numMIN) * 100

    #prints value with one decimal place
    ##print("{:.1f}".format(abs(soilPercent)))
    return soilPercent

while True:
    
    print('Humidity: %0.1f %%' % bme680.humidity)
    print('Air Quality: %0.1f' %read_gas())
    print("Soil = %0.1f" %soil_to_percent(adc.read_adc(0, gain=GAIN)))
    print('Temp in C: %0.f' %read_temp())
    print("")
    temp =  int(read_temp())
    humid = int(bme680.humidity)
    gas =  int(read_gas())
    soil = int(soil_to_percent(adc.read_adc(0, gain=GAIN)))

    doc_ref.set({
        u'Temp': temp,
        u'Humidity': humid,
        u'AirQ': gas,
        u'Soil': soil
    })
    
    time.sleep(30)
