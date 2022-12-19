package threephone.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import threephone.group.model.otp.SmsOTP;
import threephone.group.service.otp.SmsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class SmsController {


    @Autowired
    SmsService smsService;
    @Autowired
    private SimpMessagingTemplate websocket;
    private final String TOPIC_DESTINATION = "/lesson/sms";

    @PostMapping("/mobileNo")
    public ResponseEntity<?> sendOtp(@RequestBody SmsOTP sms){
        try {
            smsService.send(sms);
        }catch (Exception e){
            return new ResponseEntity<>("Vui lòng kiểm tra lại số điện thoại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        websocket.convertAndSend(TOPIC_DESTINATION,getTimeStamp()+": SMS đã được gửi!" + sms.getPhoneNumber());
        return new ResponseEntity<>("Đã gửi mã OTP xin vui lòng nhập mã",HttpStatus.OK);
    }
    private String getTimeStamp(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

}
