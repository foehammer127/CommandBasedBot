package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class DriveTrainSubsystem extends SubsystemBase {
    private Motor leftfront;
    private Motor leftback;
    private Motor rightfront;
    private Motor rightback;

    private MecanumDrive drive;

    public static double TURN_MULTIPLIER = 0.5;

    public DriveTrainSubsystem(HardwareMap hardwareMap) {
        leftfront = new Motor(hardwareMap, "leftfront", Motor.GoBILDA.RPM_312);
        leftback = new Motor(hardwareMap, "leftback", Motor.GoBILDA.RPM_312);
        rightback = new Motor(hardwareMap, "rightback", Motor.GoBILDA.RPM_312);
        rightfront = new Motor(hardwareMap, "rightfront", Motor.GoBILDA.RPM_312);

        drive = new MecanumDrive(leftfront, rightfront, leftback, rightback);


    }

    public void driveFieldCentric(GamepadEx inputs, double imuHeading) {
        drive.driveFieldCentric(
                inputs.getLeftX(),
                inputs.getLeftY(),
                inputs.getRightX() * TURN_MULTIPLIER,
                imuHeading,
                false
        );
    }

    public void driveRobotCentric(GamepadEx inputs) {
        drive.driveRobotCentric(
                inputs.getLeftX(),
                inputs.getLeftY(),
                inputs.getRightX() * TURN_MULTIPLIER,
                false
        );
    }

    public void driveFieldCentricFromInputs(double x, double y, double r, double heading) {
        drive.driveFieldCentric(x, y, r, heading, false);
    }

    public void setMaxSpeed(double speed) {
        drive.setMaxSpeed(speed);
    }

    public void setDefaultSpeed() {
        drive.setMaxSpeed(0.5);
    }
}
