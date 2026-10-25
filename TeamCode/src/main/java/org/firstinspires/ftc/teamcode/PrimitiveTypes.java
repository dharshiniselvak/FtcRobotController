package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class PrimitiveTypes extends OpMode {
    @Override
    public void init() {
       int teamNumber = 2;
       double motorSpeed = 0.5;
       boolean touchSensorPresses = true;

       telemetry.addData("Team Number", teamNumber);
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("Touch Sensor Presses", touchSensorPresses);
    }

    @Override
    public void loop() {

    }
}
