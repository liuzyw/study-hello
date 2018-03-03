package com.study.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface SendService {

    @Output("myInput")
    SubscribableChannel sendOrder();
}
