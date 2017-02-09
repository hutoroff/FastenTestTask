package ru.hutoroff.fasten.testtask.service.data.response;

import ru.hutoroff.fasten.testtask.service.data.MessageType;

import java.util.Date;

/**
 * Created by hutoroff on 06.02.17.
 */
public class SuccessResponseMessage extends ResponseMessage<SuccessResponseData> {

    public SuccessResponseMessage(String sequenceId, String apiToken, Date apiTokenExpirationDate) {
        this.setType(MessageType.CUSTOMER_API_TOKEN);
        this.setSequenceId(sequenceId);
        this.setData(new SuccessResponseData(apiToken, apiTokenExpirationDate));
    }
}
