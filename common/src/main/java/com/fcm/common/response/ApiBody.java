package com.fcm.common.response;

import com.fcm.common.constant.FieldConstant;
import com.fcm.common.constant.Message;

import java.util.HashMap;

public class ApiBody extends HashMap<String, Object> {

    public void setMessage(Message message) {
        this.put(FieldConstant.MESSAGE, message);
    }
}