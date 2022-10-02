# Martian Robots
## How to build
To build the code locally please run following command.

`./gradlew clean build`

## How to run
After running the above command it should produce an executable jar file in `build/libs` folder.
You can run the jar file with following command from the project folder.

`java -jar build/libs/mars-robots-1.0-SNAPSHOT.jar src/main/resources/sample_input.txt`

The jar file need path to the input file as argument. Please provide your input file, 
alternatively you can use sample file from the `resources` folder. 
