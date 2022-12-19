package threephone.group.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import threephone.group.model.otp.StoreOTP;
import threephone.group.model.otp.TempOTP;

@RestController
public class VerityOTPController {
    @PostMapping("/otp")
    public String verityOTP(@RequestBody TempOTP sms){
        if (sms.getOtp()== StoreOTP.getOtp())
            return "Đã nhập đúng OTP xin cảm ơn";
        else
            return "Sai mã OTP xin vui lòng nhập lại";
    }
}
