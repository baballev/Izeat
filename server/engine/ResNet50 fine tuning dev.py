## Specifications
"""
Réalisé dans le cadre du projet PACT de Télécom Paris

Aliments retenus: Donut, Churros, Frites, Sushi, Steak, Club Sandwich, Gnocchi, Salade Grecque, Lasagne, Pizza
Dataset: 1000 images dans chaque catégorie, 100 catégories à disposition
Répartition:
    Test set -> 125 images de chaque catégorie
    Valid set -> 125 images de chaque catégorie
    Train set -> 750 images de chaque catégorie
"""
# TODO: Sauvegarder l'entrainement sur le disque dur ?
# TODO: Setup 2 branch forks de recoImage pour chacun et faire les commits
# TODO: Refactor tout le code, noms de variables appropriés, commenter, anglais...

## LIB
import numpy as np
import pandas as pd
import os
import shutil
import matplotlib.pyplot as plt
from keras.preprocessing.image import ImageDataGenerator, load_img, img_to_array, array_to_img
from six.moves import cPickle as pickle

## GLOBAL VARIABLES
np.random.seed(44)
labels = ["churros", "club_sandwich", "donuts", "french_fries", "gnocchi", "greek_salad", "lasagna", "pizza", "steak", "sushi"]
numClasses = len(labels)
IMG_SIZE = 300
IMG_DIM = (IMG_SIZE, IMG_SIZE)
pixelDepth = 255.0

## UTILS
def randomize(dataset, labels): # En place pour gagner de la RAM ou bien pickle avant ?
    permutation = np.random.permutation(labels.shape[0])
    dataset = dataset[permutation, :, :]
    labels = labels[permutation, :, :]


## DATA IMPORT
os.chdir("E:\\Programmation\\Python\\dataset-food")
with open("test.txt", 'r') as f:
    testFiles = []
    files = []
    for k in range(numClasses):
        testFiles.append([])
        files.append([])
    k = 1
    while (k <= 1250):
        testFiles[2*(k-1)//250].append("images\\" + f.readline()[:-1].replace("/", "\\") + ".jpg")
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

tmp = []
for k in range(numClasses):
    for i in range(n):
        tmp.append(img_to_array(load_img(files[k][i], target_size=IMG_DIM)))

valid_dataset = (np.array(tmp, dtype=np.float32) - (pixelDepth/2))/pixelDepth
valid_labels = np.copy(test_labels)

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
del files
del testFiles

print("Training dataset shape: " + str(train_dataset.shape) + " - Training labels shape: " + str(train_labels.shape))
print("Validation dataset shape: " + str(valid_dataset.shape) + " - Validation labels shape: " + str(valid_labels.shape))
print("Test dataset shape: " + str(test_dataset.shape) + " - Test labels shape: " + str(test_labels.shape))

# TODO: shuffle les arrays cf uda assign1
# TODO: Data Augmentation?









