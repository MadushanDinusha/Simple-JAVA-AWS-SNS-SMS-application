package com.aws.sms;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.*;

import java.util.HashMap;
import java.util.Map;



public class App {
    public static void main(String[] args) {


        AmazonSNSClientBuilder clientBuilder = AmazonSNSClientBuilder.standard();
        clientBuilder.setRegion(Regions.US_WEST_2.getName());
        AmazonSNS snsClient = clientBuilder.build();

        String message = "Hi PAM";
        String phoneNumber = "+639XXXXXXX";
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue("Title")
                .withDataType("String"));

        sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);

    }


    public static void sendSMSMessage(AmazonSNS snsClient, String message,
                                      String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        System.out.println(result);
    }

}
