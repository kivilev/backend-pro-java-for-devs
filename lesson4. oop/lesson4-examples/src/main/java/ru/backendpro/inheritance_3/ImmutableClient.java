package ru.backendpro.inheritance_3;

final class ImmutableClient {
    private final String name;
    private final String email;

    public ImmutableClient(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
