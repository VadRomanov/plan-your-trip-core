package com.planyourtrip.core.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.time.OffsetDateTime;

import static java.util.Objects.isNull;

@MappedSuperclass
public abstract class BaseEntity {

    public abstract Long getId();

    public abstract OffsetDateTime getCreatedAt();

    public abstract void setCreatedAt(OffsetDateTime createdAt);

    @PrePersist
    public void prePersist() {
        if (isNull(getCreatedAt())) {
            setCreatedAt(OffsetDateTime.now());
        }
    }
}
