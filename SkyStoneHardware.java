package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by RHSRobotics on 9/18/2019.
 */
/** Hardware Configuration on Phones

REV Expansion Hub 2:
  Motor Port 0: rmb
  Motor Port 1: lmb
  Motor Port 2: rmf
  Motor Port 3: lmf
  Servo port 0: ct
  Servo port 1: cb

REV Expansion Hub 1:
   Motor port 0: larm
   Motor port 1: rarm
   I2C Bus port 1: color
*/




public class SkyStoneHardware
{

    /* Public OpMode members. */
    public DcMotor  leftFrontMotor   = null;
    public DcMotor  leftBackMotor    = null;
    public DcMotor  rightFrontMotor  = null;
    public DcMotor  rightBackMotor   = null;
    public DcMotor  armMotorLeft     = null;
    public DcMotor  armMotorRight    = null;
    public ColorSensor color_sensor  = null;
    public Servo    clawservobottom  = null;
    public Servo    clawservotop     = null;



    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public SkyStoneHardware() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontMotor   = hwMap.get(DcMotor.class, "lmf");
        leftBackMotor    = hwMap.get(DcMotor.class, "lmb");
        rightFrontMotor  = hwMap.get(DcMotor.class, "rmf");
        rightBackMotor   = hwMap.get(DcMotor.class, "rmb");
        armMotorLeft     = hwMap.get(DcMotor.class, "larm");
        armMotorRight    = hwMap.get(DcMotor.class, "rarm");
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        color_sensor     = hwMap.colorSensor.get("color");
        clawservotop     = hwMap.get(Servo.class,"ct");
        clawservobottom  = hwMap.get(Servo.class,"cb");

        // Set all motors to zero power
        leftFrontMotor.setPower(0.0);
        leftBackMotor.setPower(0.0);
        rightFrontMotor.setPower(0.0);
        rightBackMotor.setPower(0.0);
        armMotorLeft.setPower(0.0);
        armMotorRight.setPower(0.0);
        clawservotop.setPosition(0.0);
        clawservobottom.setPosition(0.0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}

