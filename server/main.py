# -*- coding: utf-8 -*-
"""
Created on Thu Jan 28 00:44:25 2021

@author: chakati
"""
import cv2
import numpy as np
import os
#import tensorflow as tf
## import the handfeature extractor class
import glob
import handshape_feature_extractor
import frameextractor
# =============================================================================
# Get the penultimate layer for trainig data
# =============================================================================
# your code goes here
# Extract the middle frame of each gesture video
fns = glob.glob(os.path.join("traindata", '*.mp4'))
nf = len(fns)
feat = np.zeros([nf,27])
frames_path = "frames"
hfe = handshape_feature_extractor.HandShapeFeatureExtractor.get_instance()
for i in range(nf):
    frameextractor.frameExtractor(fns[i], frames_path, i)
    framei = cv2.imread( os.path.join(frames_path, "%05d.png" % (i+1)), cv2.IMREAD_GRAYSCALE)
    feat[i] = hfe.extract_feature(framei)
# =============================================================================
# Get the penultimate layer for test data
# =============================================================================
# your code goes here 
# Extract the middle frame of each gesture video
fnt = glob.glob(os.path.join("test", '*.mp4'))
nfnt = len(fnt)
feat2 = np.zeros([nfnt,27])
framet_path = "framet"
for i in range(nfnt):
    frameextractor.frameExtractor(fnt[i], framet_path, i)
    framei = cv2.imread( os.path.join(framet_path, "%05d.png" % (i+1)), cv2.IMREAD_GRAYSCALE)
    feat2[i] = hfe.extract_feature(framei)
# =============================================================================
# Recognize the gesture (use cosine similarity for comparing the vectors)
# =============================================================================
mat = feat @ feat2.T
idx = np.argmax(mat,axis=0)//3
np.savetxt("results.csv",idx, delimiter =",", fmt='%d')