package org.ts.robot.instruction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionFactoryTest {

    @Test
    void getKnownInstruction() {
        // Given
        String text = "L";

        // When
        Instruction instruction = InstructionFactory.getInstruction(text);

        // Then
        Assertions.assertNotNull(instruction);
    }

    @Test
    void getUnKnownInstruction() {
        // Given
        String text = "X";

        // When
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            InstructionFactory.getInstruction(text);
        });

        // Then
        Assertions.assertEquals("Invalid instruction: X", thrown.getMessage());
    }
}