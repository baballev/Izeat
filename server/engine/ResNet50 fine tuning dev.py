## Specifications
"""
Réalisé dans le cadre du projet PACT de Télécom Paris

Aliments retenus: Donut, Churros, Frites, Sushi, Steak, Club Sandwich, Gnocchi, Salade Grecque, Lasagne, Pizza
Dataset: 1000 images dans chaque catégorie, 100 catégories à disposition
Répartition:
    Test set -> 125 images de chaque catégorie
    Valid set -> 125 images de chaque catégorie
    Train set -> 750 images de chaque catégorie

Taille des données: environ 10GB en ready-to-use
"""
# TODO: Refactor tout le code, noms de variables appropriés, commenter, anglais...

## LIB
import numpy as np
import pandas as pd
import os
import shutil
import keras
import matplotlib.pyplot as plt
from keras.preprocessing.image import ImageDataGenerator, load_img, img_to_array, array_to_img
from six.moves import cPickle as pickle
from keras.applications.resnet50 import ResNet50
from keras.models import Model, Sequential
from keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Dropout, InputLayer

## GLOBAL VARIABLES
np.random.seed(9)
labels = ["churros", "club_sandwich", "donuts", "french_fries", "gnocchi", "greek_salad", "lasagna", "pizza", "steak", "sushi"]
numClasses = len(labels)
img_size = 128
img_dim = (img_size, img_size)
depth = 3 # 3 = RGB, 1 = Greyscale
pixelDepth = 255.0
nb_of_epochs = 10 # Number of times the entire dataset will be gone through during training
root_path = "E:\\Programmation\\Python\\dataset-food"
weights_file = 'ResNet50-test-13-02-2020.h5'

## UTILS
def randomize(dataset, labels):
    idx = np.random.permutation(labels.shape[0])
    rd_dataset, rd_labels = dataset[idx], labels[idx]
    return rd_dataset, rd_labels

## Quick menu
loadNN = (input("Load NN? (y/n): ") == "y") # True to load an already trained NN, False to start a new training from scratch
needTrain = (input("Train NN ? (y/n, n for tests only): ") == "y") # Make false for testing only
s = input("Root directory (default : " + root_path + "): ")
if s != "": root_path = s
os.chdir(root_path)
if loadNN:
    s = input("Weights file location to load (.h5)(default: " + weights_file + "): ")
    if s != "": weights_file = y
else:
    s = input("Weights file location to save (.h5)(default: " + weights_file + "): ")
    if s != "": weights_file = y

if needTrain:
    s = input("Number of epochs to train (default = " + str(nb_of_epochs) + "): ")
    if s != "": nb_of_epochs = int(s)
    s = input("Image size (default: " + str(img_size) + "->" + str(img_dim) + "): ")
    if s != "": img_size = int(s)
    s = input("Color ? (y = color/ n = grayscale, default = color): ")
    if s == "n": depth = 1

## DATA LOADING + AUGMENTATION
train_datagen = ImageDataGenerator(rescale=1./255, zoom_range=0.3, rotation_range=50, width_shift_range=0.2, height_shift_range=0.2, shear_range=0.2, horizontal_flip=True, fill_mode='nearest')
valid_datagen = ImageDataGenerator(rescale=1./255)

train_generator = train_datagen.flow_from_directory(root_path + '/images', save_format='jpg', target_size=img_dim, color_mode='rgb', batch_size=32)

## SETUP RESTNET5

if loadNN:
    model = keras.models.load_model(weights_file)
else:
    restnet = ResNet50(include_top=False, weights='imagenet', input_shape=(img_size, img_size, depth))
    output = restnet.layers[-1].output
    output = keras.layers.Flatten()(output)

    restnet = Model(restnet.input, output=output)

    for layer in restnet.layers: # Disable trainability on the layers already trained
        layer.trainable = False
    model = Sequential()
    model.add(restnet)
    model.add(Dense(10000, activation='softmax'))
    model.add(Dense(1000, activation='softmax'))
    model.add(Dense(numClasses, activation='softmax'))
    model.compile(loss='categorical_crossentropy', optimizer=keras.optimizers.SGD(0.01), metrics=['accuracy'])
    model.summary() # Display trainable layers

if needTrain:
    history = model.fit_generator(train_generator, steps_per_epoch=100, epochs=nb_of_epochs, validation_steps=50, verbose=1)
    model.save(weights_file)
else: # predict tests
    x_test = np.zeros((25, img_size, img_size, depth), dtype=np.float32)
    for i in range(25):
        x_test[i] = img_to_array(load_img("E:\\Programmation\\Python\\dataset-food\\test-images\\sushi\\" + str(i+1) + ".jpg", target_size=img_dim))
    y_prob = model.predict_classes(x_test)
    print(y_prob)


