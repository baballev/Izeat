"""
https://www.tensorflow.org/lite
https://www.youtube.com/watch?v=kFWKdLOxykE&feature=youtu.be&t=1019&fbclid=IwAR0XrStORn0UXwVy7D2Ea-nQtSY9K0YiWplV2r7eYBronkm3fb1MGEPJG9E
    https://github.com/tensorflow/tensorflow/tree/master/tensorflow/examples/android

"""
import tensorflow as tf
import os

root_path = "E:\\Programmation\\Python\\dataset-food"
weights_file = 'RN50&1Dense-300x300x3-13-02-2020 - (40epochs) acc=0.8414.h5'
os.chdir(root_path)

model = tf.keras.models.load_model(weights_file)

tf_converter = tf.lite.TFLiteConverter.from_keras_model(model)
tf_model = tf_converter.convert()
open("tf_lite_model_RestNet50_fine_tuned_izeat_23-02-2020.tflite", "wb").write(tf_model)
