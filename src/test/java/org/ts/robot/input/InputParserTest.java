package org.ts.robot.input;

import org.junit.jupiter.api.Test;
import org.ts.robot.instruction.Instruction;
import org.ts.robot.model.GridPoint;
import org.ts.robot.model.Orientation;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
    void parseValidInput() {
        // Given
        InputParser parser = new InputParser(new GridPoint(50, 50), 99);
        String textInput = "5 3\n" +
                "1 1 E\n" +
                "RFRFRFRF\n" +
                " \n" +
                "3 2 N\n" +
                "FRRFLLFFRRFLL\n" +
                " \n" +
                "0 3 W\n" +
                "LLFFFLFLFL";

        // When
        ApplicationInput applicationInput = parser.parse(new StringReader(textInput));

        // Then
        assertEquals(new GridPoint(5, 3), applicationInput.getMaxPoint());

        List<RobotInput> robotInputs = applicationInput.getRobotInputs();
        assertEquals(3, robotInputs.size());

        RobotInput robotInput = robotInputs.get(0);
        RobotState initialState = robotInput.getInitialState();
        List<Instruction> instructions = robotInput.getInstructions();

        assertEquals(new GridPoint(1, 1), initialState.getPosition());
        assertEquals(Orientation.EAST, initialState.getOrientation());
        assertEquals(8, instructions.size());
    }

    @Test
    void parseInValidGridBounds() {
        // Given
        InputParser parser = new InputParser(new GridPoint(5, 5), 99);
        String textInput = "6 6\n" +
                "1 1 E\n" +
                "RFRFRFRF\n";

        // When
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(new StringReader(textInput));
        });

        // Then
        assertTrue(thrown.getMessage().contains("Grid limit exceeded"));

    }

    @Test
    void parseInValidInstruction() {
        // Given
        InputParser parser = new InputParser(new GridPoint(5, 5), 99);
        String textInput = "5 5\n" +
                "1 1 E\n" +
                "RFRFRFRFX\n";

        // When
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(new StringReader(textInput));
        });

        // Then
        assertEquals("Invalid instruction: X", thrown.getMessage());

    }

    @Test
    void parseInValidOrientation() {
        // Given
        InputParser parser = new InputParser(new GridPoint(5, 5), 99);
        String textInput = "5 5\n" +
                "1 1 X\n" +
                "RFRFRFRF\n";

        // When
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(new StringReader(textInput));
        });

        // Then
        assertEquals("Invalid Orientation: X", thrown.getMessage());

    }
}