package com.eduardo.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
 
import java.util.HashMap;
import java.util.Map;
 
public class ShortMessage {  // 发送短信息的类
    private Map<String, MessageAttributeValue> smsAttributes;
 
    public Map<String, MessageAttributeValue> getDefaultSMSAttributes() {
        if (smsAttributes == null) {
            smsAttributes = new HashMap<>();
            smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                    .withStringValue("1")
                    .withDataType("String"));
            smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                    .withStringValue("0.05")
                    .withDataType("Number"));
            smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                    .withStringValue("Transactional")
                    .withDataType("String"));
        }
        return smsAttributes;
    }
 
    public PublishResult sendSMSMessage(String phoneNumber, String message) {
        return sendSMSMessage(phoneNumber, message, getDefaultSMSAttributes());
    }
 
    public PublishResult sendSMSMessage(String phoneNumber, String message, Map<String, MessageAttributeValue> smsAttributes) {
        AWSCredentials awsCredentials = new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return "AKIAJSP2U45KV5QSZC2Q"; // 带有发短信权限的 IAM 的 ACCESS_KEY
            }
 
            @Override
            public String getAWSSecretKey() {
                return "xG6m/sMual8whTzqv1u+5ShUfTE9YZ6jgCbcPF58"; // 带有发短信权限的 IAM 的 SECRET_KEY
            }
        };
        AWSCredentialsProvider provider = new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                return awsCredentials;
            }
 
            @Override
            public void refresh() {
            }
        };
        AmazonSNS amazonSNS = null;
        try {
            amazonSNS = AmazonSNSClientBuilder.standard().withCredentials(provider).withRegion("ap-southeast-1").build();
        } catch (Exception e) {
 
        }
        return amazonSNS.publish(
                new PublishRequest()
                        .withMessage(message)
                        .withPhoneNumber(phoneNumber)
                        .withMessageAttributes(smsAttributes)
        );
    }
 
    public static void main(String[] args) {
        ShortMessage shortMessage = new ShortMessage();
        PublishResult publishResult = shortMessage.sendSMSMessage("+8615818888888", "短信内容");  //向手机号码发送信息。0.01美元一条。
        System.out.println(publishResult);
    }
 
}