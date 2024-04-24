package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.pc3r.vfarm.entities.Log}
 */
public class LogDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String action;
    private final Instant createdAt;
    private final UserDto user;

    public LogDto(Integer id, String action, Instant createdAt,
                  UserDto user) {
        this.id = id;
        this.action = action;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogDto entity = (LogDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.action, entity.action) &&
                Objects.equals(this.createdAt, entity.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, createdAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "action = " + action + ", " +
                "createdAt = " + createdAt + ")";
    }

    public UserDto getUser() {
        return user;
    }
}