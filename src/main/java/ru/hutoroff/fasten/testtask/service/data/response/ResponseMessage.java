package ru.hutoroff.fasten.testtask.service.data.response;

import ru.hutoroff.fasten.testtask.service.data.Message;

/**
 * Created by hutoroff on 06.02.17.
 */
public abstract class ResponseMessage<T> extends Message {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
