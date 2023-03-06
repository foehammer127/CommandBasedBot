package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.commands.RobotCentricDriveCommand;
import org.firstinspires.ftc.teamcode.commands.TurnToAngleCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrainSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ImuSubsystem;

import java.util.List;

public class Robot extends com.arcrobotics.ftclib.command.Robot {
    private ImuSubsystem imu;
    private DriveTrainSubsystem driveTrainSubsystem;

    private GamepadEx driver;
    private GamepadEx manipulator;

    private List<LynxModule> hubs;

    public Robot(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2) {

        hubs = hardwareMap.getAll(LynxModule.class);
        for(LynxModule hub : hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        driver = new GamepadEx(gamepad1);
        manipulator = new GamepadEx(gamepad2);

        imu = new ImuSubsystem(hardwareMap);
        register(imu);

        driveTrainSubsystem = new DriveTrainSubsystem(hardwareMap);
        CommandScheduler.getInstance().setDefaultCommand(driveTrainSubsystem, new DefaultDriveCommand(imu, driveTrainSubsystem, driver));


    }

    @Override
    public void run() {
        clearCache();
        super.run();
    }


    private void bindButtons() {
        // Reset IMU when Back is Released (To Facilitate Recalibration of the IMU)
        driver.getGamepadButton(GamepadKeys.Button.BACK)
                .whenReleased(new InstantCommand(imu::reset, imu));

        // Slow Mode When Left Bumper Pressed
        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    driveTrainSubsystem.setMaxSpeed(0.2);
                }))
                .whenReleased(new InstantCommand(() -> {
                    driveTrainSubsystem.setDefaultSpeed();
                }));
        // Fast Mode When Right Bumper Pressed
        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    driveTrainSubsystem.setMaxSpeed(1);
                }))
                .whenReleased(new InstantCommand(() -> {
                    driveTrainSubsystem.setDefaultSpeed();
                }));

        driver.getGamepadButton(GamepadKeys.Button.Y)
                .whileHeld(new TurnToAngleCommand(driveTrainSubsystem, imu));

        driver.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new TurnToAngleCommand(driveTrainSubsystem, imu).setTarget(Math.toRadians(180)));

    }

    private void clearCache() {
        for(LynxModule hub : hubs) {
            hub.clearBulkCache();
        }
    }
}
