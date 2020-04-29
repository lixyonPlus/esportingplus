package com.panda.esportingplus.user.event;

import com.panda.esportingplus.common.event.Event;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 发送问候语事件
 * @author
 */
public class GreetingConsumerEvent implements Event, Serializable {

    private static final long serialVersionUID = -2280606331249922892L;

    private List<String> uidList = new ArrayList<>();


    public GreetingConsumerEvent() {
    }

    public GreetingConsumerEvent(List<String> uidList) {
        this.uidList = uidList;
    }

    public GreetingConsumerEvent(String uid) {
        if (this.uidList == null) {
            this.uidList = new ArrayList<>();
        }
        this.uidList.add(uid);
    }

    public List<String> getUidList() {
        return uidList;
    }

    public void setUidList(List<String> uidList) {
        this.uidList = uidList;
    }
}
