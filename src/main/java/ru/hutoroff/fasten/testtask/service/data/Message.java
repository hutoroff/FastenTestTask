package ru.hutoroff.fasten.testtask.service.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Created by hutoroff on 06.02.17.
 */
@JsonPropertyOrder({"type", "sequence_id"})
public abstract class Message implements Serializable {

    static final long serialVersionUID = 1L;

    private MessageType type;
    @JsonProperty("sequence_id")
    private String sequenceId;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (getType() != message.getType()) return false;
        return getSequenceId().equals(message.getSequenceId());
    }

    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + getSequenceId().hashCode();
        return result;
    }
}
