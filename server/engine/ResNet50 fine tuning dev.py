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
# TODO: Sauvegarder l'entrainement sur le disque dur ?
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
IMG_SIZE = 300
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
if ENABLE_DATA_IMPORT:
    with open("test.txt", 'r') as f:
        testFiles = []
        files = []
        for k in range(numClasses):
            testFiles.append([])
            files.append([])
        k = 1
        while (k <= 2500):
            if k%2 == 0:
                testFiles[(k-1)//250].append("images\\" + f.readline()[:-1].replace("/", "\\") + ".jpg")
            else:
                f.readline()
            k+=1
        for k in range(numClasses):
            tmp = []
            for file in os.listdir("images\\" + labels[k] + "\\"):
                tmp.append("images\\" + labels[k] + "\\" + file)
            files[k] = list(set(tmp) - set(testFiles[k]))

        # files = les fichiers différents de ceux du test set

    # TODO: Code hyper sale à retravailler pour une généralisation
    churros_test, club_sandwich_test, donuts_test, french_fries_test, gnocchi_test, greek_salad_test, lasagna_test, pizza_test, steak_test, sushi_test  = [], [], [], [], [], [], [], [], [], []

    for i in range(len(testFiles[0])):
        churros_test.append(img_to_array(load_img(testFiles[0][i], target_size=IMG_DIM)))
        club_sandwich_test.append(img_to_array(load_img(testFiles[1][i], target_size=IMG_DIM)))
        donuts_test.append(img_to_array(load_img(testFiles[2][i], target_size=IMG_DIM)))
        french_fries_test.append(img_to_array(load_img(testFiles[3][i], target_size=IMG_DIM)))
        gnocchi_test.append(img_to_array(load_img(testFiles[4][i], target_size=IMG_DIM)))
        greek_salad_test.append(img_to_array(load_img(testFiles[5][i], target_size=IMG_DIM)))
        lasagna_test.append(img_to_array(load_img(testFiles[6][i], target_size=IMG_DIM)))
        pizza_test.append(img_to_array(load_img(testFiles[7][i], target_size=IMG_DIM)))
        steak_test.append(img_to_array(load_img(testFiles[8][i], target_size=IMG_DIM)))
        sushi_test.append(img_to_array(load_img(testFiles[9][i], target_size=IMG_DIM)))

    churros_test = np.array(churros_test, dtype=np.float32)
    club_sandwich_test = np.array(club_sandwich_test, dtype=np.float32)
    donuts_test = np.array(donuts_test, dtype=np.float32)
    french_fries_test = np.array(french_fries_test, dtype=np.float32)
    gnocchi_test = np.array(gnocchi_test, dtype=np.float32)
    greek_salad_test = np.array(greek_salad_test, dtype=np.float32)
    lasagna_test = np.array(lasagna_test, dtype=np.float32)
    pizza_test = np.array(pizza_test, dtype=np.float32)
    steak_test = np.array(steak_test, dtype=np.float32)
    sushi_test = np.array(sushi_test, dtype=np.float32)

    test_dataset = np.concatenate((churros_test, club_sandwich_test, donuts_test, french_fries_test, gnocchi_test, greek_salad_test, lasagna_test, pizza_test, steak_test, sushi_test), axis=0)

    del club_sandwich_test, donuts_test, french_fries_test, gnocchi_test, greek_salad_test, lasagna_test, pizza_test, steak_test, sushi_test

    n = churros_test.shape[0] # length of test set and valid set
    test_labels = np.zeros(n, dtype=np.int32)
    for k in range(1, numClasses):
        test_labels = np.concatenate((test_labels, k*np.ones(n , dtype=np.int32)))
    valid_labels = np.copy(test_labels)
    # Pickle test data
    test_dataset, test_labels = randomize(test_dataset, test_labels)
    print("Test dataset shape: " + str(test_dataset.shape) + " - Test labels shape: " + str(test_labels.shape))
    try:
        f = open(test_pickle_file, 'wb')
        save = {
        'test_dataset': test_dataset,
        'test_labels': test_labels,
        }
        pickle.dump(save, f, pickle.HIGHEST_PROTOCOL)
        f.close()
    except Exception as e:
        print('Unable to save data to', test_pickle_file, ':', e)
        raise e
    del test_dataset, test_labels

    tmp = []
    for k in range(numClasses):
        for i in range(n):
            tmp.append(img_to_array(load_img(files[k][i], target_size=IMG_DIM)))

    valid_dataset = (np.array(tmp, dtype=np.float32) - (pixelDepth/2))/pixelDepth
    # Pickle validation data
    valid_dataset, valid_labels = randomize(valid_dataset, valid_labels)
    print("Validation dataset shape: " + str(valid_dataset.shape) + " - Validation labels shape: " + str(valid_labels.shape))
    try:
        f = open(valid_pickle_file, 'wb')
        save = {
        'valid_dataset': valid_dataset,
        'valid_labels': valid_labels,
        }
        pickle.dump(save, f, pickle.HIGHEST_PROTOCOL)
        f.close()
    except Exception as e:
        print('Unable to save data to', valid_pickle_file, ':', e)
        raise e
    del valid_dataset, valid_labels

    m = len(files[0][n:])
    tmp = []
    train_labels = np.zeros(m, dtype=np.int32)
    for k in range(numClasses):
        if k!=0:
            truc = k*np.ones(m, dtype=np.int32)
            train_labels = np.concatenate((train_labels, truc), axis=0)
        for i in range(m):
            tmp.append(img_to_array(load_img(files[k][i+n], target_size=IMG_DIM)))

    train_dataset = (np.array(tmp, dtype=np.float32) - (pixelDepth/2))/pixelDepth
    del tmp
    # Pickle train data
    train_dataset, train_labels = randomize(train_dataset, train_labels)
    print("Training dataset shape: " + str(train_dataset.shape) + " - Training labels shape: " + str(train_labels.shape))
    try:
        f = open(train_pickle_file, 'wb')
        save = {
        'train_dataset': train_dataset,
        'train_labels': train_labels,
        }
        pickle.dump(save, f, pickle.HIGHEST_PROTOCOL)
        f.close()
    except Exception as e:
        print('Unable to save data to', train_pickle_file, ':', e)
        raise e
    del train_dataset, train_labels

    del files
    del testFiles

    # TODO: Data Augmentation?

##
else:
    with open(test_pickle_file, 'rb') as f:
        neat = pickle.load(f)
    X = neat['test_dataset']
    Y = neat['test_labels']


    restnet = ResNet50(include_top=False, weights='imagenet', input_shape =(IMG_SIZE, IMG_SIZE, 3))
    output = restnet.layers[-1].output
    output = keras.layers.Flatten()(output)

    restnet = Model(restnet.input, output=output)

    for layer in restnet.layers: # Disable trainability on the layers already trained
        layer.trainable = False
    #restnet.summary() # See all the layers

    model = Sequential()
    model.add(restnet)
    model.add(Dense(512, activation='relu', input_dim=(IMG_SIZE,IMG_SIZE,3))) # Fully connected layer
    model.add(Dropout(0.3))
    model.add(Dense(512, activation='relu'))
    model.add(Dropout(0.3))
    model.add(Dense(10, activation='sigmoid'))
    model.compile(loss='categorical_crossentropy', optimizer=keras.optimizers.RMSprop(learning_rate=2e-5), metrics=['accuracy'])
    model.summary() # Display trainable layers





