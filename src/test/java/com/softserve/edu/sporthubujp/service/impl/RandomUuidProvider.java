package com.softserve.edu.sporthubujp.service.impl;

import java.util.UUID;

public class RandomUuidProvider implements UuidProvider {
    @Override
    public UUID uuid() {
        return UUID.randomUUID();
    }
}
