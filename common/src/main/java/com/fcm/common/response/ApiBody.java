package com.fcm.common.response;

import com.fcm.common.constant.FieldConstant;
import com.fcm.common.constant.IMessage;

import java.util.HashMap;

public class ApiBody extends HashMap<String, Object> {

    public void setMessage(IMessage message) {
        this.put(FieldConstant.MESSAGE, message.getValue());
    }
}