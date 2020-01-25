package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="SkyStone", group="Pushbot")
@Disabled
public class SkyStoneLoad extends LinearOpMode {
    /* Declare OpMode members. */
    SkyStoneHardware robot = new SkyStoneHardware();
    private boolean hiFound;

    @Override
    public void runOpMode() {

        double gamepad1LeftY;
        double gamepad1LeftX;
        double gamepad1RightX;
        double gamepad1RightY;

        double SPEED_MODIFIER = 1.00;
        double SPEED_MODIFIER_INTERVAL = 0.25;

        robot.init(hardwareMap);

        telemetry.addData("Say", "Robot is ready");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            robot.leftFrontMotor.setPower(1);
            robot.rightFrontMotor.setPower(1);
            // Pause for 40 mS each cycle = update 25 times a second.
            sleep(40);
        }
    }
}