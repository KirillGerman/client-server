package repository.dto;

public class Client {

    public Client() {
    }

    public Client(String login, String token, Integer balance) {
        this.login = login;
        this.token = token;
        this.balance = balance;

    }

    public Client(String login, String token) {
        this.login = login;
        this.token = token;
    }

    String login;
    String token;
    Integer balance;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getBalance() { return balance ; }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "\"login\" : " + '\"' + login + '\"' +
                ", \"token\" : " + '\"' + token + '\"' +
                ", \"balance\" : " + balance +
                '}'; }


    public String toStringBalance() {
        return "{" +
                "\"balance\" : " + balance +
                '}'; }

}