package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;

@Config
public class TurnToAngleCommand extends CommandBase {
    PIDController controller;
    DriveTrainSubsystem driveTrainSubsystem;

    ImuSubsystem imu;

    public static double P = 1;
    public static double I = 0;
    public static double D = 0;

    public static double TOLERANCE = 0.01;

    public double target = 0;

    public TurnToAngleCommand(DriveTrainSubsystem driveTrainSubsystem, ImuSubsystem imu) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.imu = imu;
        controller = new PIDController(P, I, D);
        controller.setTolerance(TOLERANCE);

        addRequirements(driveTrainSubsystem);


    }

    public void execute() {
        double radians = imu.getRadians();
        double output = controller.calculate(radians, target);
        driveTrainSubsystem.driveFieldCentricFromInputs(0, 0, output, radians);
    }

    public TurnToAngleCommand setTarget(double target) {
        this.target = target;

        return this;
    }
}
