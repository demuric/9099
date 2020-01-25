package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="SkyStoneTeleOpTank", group="Pushbot")
//@Disabled
public class SkyStoneTeleOpTankdrive extends LinearOpMode
{
    /* Declare OpMode members. */
    SkyStoneHardware robot = new SkyStoneHardware();
    private ElapsedTime runtime  = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: AndyMark NeveRest Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    double SPEED_MODIFIER = 1.00;
    double SPEED_MODIFIER_INTERVAL = 0.25;
    @Override
    public void runOpMode()
    {

        double gamepad1LeftY;
        double gamepad1RightX;

        double SPEED_MODIFIER = 1.00;
        double SPEED_MODIFIER_INTERVAL = 0.25;

        robot.init(hardwareMap);

        telemetry.addData("Say", "Robot is ready");
        telemetry.update();
        waitForStart();

        while (opModeIsActive())
        {
            // Tank drive
            gamepad1LeftY = gamepad1.left_stick_y;
            gamepad1RightX = gamepad1.right_stick_y;

            telemetry.addData("LeftY: ", gamepad1LeftY);
            telemetry.addData("RightX: ", gamepad1RightX);

            telemetry.update();

            //Write the values to the Motor`
            robot.leftFrontMotor.setPower(gamepad1LeftY);
            robot.leftBackMotor.setPower(gamepad1LeftY);

            robot.rightFrontMotor.setPower(gamepad1RightX);
            robot.rightBackMotor.setPower(gamepad1RightX);

            // raises the arm
            if (gamepad1.dpad_up)
            {
                robot.armMotorLeft.setPower(-1);
                robot.armMotorRight.setPower(-1);
            }
            // Lowers the arm
            if (gamepad1.dpad_down)
            {
                robot.armMotorLeft.setPower(1);
                robot.armMotorRight.setPower(1);
            }
            // Sets the arm motor to not move
            else
            {
                robot.armMotorLeft.setPower(0);
                robot.armMotorRight.setPower(0);
            }

            if (gamepad1.a)
            {
                //open the claw
                robot.clawservotop.setPosition(0.27);
                robot.clawservobottom.setPosition(0.27);
            }
            if (gamepad1.b)
            {
                // close the claw
                robot.clawservotop.setPosition(1);
                robot.clawservobottom.setPosition(1);
            }

        }
    }
}
