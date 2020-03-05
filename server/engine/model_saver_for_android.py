"""
https://www.tensorflow.org/lite
https://www.youtube.com/watch?v=kFWKdLOxykE&feature=youtu.be&t=1019&fbclid=IwAR0XrStORn0UXwVy7D2Ea-nQtSY9K0YiWplV2r7eYBronkm3fb1MGEPJG9E
https://github.com/tensorflow/tensorflow/tree/master/tensorflow/examples/android
"""

import tensorflow as tf
import os

root_path = "E:\\Programmation\\Python\\dataset-food\\BEST_MODELS"
weights_file = 'RN50&3Dense-300x300x3_28-02-2020 - TEST_0.98.h5'
os.chdir(root_path)

model = tf.keras.models.load_model(weights_file)

tf_converter = tf.lite.TFLiteConverter.from_keras_model(model)
tf_converter.optimizations = [tf.lite.Optimize.OPTIMIZE_FOR_SIZE]
tf_model = tf_converter.convert()
open("RN50&3Dense_300x300x3_10epochs.tflite", "wb").write(tf_model)
