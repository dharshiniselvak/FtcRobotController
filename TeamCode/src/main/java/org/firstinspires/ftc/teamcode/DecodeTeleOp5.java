package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * TeleOp Mode for FTC Decode Game
 *
 * This is the manual control mode where drivers control the robot during the match.
 *
 * CONTROLS:
 * ========
 * Gamepad1 (Driver):
 * - Left Stick Y:  Controls the power for the left-side motors (front and back).
 * - Right Stick Y: Controls the power for the right-side motors (front and back).
 *
 * Gamepad2 (Operator):
 * - Right Trigger: Run shooter motors.
 * - A Button: Run feeder, intake, and servo forward.
 * - B Button: Run feeder, intake, and servo in reverse.
 */
@TeleOp(name="Decode TeleOp 5", group="Decode")
public class DecodeTeleOp5 extends OpMode {

    // Declare all hardware
    private DcMotor front_left_motor;
    private DcMotor back_left_motor;
    private DcMotor front_right_motor;
    private DcMotor back_right_motor;
    private DcMotor feederMotor;
    private DcMotor intakeMotor;
    private DcMotor TopshooterMotor;
    private DcMotor BottomshooterMotor; // Re-enabled this motor
    private CRServo servoMotor;

    // Declare power variables for telemetry
    private double feederPower;
    private double intakePower;
    private double servoPower;
    private double TopshooterPower;
    private double BottomshooterPower;

    // FIX 2: Added definitions for missing speed constants
    private static final double FEEDER_SPEED = 0.8;
    private static final double INTAKE_SPEED = 0.8;
    private static final double SERVO_SPEED = 1.0;
    private static final double TOP_SHOOTER_SPEED = 1.0;
    private static final double BOTTOM_SHOOTER_SPEED = 1.0;

    // This multiplier can be used to globally reduce the robot's top speed.
    private static final double DRIVE_SPEED_MULTIPLIER = 1.0; // 1.0 = 100% speed

    @Override
    public void init() {
        // Initialize all hardware with telemetry for debugging
        telemetry.addData("Status", "Initializing motors...");
        telemetry.update();

        try {
            front_left_motor = hardwareMap.get(DcMotor.class, "front_left_motor");
            telemetry.addData("front_left_motor", "OK");
        } catch (Exception e) {
            telemetry.addData("front_left_motor", "NOT FOUND");
        }

        try {
            back_left_motor = hardwareMap.get(DcMotor.class, "back_left_motor");
            telemetry.addData("back_left_motor", "OK");
        } catch (Exception e) {
            telemetry.addData("back_left_motor", "NOT FOUND");
        }

        try {
            front_right_motor = hardwareMap.get(DcMotor.class, "front_right_motor");
            telemetry.addData("front_right_motor", "OK");
        } catch (Exception e) {
            telemetry.addData("front_right_motor", "NOT FOUND");
        }

        try {
            back_right_motor = hardwareMap.get(DcMotor.class, "back_right_motor");
            telemetry.addData("back_right_motor", "OK");
        } catch (Exception e) {
            telemetry.addData("back_right_motor", "NOT FOUND");
        }

        try {
            feederMotor = hardwareMap.get(DcMotor.class, "feederMotor");
            telemetry.addData("feederMotor", "OK");
        } catch (Exception e) {
            telemetry.addData("feederMotor", "NOT FOUND");
        }

        try {
            intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
            telemetry.addData("intakeMotor", "OK");
        } catch (Exception e) {
            telemetry.addData("intakeMotor", "NOT FOUND");
        }

        try {
            TopshooterMotor = hardwareMap.get(DcMotor.class, "TopshooterMotor");
            telemetry.addData("TopshooterMotor", "OK");
        } catch (Exception e) {
            telemetry.addData("TopshooterMotor", "NOT FOUND");
        }

        try {
            BottomshooterMotor = hardwareMap.get(DcMotor.class, "BottomshooterMotor");
            telemetry.addData("BottomshooterMotor", "OK");
        } catch (Exception e) {
            telemetry.addData("BottomshooterMotor", "NOT FOUND");
        }

        try {
            servoMotor = hardwareMap.get(CRServo.class, "servoMotor");
            telemetry.addData("servoMotor", "OK");
        } catch (Exception e) {
            telemetry.addData("servoMotor", "NOT FOUND");
        }

        telemetry.update();

        // Set drivetrain motor directions (with null checks).
        if (front_left_motor != null) front_left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        if (back_left_motor != null) back_left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        if (front_right_motor != null) front_right_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        if (back_right_motor != null) back_right_motor.setDirection(DcMotorSimple.Direction.FORWARD);

        // Set mechanism motor directions (with null checks).
        if (feederMotor != null) feederMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        if (intakeMotor != null) intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        if (TopshooterMotor != null) TopshooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        if (BottomshooterMotor != null) BottomshooterMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        if (servoMotor != null) servoMotor.setDirection(CRServo.Direction.FORWARD);

        // Set motors to BRAKE when power is zero (with null checks).
        if (front_left_motor != null) front_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (back_left_motor != null) back_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (front_right_motor != null) front_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (back_right_motor != null) back_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (intakeMotor != null) intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (feederMotor != null) feederMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (TopshooterMotor != null) TopshooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (BottomshooterMotor != null) BottomshooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // ==============================
        // --- GAMEPAD 1: DRIVETRAIN ---
        // ==============================
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickY = -gamepad1.right_stick_y;

        double leftPower = leftStickY * DRIVE_SPEED_MULTIPLIER;
        double rightPower = rightStickY * DRIVE_SPEED_MULTIPLIER;

        if (front_left_motor != null) front_left_motor.setPower(leftPower);
        if (back_left_motor != null) back_left_motor.setPower(leftPower);
        if (front_right_motor != null) front_right_motor.setPower(rightPower);
        if (back_right_motor != null) back_right_motor.setPower(rightPower);

        // =================================
        // --- GAMEPAD 2: MECHANISMS ---
        // =================================

        // --- Shooter Control (Right Trigger) ---
        TopshooterPower = gamepad2.right_trigger * TOP_SHOOTER_SPEED;
        BottomshooterPower = gamepad2.right_trigger * BOTTOM_SHOOTER_SPEED;
        if (TopshooterMotor != null) TopshooterMotor.setPower(TopshooterPower);
        if (BottomshooterMotor != null) BottomshooterMotor.setPower(BottomshooterPower);

        // --- Feeder, Intake, and Servo Control (A and B Buttons, or Right Trigger) ---
        // Also feed when shooter is running (right trigger pressed)
        boolean shooterActive = gamepad2.right_trigger > 0.1;
        if (gamepad2.a || shooterActive) {
            feederPower = FEEDER_SPEED;
            intakePower = INTAKE_SPEED;
            servoPower = SERVO_SPEED;
        } else if (gamepad2.b) {
            feederPower = -FEEDER_SPEED;
            intakePower = -INTAKE_SPEED;
            servoPower = -SERVO_SPEED;
        } else {
            // FIX 4: This block is crucial to stop the motors when no button is pressed.
            feederPower = 0;
            intakePower = 0;
            servoPower = 0;
        }

        intakeMotor.setPower(intakePower);
        feederMotor.setPower(feederPower);
        servoMotor.setPower(servoPower);

        // ====================
        // --- TELEMETRY ---
        // ====================
        telemetry.addData("Status", "Running");

        // Gamepad connection debug
        telemetry.addData("---", "GAMEPAD STATUS");
        telemetry.addData("Gamepad1 Connected", gamepad1.atRest() ? "Yes (at rest)" : "Yes (active)");
        telemetry.addData("Gamepad2 Connected", gamepad2.atRest() ? "Yes (at rest)" : "Yes (active)");

        // Drivetrain debug
        telemetry.addData("---", "DRIVETRAIN (GP1)");
        telemetry.addData("Left Stick Y Raw", "%.2f", gamepad1.left_stick_y);
        telemetry.addData("Right Stick Y Raw", "%.2f", gamepad1.right_stick_y);
        telemetry.addData("Drive Power", "L: %.2f | R: %.2f", leftPower, rightPower);

        // Shooter debug
        telemetry.addData("---", "SHOOTER (GP2 Right Trigger)");
        telemetry.addData("Right Trigger Raw", "%.2f", gamepad2.right_trigger);
        telemetry.addData("Top Shooter Power", "%.2f", TopshooterPower);
        telemetry.addData("Bottom Shooter Power", "%.2f", BottomshooterPower);
        telemetry.addData("Shooter Active", shooterActive ? "YES" : "NO");

        // Feeder/Intake/Servo debug
        telemetry.addData("---", "FEEDER/INTAKE/SERVO (GP2)");
        telemetry.addData("A Button", gamepad2.a ? "PRESSED" : "-");
        telemetry.addData("B Button", gamepad2.b ? "PRESSED" : "-");
        telemetry.addData("Feeder Power", "%.2f", feederPower);
        telemetry.addData("Intake Power", "%.2f", intakePower);
        telemetry.addData("Servo Power", "%.2f", servoPower);

        telemetry.update();
    }

    @Override
    public void stop() {
        // Make sure all motors are stopped when the OpMode ends.
        front_left_motor.setPower(0);
        back_left_motor.setPower(0);
        front_right_motor.setPower(0);
        back_right_motor.setPower(0);
        TopshooterMotor.setPower(0);
        BottomshooterMotor.setPower(0); // Re-enabled stop command
        intakeMotor.setPower(0);
        feederMotor.setPower(0);
        servoMotor.setPower(0);

        telemetry.addData("Status", "Stopped");
        telemetry.update();
    }
}
