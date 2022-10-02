package org.ts.robot.input;

import lombok.Value;
import org.ts.robot.model.GridPoint;

import java.util.List;

@Value
public class ApplicationInput {
    GridPoint maxPoint;
    List<RobotInput> robotInputs;
}
