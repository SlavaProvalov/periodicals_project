package model.entity.builder;

import model.entity.Client;

import java.time.LocalDate;

/**
 * Created by Provalov on 25.09.2017.
 */
public class ClientBuilder {
    public static Client createClient(int id, String firstName, String lastName, String email, String phoneNumber) {
        return new Client(id, firstName, lastName, email, phoneNumber, LocalDate.now());
    }
}
