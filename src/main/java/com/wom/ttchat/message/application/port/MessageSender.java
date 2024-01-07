//package com.wom.ttchat.message.application.port;
//
//import com.wom.ttchat.message.adapter.in.request.MessageRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.messaging.MessagingException;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class MessageSender {
//
//    private final SimpMessagingTemplate template;
//
//    public void send(MessageRequest message) throws MessagingException {
//            template.convertAndSend("/topic/chat/" + message.getRoomId(), message);
//    }
//}