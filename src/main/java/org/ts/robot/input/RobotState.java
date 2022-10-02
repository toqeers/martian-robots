package org.ts.robot.input;

import lombok.Value;
import org.ts.robot.model.GridPoint;
import org.ts.robot.model.Orientation;

@Value
public class RobotState {
    GridPoint position;
    Orientation orientation;
}
