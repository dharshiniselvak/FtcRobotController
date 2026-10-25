package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
@TeleOp(name ="gamepad1")

public class gamepad1 extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        double rightstickY = -gamepad1.right_stick_y / 2.0;
            telemetry.addData("right stick x", gamepad1.right_stick_x);
            telemetry.addData("right stick y ", rightstickY);
        double leftstickY = -gamepad1.left_stick_y / 2.0;
        telemetry.addData("left stick x", gamepad1.left_stick_x);
        telemetry.addData("left stick y", leftstickY);
        telemetry.addData("A", gamepad1.a);
        telemetry.addData("B", gamepad1.b);
        telemetry.addData("X", gamepad1.x);
        telemetry.addData("Y", gamepad1.y);
        telemetry.addData("D Pad Down", gamepad1. dpad_down);
        telemetry.addData("D Pad Up", gamepad1.dpad_up);
        telemetry.addData("D Pad Left", gamepad1.dpad_left);
        telemetry.addData("D Pad Right", gamepad1.dpad_right);
        telemetry.addData("R2", gamepad1.right_trigger_pressed);
        telemetry.addData("L2", gamepad1.left_trigger_pressed);
        telemetry.addData("R1", gamepad1.right_bumper);
        telemetry.addData("L1", gamepad1.left_bumper);








    }

}