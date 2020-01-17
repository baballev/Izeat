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

# TODO: Setup 2 branch forks de recoImage pour chacun et faire les commits
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
np.random.seed(3)
labels = ["churros", "club_sandwich", "donuts", "french_fries", "gnocchi", "greek_salad", "lasagna", "pizza", "steak", "sushi"]
numClasses = len(labels)
IMG_SIZE = 256
IMG_DIM = (IMG_SIZE, IMG_SIZE)
pixelDepth = 255.0

## UTILS
def randomize(dataset, labels):
    idx = np.random.permutation(labels.shape[0])
    rd_dataset, rd_labels = dataset[idx], labels[idx]
    return rd_dataset, rd_labels

## DATA IMPORT
ENABLE_DATA_IMPORT = False

train_pickle_file = 'trainData.pickle'
valid_pickle_file = 'validData.pickle'
test_pickle_file = 'testData.pickle'

os.chdir("E:\\Programmation\\Python\\dataset-food")

## DATA LOADING + AUGMENTATION
    train_datagen = ImageDataGenerator(rescale=1./255, zoom_range=0.3, rotation_range=50, width_shift_range=0.2, height_shift_range=0.2, shear_range=0.2, horizontal_flip=True, fill_mode='nearest')
    valid_datagen = ImageDataGenerator(rescale=1./255)

    train_generator = train_datagen.flow_from_directory('E:/Programmation/Python/dataset-food/images', save_format='jpg', target_size=(256, 256), color_mode='rgb', batch_size=32)

## SETUP RESTNET50
loadNN = True # True to load an already trained NN, False to start a new training from scratch
needTrain = False # Make false for testing only
if loadNN:
    model = keras.models.load_model('ResNet50-test-17-01-2020.h5')
else:
    restnet = ResNet50(include_top=False, weights='imagenet', input_shape=(IMG_SIZE, IMG_SIZE, 3))
    output = restnet.layers[-1].output
    output = keras.layers.Flatten()(output)

    restnet = Model(restnet.input, output=output)

    for layer in restnet.layers: # Disable trainability on the layers already trained
        layer.trainable = False
    model = Sequential()
    model.add(restnet)
    model.add(Dense(numClasses, activation='softmax'))
    model.compile(loss='categorical_crossentropy', optimizer=keras.optimizers.Adadelta(), metrics=['accuracy'])
    model.summary() # Display trainable layers

if needTrain:
    history = model.fit_generator(train_generator, steps_per_epoch=100, epochs=20, validation_steps=50, verbose=2)
    model.save('ResNet50-test-17-01-2020.h5')
else: # predict tests
    x_test = np.zeros((5, IMG_SIZE, IMG_SIZE, 3), dtype=np.float32)
    x_test[0] = img_to_array(load_img("E:\\Programmation\\Python\\dataset-food\\test-images\\frites\\1.jpg", target_size=IMG_DIM))
    x_test[1] = img_to_array(load_img("E:\\Programmation\\Python\\dataset-food\\test-images\\frites\\2.jpg", target_size=IMG_DIM))
    x_test[2] = img_to_array(load_img("E:\\Programmation\\Python\\dataset-food\\test-images\\frites\\3.jpg", target_size=IMG_DIM))
    x_test[3] = img_to_array(load_img("E:\\Programmation\\Python\\dataset-food\\test-images\\frites\\4.jpg", target_size=IMG_DIM))
    x_test[4] = img_to_array(load_img("E:\\Programmation\\Python\\dataset-food\\test-images\\frites\\5.jpg", target_size=IMG_DIM))
    y_prob = history.model.predict_classes(x_test)
    print(y_prob)


