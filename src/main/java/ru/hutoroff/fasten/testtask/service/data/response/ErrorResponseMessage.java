package ru.hutoroff.fasten.testtask.service.data.response;

import ru.hutoroff.fasten.testtask.service.data.MessageType;

/**
 * Created by hutoroff on 06.02.17.
 */
public class ErrorResponseMessage extends ResponseMessage<ErrorResponseData> {
    public ErrorResponseMessage(String sequenceId, String error_description, String error_code) {
        this.setType(MessageType.CUSTOMER_ERROR);
        this.setSequenceId(sequenceId);
        this.setData(new ErrorResponseData(error_description, error_code));
    }
}
