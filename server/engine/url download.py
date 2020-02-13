# Easy Python script to download images dataset from url txt file.

import urllib.request
import os
import socket


os.chdir("E://Programmation//Python//url_retrieve//")
socket.setdefaulttimeout(15)
with open("url.txt", mode='r') as f:
    s = f.readline()
    k = 0
    while s != "":
        try:
            urllib.request.urlretrieve(s, "imgnet_" + str(k) + ".jpg")
            print(str(k))
        except:
            print("error: " + str(k))
        try:
            s = f.readline()
        except:
            print("error: " + str(k))
        k+= 1

