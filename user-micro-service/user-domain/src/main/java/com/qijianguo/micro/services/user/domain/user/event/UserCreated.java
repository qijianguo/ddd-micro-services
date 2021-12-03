package com.qijianguo.micro.services.user.domain.user.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author qijianguo
 */
@Component
public class UserCreated extends ApplicationEvent {

    @Autowired
    private ApplicationEventPublisher publisher;

    public UserCreated(Object source) {
        super(source);
    }
}
