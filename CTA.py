#!/usr/bin/env python
# coding: utf-8



import json
import requests
import datetime
import time
import os
import psycopg2
import pandas as pd
from urllib.request import urlopen
import json
import csv
from pprint import pprint
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT 
from datetime import datetime
import pyodbc
from pymongo import MongoClient

import pymysql.cursors
db_connection = pymysql.connect(host='127.0.0.1',database="exampledatabase", user="root" , passwd="root1234")
cursor = db_connection.cursor()
db_connection.commit()

cursor.execute("DROP TABLE IF EXISTS Routes;")
cursor.execute("DROP TABLE IF EXISTS Stops;")
db_connection.commit()

cursor.execute("""CREATE TABLE Routes(
                 routeNumber varchar(255),
                 routeColour varchar(255),
                 routeName varchar(255)
                 );""")

cursor.execute("""CREATE TABLE Stops(
                 routeNumber varchar(255),
                 routeColour varchar(255),
                 routeName varchar(255),
                 busRouteDirection varchar(255),
                 stopId varchar(255),
                 stopName varchar(255),
                 stoplat varchar(255),
                 stoplon varchar(255)
                 );""")



#serverStatusResult=db.command("serverStatus")
#pprint(serverStatusResult)

# client = MongoClient(port=27017)
# db=client.CTABusData
#myclient = pymongo.MongoClient("mongodb://localhost:27017/")
#mydb = myclient["myReviews"]
# mycol = db.CTABus
# myCTARoutes = db.CTABusRoutes

#cursor = mycol.find()
#for record in cursor:
#    print(record)

response_routes = urlopen('http://www.ctabustracker.com/bustime/api/v2/getroutes?key=zGUVrNRusiVnxcsQt2kMqcBEK&format=json')
response_body_routes = response_routes.read()
routes_json = json.loads(response_body_routes.decode("utf-8"))
#pprint(routes_json)
value1 = routes_json["bustime-response"]
value2 = value1["routes"]
#counter = 0
for value3 in value2:
    routeNumber = value3["rt"]
    routeColour = value3["rtclr"]
    routeName = value3["rtnm"]
    
    # mydict = { "routeNumber": routeNumber, "routeColour": routeColour, "routeName": routeName}
    # x = myCTARoutes.insert_one(mydict)
    cursor.execute("""INSERT INTO Routes (routeNumber,routeColour,routeName) VALUES (%s,%s,%s) """,(routeNumber,routeColour,routeName))
    db_connection.commit()

    response_directions = urlopen("http://www.ctabustracker.com/bustime/api/v2/getdirections?key=zGUVrNRusiVnxcsQt2kMqcBEK&format=json&rt={}".format(routeNumber))
    response_body_directions = response_directions.read()
    directions_json = json.loads(response_body_directions.decode("utf-8"))
#                pprint(directions_json) #{'bustime-response': {'directions': [{'dir': 'Northbound'},{'dir': 'Southbound'}]}}
    value4 = directions_json["bustime-response"]
    value5 = value4["directions"]

    for currentDirection in value5:

        busRouteDirection = currentDirection["dir"]

        response_stops = urlopen("http://www.ctabustracker.com/bustime/api/v2/getstops?key=zGUVrNRusiVnxcsQt2kMqcBEK&format=json&rt={}&dir={}".format(routeNumber,busRouteDirection))
        response_body_stops = response_stops.read()
        stops_json = json.loads(response_body_stops.decode("utf-8"))
#        print(stops_json)
        value6 = stops_json["bustime-response"]
        print(value6)

        value7 = value6["stops"]

        for stopDetail in value7:
#             {
#                "stpid": "2180",
#                "stpnm": "2800 S King Drive",
#                "lat": 41.843451999999,
#                "lon": -87.617151999998
#            },
            stopId = stopDetail["stpid"]
            stopName = stopDetail["stpnm"]
            stoplat = stopDetail["lat"]
            stoplon = stopDetail["lon"]

            print(stoplon)
            cursor.execute("""INSERT INTO Stops (routeNumber,routeColour,routeName,busRouteDirection,stopId,stopName,stoplat,stoplon) VALUES (%s,%s,%s,%s,%s,%s,%s,%s) """,(routeNumber,  routeColour, routeName,  busRouteDirection, stopId, stopName,  stoplat, stoplon))
            db_connection.commit()
# mydict = { }
            # x = mycol.insert_one(mydict)
            # print(x)
#            counter = counter + 1

#print(counter)
