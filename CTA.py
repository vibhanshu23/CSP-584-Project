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


#
#
#serverStatusResult=db.command("serverStatus")
#pprint(serverStatusResult)

client = MongoClient(port=27017)
db=client.myReviews
#myclient = pymongo.MongoClient("mongodb://localhost:27017/")
#mydb = myclient["myReviews"]
mycol = db.CTABus
myCTARoutes = db.CTABusRoutes

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
    
    mydict = { "routeNumber": routeNumber, "routeColour": routeColour, "routeName": routeName}
    x = myCTARoutes.insert_one(mydict)
    
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

            mydict = { "routeNumber": routeNumber, "routeColour": routeColour, "routeName": routeName, "busRouteDirection": busRouteDirection, "stopId": stopId, "stopName": stopName, "stoplat": stoplat,"stoplon":stoplon}
            x = mycol.insert_one(mydict)
            print(x)
#            counter = counter + 1

#print(counter)
