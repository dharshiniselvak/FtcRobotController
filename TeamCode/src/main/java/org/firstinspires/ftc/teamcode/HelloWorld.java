package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name="Hello World")
public class HelloWorld extends OpMode{
    @Override
    public void init() {
        telemetry.addData("Team Name", "Automation Avengers");
    }

    @Override
    public void loop()
    {

    }
}
