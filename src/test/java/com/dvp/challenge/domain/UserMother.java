package com.dvp.challenge.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserMother {

    public static User random() {
        LocalDateTime now = LocalDateTime.now();
        return new User(UUID.randomUUID().toString(), "Brayan rafael", "Parra perez", now, now);
    }
}
