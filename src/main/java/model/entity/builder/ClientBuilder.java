package model.entity.builder;

import model.entity.Client;

import java.time.LocalDate;

/**
 * Created by Provalov on 25.09.2017.
 */
public class ClientBuilder {
    private Client client;

    public ClientBuilder createNewClient() {
        client = new Client();
        return this;
    }

    public ClientBuilder setId(int id) {
        client.setId(id);
        return this;
    }

    public ClientBuilder setFirstName(String firstName) {
        client.setFirstName(firstName);
        return this;
    }

    public ClientBuilder setLastName(String lastName) {
        client.setLastName(lastName);
        return this;
    }

    public ClientBuilder setEmail(String email) {
        client.setEmail(email);
        return this;
    }

    public ClientBuilder setPhone(String phone) {
        client.setPhoneNumber(phone);
        return this;
    }

    public ClientBuilder setSignUpDate(LocalDate signUpDate) {
        client.setRegistrationDate(signUpDate);
        return this;
    }

    public Client getClient() {
        return client;
    }


}
