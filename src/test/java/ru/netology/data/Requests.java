package ru.netology.data;

public class Requests {

    private Requests() {}

    public static class LoginRequest {
        private String login;
        private String password;

        private LoginRequest() {}

        public LoginRequest(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class TokenRequest {
        private String login;
        private String code;

        private TokenRequest() {}

        public TokenRequest(String login, String code) {
            this.login = login;
            this.code = code;
        }

        public String getLogin() {
            return login;
        }

        public String getCode() {
            return code;
        }
    }

    public static class TransferRequest {
        private String from;
        private String to;
        private String amount;

        private TransferRequest() {}

        public TransferRequest(String from, String to, String amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getAmount() {
            return amount;
        }
    }
}