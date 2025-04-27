package com.practice.linkedln.ConnectionsService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic sendRequestTopic(){
        return new NewTopic("request_send_topic", 3,(short)1);
    }

    @Bean
    public NewTopic acceptRequestTopic(){
        return new NewTopic("request_accept_topic", 3,(short)1);
    }

    @Bean
    public NewTopic rejectRequestTopic(){
        return new NewTopic("request_reject_topic", 3,(short)1);
    }


}
