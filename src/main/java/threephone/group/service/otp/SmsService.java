package threephone.group.service.otp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;
import threephone.group.model.otp.SmsOTP;
import threephone.group.model.otp.StoreOTP;
@Component
public class SmsService {
    private final String ACCOUNT_SID ="ACe1044c5ea1f8765a412cc10801a2b8f5";
    private final String AUTH_TOKEN = "735f18bff347e273998db8787d2baaaa";
    private final String FROM_NUMBER = "+19295566333";
    public void send(SmsOTP sms){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        int min = 100000;
        int max = 999999;
        int number =(int) (Math.random()*(max-min+1)+min);
        String msg = " Mã OTP của bạn là " + number + " không gửi mã kích hoạt cho bất kỳ ai, xin vui lòng cảm ơn";
        Message message = Message.creator(new PhoneNumber(sms.getPhoneNumber()),new PhoneNumber(FROM_NUMBER),msg).create();
        StoreOTP.setOtp(number);
    }
}
