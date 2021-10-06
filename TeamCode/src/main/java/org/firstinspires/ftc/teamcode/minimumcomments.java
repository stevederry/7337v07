////////////////////////////////// THIS FILE TO BECOME TEACHING TEMPLATE FOR AUTONOMOUS MODE ////////////////////////
// Edit Date:   Aug 31, 2021 @ 14:08
// Team Name:   Name
// Team Number: FTCNumber
// Code Type:   OpMode for AUTONOMOUS
// Description: Brief description of what this code does, such as:
//              1. Start at predetermined location (positioned by drivers prior to game start)
//              2. Drive forward to make contact with game object, then pause to let object flex/bounce/roll/slide
//              3. Spin left to push object off its original position, then pause to let object flex/bounce/roll/slide
//              4. Spin right to return to original orientation and prepare to park on object's original location
//              5. Drive forward onto object's original location, then stop
//              6. Set gripper to open position
//              7. Wait for Teleop
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
package org.firstinspires.ftc.teamcode;
 
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="TemplateAutonGenericV06", group="Derry_FTC_Templates")

public class TemplateAutonGenericV06 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();        

    DcMotor leftDriveMotor  = null;                         
    DcMotor rightDriveMotor = null;                         
    DcMotor sweeperMotor    = null;                         
    Servo gripperServo      = null;                         
    Servo sweeperServo      = null;                               

    public static final long DRIVE_TIME_TO_OBJECT       = 10000;
    public static final long DRIVE_TIME_OBJECT_TO_BASE  = 2000;
    public static final long DRIVE_TIME_45_DEG_TURN     = 500;
    public static final long DRIVE_TIME_90_DEG_TURN     = DRIVE_TIME_45_DEG_TURN * 2;
    public static final double DRIVE_POWER_FAST         = .8;
    public static final double DRIVE_POWER_MEDIUM       = .5;
    public static final double DRIVE_POWER_SLOW         = .2;
    public static final double GRIPPER_SERVO_START      = 10;           
    public static final double GRIPPER_SERVO_REST       = 50;           
    public static final double GRIPPER_SERVO_OPEN       = 100;          
    public static final double GRIPPER_SERVO_GRIP_LG    = 150;          
    public static final double GRIPPER_SERVO_GRIP_SM    = 200;          
    public static final double GRIPPER_SERVO_CLOSED     = 250;

    @Override
    public void runOpMode() throws InterruptedException  {   
    
        telemetry.addData("Status", "Initialized", "name");
        telemetry.update();                

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        sweeperMotor = hardwareMap.dcMotor.get("sweeperMotor");
        gripperServo = hardwareMap.servo.get("gripperServo");

        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);     
        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);    
        sweeperMotor.setDirection(DcMotor.Direction.FORWARD);

        stopRobot();                        
        gripperServo.setPosition(GRIPPER_SERVO_START);      

        waitForStart();                     

        driveForward(DRIVE_TIME_TO_OBJECT,DRIVE_POWER_FAST);    
        stopRobot();                                                
        sleep((long) 2);                                     

        spinLeft(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_MEDIUM);    
        stopRobot();                                                
        sleep((long) 2);                                        

        spinRight(DRIVE_TIME_45_DEG_TURN,DRIVE_POWER_MEDIUM);   

        driveForward(DRIVE_TIME_OBJECT_TO_BASE,DRIVE_POWER_SLOW);    
        stopRobot();                                   

        gripperServo.setPosition(GRIPPER_SERVO_OPEN);
    }
    
    public void stopRobot(){                               
        leftDriveMotor.setPower(0);                         
        rightDriveMotor.setPower(0);                        
        sweeperMotor.setPower(0);                          
    }

    public void driveForward(double Time, double Power){    
        leftDriveMotor.setPower(Power);                     
        rightDriveMotor.setPower(Power);                    
        sleep((long) Time);                                
    }

    public void spinRight(double Time, double Power){       
        leftDriveMotor.setPower(Power);                    
        rightDriveMotor.setPower(-Power);                   
        sleep((long) Time);                                 
    }

    public void spinLeft(long Time, double Power){         
        leftDriveMotor.setPower(-Power);                    
        rightDriveMotor.setPower(Power);                    
        sleep(Time);                
    }
}   

// END OF FILE
