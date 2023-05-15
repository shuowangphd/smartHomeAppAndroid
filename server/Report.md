# Project Report

The problem is to write a Python code to recognize gestures using handshape feature extraction from video data. 

Here is a detailed explanation of my approach and solution code:

First, the necessary libraries, namely "cv2" for image processing, "numpy" for numerical calculations, "os" for interacting with the operating system, "glob" for file manipulation, "handshape_feature_extractor" for extracting handshape features from images, and "frameextractor" for extracting frames from video data, are imported.

Next, the code creates an empty numpy array "feat" with dimensions [nf,27], where nf is the number of video files in the "traindata" directory. The purpose of this array is to store the handshape feature vectors extracted from the middle frame of each video. The code then initializes a path variable "frames_path" to store the extracted frames.

After that, the code creates an instance of the "HandShapeFeatureExtractor" class, which is defined in the "handshape_feature_extractor" module. This class contains methods for extracting handshape features from an image.

Then, for each video file in the "traindata" directory, the code extracts the middle frame using the "frameExtractor" function, which is defined in the "frameextractor" module. The extracted frame is then read as a grayscale image using the "cv2.imread" function, and its handshape feature vector is extracted using the "hfe.extract_feature" method of the "HandShapeFeatureExtractor" class. The feature vector is stored in the "feat" array.

After the training data has been processed, the code performs a similar operation for the test data. It creates an empty numpy array "feat2" with dimensions [nfnt,27], where nfnt is the number of video files in the "test" directory. The code also initializes a path variable "framet_path" to store the extracted frames.

For each video file in the "test" directory, the code extracts the middle frame, reads it as a grayscale image, and extracts its handshape feature vector. The feature vector is stored in the "feat2" array.

Once both the training and test data have been processed, the code calculates the similarity between the two sets of feature vectors using the dot product, which is equivalent to the cosine similarity between the two vectors. The resulting similarity matrix "mat" is a numpy array with dimensions [nf, nfnt]. The index of the maximum value in each column of this matrix is computed using the "np.argmax" function, which returns the index of the largest element in an array.

Finally, the code saves the resulting index values to a CSV file "results.csv" using the "np.savetxt" function. The delimiter is set to "," and the format of the data is set to integer values ("%d").

In summary, my code extracts handshape features from the middle frames of video files in the "traindata" and "test" directories, and calculates the similarity between the feature vectors using the dot product. The resulting index values of the most similar middle frames for each video in the "test" directory are saved to a CSV file.