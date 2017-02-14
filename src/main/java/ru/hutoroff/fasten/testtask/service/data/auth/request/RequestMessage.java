package ru.hutoroff.fasten.testtask.service.data.auth.request;

import ru.hutoroff.fasten.testtask.service.data.auth.Message;

/**
 * Created by hutoroff on 06.02.17.
 */
public class RequestMessage extends Message {

    private RequestData data;

    public RequestData getData() {
        return data;
    }

    public void setData(RequestData data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestMessage)) return false;
        if (!super.equals(o)) return false;

        RequestMessage that = (RequestMessage) o;

        return getData() != null ? getData().equals(that.getData()) : that.getData() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }
}
