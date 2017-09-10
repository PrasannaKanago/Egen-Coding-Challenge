# Egen-Coding-Challenge
Egen-Coding-Challenge

Pre-Requisite : Create the database coding_challenge in mongoDb in local.

Steps to execute this project
1. Build the project using maven.
2. Run EgenCodingChallengeApplication class as java application 
3. This will install the jar on the embedded tomcat server.
4. Open command prompt, and Go to the path where sensor-emulator-0.0.1-SNAPSHOT.jar is placed. (sensor-emulator-0.0.1-SNAPSHOT.jar was provided as part of the problem statement). 
5. Now execute the below command

java -jar -Dbase.value=150 -Dapi.url=http://localhost:8080/metric/baseweight/150 sensor-emulator-0.0.1-SNAPSHOT.jar

6. Now the records inserted can be read by accessing the url http://localhost:8080/metrics
7. Alerts inserted can be read by accessing the url http://localhost:8080/alerts


For the time range note down couple of timestamp values getting printed during the step 5 in the command prompt.
Now metrics and alerts can be accessed for a given time range by accessing below urls

Metrics : http://localhost:8080/metrics//timerange/{startTimeStamp}/{endTimeStamp}
Alerts  : http://localhost:8080/alerts//timerange/{startTimeStamp}/{endTimeStamp}
