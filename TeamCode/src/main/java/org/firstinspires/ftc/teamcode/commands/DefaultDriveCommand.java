package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;

public class DefaultDriveCommand extends CommandBase {
    private final ImuSubsystem imuSubsystem;
    private final DriveTrainSubsystem driveTrainSubsystem;

    private final GamepadEx driverpad;

    public DefaultDriveCommand(ImuSubsystem imuSubsystem, DriveTrainSubsystem driveTrainSubsystem, GamepadEx driverpad) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.imuSubsystem = imuSubsystem;
        this.driverpad = driverpad;

        addRequirements(driveTrainSubsystem);
    }

    public void execute() {
        driveTrainSubsystem.driveFieldCentric(driverpad, imuSubsystem.getDegrees());
    }

    public boolean isFinished() {
        return false;
    }
}
