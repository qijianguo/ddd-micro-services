package com.qijianguo.micro.services.user.domain.user.event;

import com.qijianguo.micro.services.base.libs.util.RandomUtils;
import com.qijianguo.micro.services.base.model.event.BaseEvent;
import com.qijianguo.micro.services.user.domain.user.entity.User;

import java.util.Date;

import static com.qijianguo.micro.services.base.libs.util.RandomUtils.LETTER_CHAR;

/**
 * 用户事件
 * @author qijianguo
 */
public class UserEvent extends BaseEvent<User> {

    private UserEvent() {
        setAggregateId("user");
        setAggregateType("");
    }

    public static UserEvent created(UserEvent.Type type, User user) {
        UserEvent userEvent = new UserEvent();
        userEvent.setId(RandomUtils.randomString(LETTER_CHAR, 32));
        userEvent.setEventType(type.name());
        userEvent.setEventPayload(user);
        userEvent.setCreateTime(new Date());
        return userEvent;
    }

    public enum Type {

        CREATED,

        UPDATED,

        DELETED,


    }
}
