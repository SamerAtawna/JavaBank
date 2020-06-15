package Common;

import Classes.Enums.Enums;

import java.io.Serializable;

public class Response implements Serializable {
    private Serializable content;
    private Enums.ResponseType type;
    private boolean      error;

    public Response(Enums.ResponseType type, Serializable content) {
        this.content = content;
        this.type = type;
    }

    public Serializable getContent() {
        return content;
    }

    public Enums.ResponseType getType() {
        return type;
    }

}
