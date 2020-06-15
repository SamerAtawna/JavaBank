package Common;

import Classes.Enums.Enums;

import java.io.Serializable;

public class Request implements Serializable {
    private Enums.RequestType type;
    private Serializable content;


    public Request(Enums.RequestType type) {
        super();
        this.type = type;
    }

    public Request(Enums.RequestType type, Serializable content) {
        this.type = type;
        this.content = content;
    }

    public Enums.RequestType getType() {
        return type;
    }

    public Serializable getContent() {
        return content;
    }
}
