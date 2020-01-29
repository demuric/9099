package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="SkyStoneTeleOp", group="Pushbot")
//@Disabled
public class SkyStoneTeleOp extends LinearOpMode {
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
    public void runOpMode() {

        double gamepad1LeftY;
        double gamepad1LeftX;
        double gamepad1RightX;
        double gamepad1RightY;

        double SPEED_MODIFIER = 1.0;
        double SPEED_MODIFIER_INTERVAL = 0.25;

        robot.init(hardwareMap);
        robot.clawservobottom.setPosition(0.5);
        robot.clawservotop.setPosition(0.5);

        telemetry.addData("Say", "Robot is ready");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            gamepad1LeftY = gamepad1.left_stick_y;
            gamepad1LeftX = gamepad1.left_stick_x;
            gamepad1RightX = -gamepad1.right_stick_x;

            telemetry.addData("LeftY: ", gamepad1LeftY);
            telemetry.addData("LeftX: ", gamepad1LeftX);
            telemetry.addData("RightX: ", gamepad1RightX);

            telemetry.addData("red: ", robot.color_sensor.red());
            telemetry.addData("blue: ", robot.color_sensor.blue());

            telemetry.update();

            //Mecanum Formulas
            final double v1 = gamepad1LeftY - gamepad1LeftX + gamepad1RightX;
            final double v2 = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
            final double v3 = gamepad1LeftY + gamepad1LeftX + gamepad1RightX;
            final double v4 = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;

            //Write the values to the Motor`
            robot.leftFrontMotor.setPower(v1);
            robot.leftBackMotor.setPower(v3);

            robot.rightFrontMotor.setPower(v2);
            robot.rightBackMotor.setPower(v4);

            // raises the arm
            if (gamepad1.dpad_up) {
                robot.armMotorLeft.setPower(-0.8);
                robot.armMotorRight.setPower(0.8);
            }
            // Lowers the arm
            else if (gamepad1.dpad_down){
                robot.armMotorLeft.setPower(0.8);
                robot.armMotorRight.setPower(-0.8);
            }
            // Sets the arm motor to not move
            else{
                robot.armMotorLeft.setPower(0);
                robot.armMotorRight.setPower(0);
            }

            if(gamepad1.a){
                //close the claw
                robot.clawservotop.setPosition(1);
            }
            else if(gamepad1.b){
                // open the claw
                robot.clawservotop.setPosition(0.2);
            }

            if(gamepad1.x){
                // lower the claw
                robot.clawservobottom.setPosition(0.0);
            }
            else if(gamepad1.y){
                // raise the claw
                robot.clawservobottom.setPosition(0.5);
            }

        }
    }
}