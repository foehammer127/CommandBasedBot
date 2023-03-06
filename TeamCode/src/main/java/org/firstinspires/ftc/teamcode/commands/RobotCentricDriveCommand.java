package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrainSubsystem;

public class RobotCentricDriveCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final GamepadEx input;


    public RobotCentricDriveCommand(DriveTrainSubsystem driveTrainSubsystem, GamepadEx input) {
        this.input = input;
        this.driveTrainSubsystem = driveTrainSubsystem;

        addRequirements(driveTrainSubsystem);
    }

    public void execute() {
        driveTrainSubsystem.driveRobotCentric(input);
    }

    public boolean isFinished() {
        return true;
    }
}
