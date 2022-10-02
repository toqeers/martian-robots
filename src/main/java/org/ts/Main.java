package org.ts;

import org.ts.robot.input.ApplicationInput;
import org.ts.robot.input.InputParser;
import org.ts.robot.input.RobotInput;
import org.ts.robot.input.RobotState;
import org.ts.robot.instruction.Instruction;
import org.ts.robot.model.Grid;
import org.ts.robot.model.GridPoint;
import org.ts.robot.model.Robot;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class Main {

    public static final String SAMPLE_INPUT_TXT = "sample_input.txt";

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new RuntimeException("Missing required argument. Please provide full file path as argument");
        }

        InputParser inputParser = new InputParser(new GridPoint(50, 50), 99);

        try (Reader inputReader = new FileReader(args[0])) {
            ApplicationInput applicationInput = inputParser.parse(inputReader);

            Grid grid = new Grid(applicationInput.getMaxPoint());
            List<RobotInput> robotInputs = applicationInput.getRobotInputs();

            robotInputs.stream()
                    .map(input -> instructRobots(grid, input))
                    .forEach(System.out::println);
        }
    }

    private static Robot instructRobots(Grid grid, RobotInput input) {
        RobotState initialState = input.getInitialState();
        Robot robot = new Robot(initialState.getPosition(), initialState.getOrientation(), grid, false);

        for (Instruction instruction : input.getInstructions()) {
            if (instruction.apply(robot).isLost()) {
                break;
            }
        }
        return robot;
    }
}