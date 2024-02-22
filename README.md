Project Overview
The goal of this project is to build an Android application that monitors vital signs and symptoms, storing the data in a local database on the smartphone. The application comprises two main activities:

Activity 1: Sign Measurement
Heart Rate Sensing
Utilize the back camera with flash enabled.
Instruct the user to softly press their index finger on the camera lens.
Capture a 45-second video.
Derive the user's heart rate from the variation in red coloration in the captured video.
Helper code is provided for heart rate sensing.
Respiratory Rate Sensing
Utilize the accelerometer sensor.
Instruct the user to lay down and place the smartphone on their chest.
Record accelerometer data for 45 seconds.
Compute the respiratory rate from the accelerometer data.
Helper code is provided for respiratory rate sensing.
Activity 2: Symptoms Data Collection
Allow the user to select symptoms from a predefined list.
Prompt the user to rate each selected symptom on a scale of 0 to 5.
Create an entry with 12 values in the local database, marking symptoms not reported with a 0 rating.
After user input, generate a database table entry.
