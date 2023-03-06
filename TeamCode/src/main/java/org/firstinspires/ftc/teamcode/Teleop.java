package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Teleop extends OpMode {
    Robot robot;


    @Override
    public void init() {
        robot = new Robot(hardwareMap, gamepad1, gamepad2);
    }

    @Override
    public void loop() {
        robot.run();
    }

    @Override
    public void stop() {
        robot.reset();
    }
}
