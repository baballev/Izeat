import tensorflow as tf
import os

root_path = "E:\\Programmation\\Python\\dataset-food\\BEST_MODELS"
weights_file = 'RN50&3Dense-300x300x3_28-02-2020 - TEST_0.98.h5'
os.chdir(root_path)

model = tf.keras.models.load_model(weights_file)
tf_converter = tf.lite.TFLiteConverter.from_keras_model(model)
tf_converter.optimizations = [tf.lite.Optimize.OPTIMIZE_FOR_SIZE] # TODO: Test with Optimize for speed.
tf_model = tf_converter.convert()

open("RN50&3Dense_300x300x3_10epochs.tflite", "wb").write(tf_model)
