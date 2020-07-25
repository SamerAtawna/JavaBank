package Classes.Enums;

public class Enums {
   public enum Status{
        ACTIVE,
        INACTIVE
    }

    public enum Permission{
       ADMIN,
        USER
    }

    public enum ResponseType{
       AUTH_INVALID,
        AUTH_VALID,
        ALL_CLIENTS,
        CREATE_ACCOUNT,
        DISPOSE,
        WITHDRAW,
        ATM_IDENTIFY,
        ATM_CHARGE,
        TRANSACTIONS,
        NEW_USER

    }
    public enum RequestType{
        ALL_CLIENTS,
        AUTHENTICATION,
        CREATE_ACCOUNT,
        DISPOSE,
        WITHDRAW,
        ATM_IDENTIFY,
        ATM_CHARGE,
        TRANSACTIONS,
        NEW_USER

    }
}
