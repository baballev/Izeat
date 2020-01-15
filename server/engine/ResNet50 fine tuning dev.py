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
# TODO: Sauvegarder l'entrainement sur le disque dur
# TODO: Setup branch commune + 2 forks pour le ML et faire les commits

##
import glob
import numpy as np
import pandas as pd
import os
import shutil
import matplotlib.pyplot as plt
from keras.preprocessing.image import ImageDataGenerator, load_img, img_to_array, array_to_img


np.random.seed(44)
## Varibles globales

folder = "\\images"
labels = ["churros", "club_sandwich", "donuts", "french_fries", "gnocchi", "greek_salad", "lasagna", "pizza", "steak", "sushi"]
numClasses = len(labels)
IMG_SIZE = 300
IMG_DIM = (IMG_SIZE, IMG_SIZE)
pixelDepth = 255.0

## Utils

def pickleData(dataFolder, minNum, force=False):
    datasetNames = []
    for folder in dataFolders:
        if folder in labels:
            filename = folder + '.pickle'
            datasetNames.append(filename)
            if os.path.exists(filename) and not force:
                print('%s already pickled, Skipping...' % filename)
            else:
                print('Pickling %s.' % filename)
                dataset = loadFood(folder, minNum)
                try:
                    with open(filename, 'wb') as f:
                        pickle.dump(dataset, f, pickle.HIGHEST_PROTOCOL)
                except Exception as e:
                    print('Unable to save data to', set_filename, ':', e)

    return dataset_names

## DATA
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
        for file in os.listdir("images\\" + labels[k] + "\\")
            tmp.append("images\\" + labels[k] + "\\" + file)
        files[k] = list(set(tmp) - set(testFiles[k]))
    # files = les fichiers différents de ceux du test set

# TODO: Commenter, mettre en anglais
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
# TODO if needed: appeler le garbage collector ici

test_dataset = np.concatenate((churros_test, club_sandwich_test, donuts_test, french_fries_test, gnocchi_test, greek_salad_test, lasagna_test, pizza_test, steak_test, sushi_test) axis=0)


test_labels = np.array(shape=(), dtype=np.int32)
for k in range(1, numClasses):
    test_labels = np.array(range(test_dataset.shape[0]), dtype=np.int32)

# TODO: Train set et valid set
# TODO: shuffle les arrays
# TODO: normalize les arrays

##










