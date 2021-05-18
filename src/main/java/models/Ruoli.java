package models;

import java.util.UUID;

public class Ruoli {
    private UUID uuid;
    private String role;
    private String description;

    public Ruoli() {
    }

    public Ruoli(UUID uuid, String role, String description) {
        this.uuid = uuid;
        this.role = role;
        this.description = description;
    }

    public Ruoli(String uuid, String role, String description) {
        this.uuid = UUID.fromString(uuid);
        this.role = role;
        this.description = description;
    }

    public Ruoli(String role, String description) {
        this.uuid = UUID.randomUUID();
        this.role = role;
        this.description = description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    public String getStringUuid() {
        return uuid.toString();
    }

    public void setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
