package com.imooc.broker;

import com.google.common.collect.Lists;
import com.imooc.api.Message;

import java.util.List;

public class MessageHolder {
    private List<Message> messages = Lists.newArrayList();

    public static final ThreadLocal<MessageHolder> holder = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new MessageHolder();
        }
    };

    public static void add(Message message) {
        holder.get().messages.add(message);
    }

    public static List<Message> clear() {
        List<Message> tmp = Lists.newArrayList(holder.get().messages);
        holder.remove();
        return tmp;
    }
}
