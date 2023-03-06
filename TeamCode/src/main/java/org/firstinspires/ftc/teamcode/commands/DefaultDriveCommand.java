package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrainSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultDriveCommand extends CommandBase {
    private final DoubleSupplier angleSupplier;
    private final DriveTrainSubsystem driveTrainSubsystem;

    private final GamepadEx driverpad;

    public DefaultDriveCommand(DoubleSupplier imuAngleSupplier, DriveTrainSubsystem driveTrainSubsystem, GamepadEx driverpad) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.angleSupplier = imuAngleSupplier;
        this.driverpad = driverpad;

        addRequirements(driveTrainSubsystem);
    }

    public void execute() {
        driveTrainSubsystem.driveFieldCentric(driverpad, angleSupplier.getAsDouble());
    }

    public boolean isFinished() {
        return false;
    }
}
