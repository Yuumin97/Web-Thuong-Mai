package threephone.group.model.otp;

import lombok.Data;

public class StoreOTP {
    private static int otp;

    public static int getOtp() {
        return otp;
    }

    public static void setOtp(int otp) {
        StoreOTP.otp = otp;
    }
}
