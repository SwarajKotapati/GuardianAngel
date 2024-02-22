# Guardian Angel Application

**Project Overview**

The primary aim of this project is to create a user-friendly Android application that serves as a health monitoring tool. The application is designed with two key activities:

## Activity 1: Sign Measurement

### Heart Rate Sensing
- Utilizes the back camera of the mobile device.
- Users softly press their index finger on the camera lens.
- The application captures a 45-second video.
- The user's heart rate is derived from analyzing the variation in red coloration within the captured video.

### Respiratory Rate Sensing
- Utilizes the accelerometer sensor.
- Users lay down and place the smartphone on their chest.
- The accelerometer records data for 45 seconds.
- The respiratory rate is computed from the recorded accelerometer data.

## Activity 2: Symptoms Data Collection

- Allows users to select symptoms from a predefined list.
- Prompts users to rate each selected symptom on a scale of 0 to 5.
- Creates an entry with 12 values in the local database, marking symptoms not reported with a 0 rating.

**Health Monitoring Benefits**

This application is designed to empower users in monitoring their health status over time. Users can conveniently measure their heart and respiratory rate in the comfort of their homes. Additionally, the application facilitates access to past health data, enabling users to track and analyze changes in their vital signs and symptoms.

**Project Structure**

The project adheres to a standard structure:

- `src/`: Contains the source code for the Android application.
- `helper_code/`: Includes helper code for heart rate and respiratory rate sensing.
- `docs/`: Additional project documentation.
- `demo/`: A demo video showcasing heart rate collection, respiratory rate collection, symptom monitoring, and database creation.

**Local Database**

The application utilizes a local database (Room Database) , ensuring secure storage of user health data. The database schema includes tables for heart rate, respiratory rate, and symptoms, providing an organized repository for user information.

**Getting Started**

To run the application, follow these steps:

1. Download the project folder.
2. Extract the zip file.
3. Install the APK on an Android device or emulator.
4. Refer to the demo video for instructions on using the application.

**Contact**

For any inquiries or issues, please contact [Venkata Swaraj Kotapati] at [kotapatiswaraj06@gmail.com].
