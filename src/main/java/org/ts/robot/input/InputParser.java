package org.ts.robot.input;

import lombok.Value;
import org.ts.robot.instruction.Instruction;
import org.ts.robot.instruction.InstructionFactory;
import org.ts.robot.model.GridPoint;
import org.ts.robot.model.Orientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
public class InputParser {

    private static final String SPACE = " ";

    GridPoint gridLimit;
    int instructionLimit;


    public ApplicationInput parse(Reader inputReader) {
        try (BufferedReader reader = new BufferedReader(inputReader)) {
            // First Line
            GridPoint maxPoint = parseMaxPoint(reader.readLine());
            validateGridLimit(maxPoint);

            // Remaining lines
            List<String> remainingLines = reader.lines().collect(Collectors.toList());
            List<RobotInput> robotInputs = parseRobotInputs(remainingLines);

            return new ApplicationInput(maxPoint, robotInputs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<RobotInput> parseRobotInputs(List<String> lines) {
        List<RobotInput> robotInputs = new ArrayList<>();
        String robotState = null;
        String instructions = null;
        for (String currentLine : lines) {
            if (currentLine.trim().length() == 0) {
                // skip empty lines
                continue;
            }

            if (robotState == null) {
                robotState = currentLine;
            } else {
                instructions = currentLine;
                robotInputs.add(new RobotInput(parseRobotState(robotState), parseInstructions(instructions)));

                robotState = null;
                instructions = null;
            }
        }
        return robotInputs;
    }

    private List<Instruction> parseInstructions(String instructions) {
        validateInstructions(instructions);
        return Stream.of(instructions.split(""))
                .map(InstructionFactory::getInstruction)
                .collect(Collectors.toList());
    }

    private RobotState parseRobotState(String robotState) {
        String[] tokens = robotState.split(SPACE);

        if (tokens.length == 3) {
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            Orientation orientation = Orientation.toEnum(tokens[2]);

            return new RobotState(new GridPoint(x, y), orientation);
        } else {
            throw new IllegalArgumentException("Invalid Robot State: " + robotState);
        }
    }

    private GridPoint parseMaxPoint(String gridBounds) {
        String[] tokens = gridBounds.split(SPACE);
        if (tokens.length == 2) {
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);

            return new GridPoint(x, y);
        } else {
            throw new IllegalArgumentException("Invalid Grid Bounds: " + gridBounds);
        }
    }

    private void validateGridLimit(GridPoint maxPoint) {
        if (maxPoint.getX() > gridLimit.getX() || maxPoint.getY() > gridLimit.getY()) {
            throw new IllegalArgumentException("Grid limit exceeded. Given: " + maxPoint + " Max Allowed: " + gridLimit);
        }
    }

    private void validateInstructions(String instructions) {
        if (instructions.length() > instructionLimit) {
            throw new IllegalArgumentException("Instruction limit exceeded. Given: " + instructions.length() + " Max Allowed: " + instructionLimit);
        }
    }
}
