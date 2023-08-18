package frc.robot;

import edu.wpi.first.wpilibj.Servo;

public class ServoDifferentialDrive {
    private final Servo leftServo;
    private final Servo rightServo;

    public ServoDifferentialDrive(int leftChannel, int rightChannel) {
        leftServo = new Servo(leftChannel);
        rightServo = new Servo(rightChannel);
    }

    /**
     * Arcade drive method.
     *
     * @param moveValue  The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
     * @param rotateValue  The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is positive.
     */
    public void arcadeDrive(double moveValue, double rotateValue) {
        double leftMotorSpeed;
        double rightMotorSpeed;

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }

        setMotorOutputs(leftMotorSpeed, rightMotorSpeed);
    }

    /**
     * Tank drive method.
     *
     * @param leftValue  The robot's left side speed [-1.0..1.0]. Forward is positive.
     * @param rightValue  The robot's right side speed [-1.0..1.0]. Forward is positive.
     */
    public void tankDrive(double leftValue, double rightValue) {
        setMotorOutputs(leftValue, rightValue);
    }

    public void setMotorOutputs(double leftValue, double rightValue) {
        leftServo.set(mapSpeedToServoPosition(leftValue));
        rightServo.set(mapSpeedToServoPosition(rightValue));
    }

    private void stopMotors(){
        leftServo.set(0);
        rightServo.set(0);
    }

    private double mapSpeedToServoPosition(double speed) {
        // Convert speed (-1.0 to 1.0) to servo position (0.0 to 1.0)
        return (speed + 1.0) / 2.0;
    }
}
