package ru.backendpro.inheritance_3;

class Person {
    protected String name;   // protected — доступно в подклассах
    protected String email;
    private String password; // private — НЕ доступно в подклассах

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.password = "default123"; // Устанавливается только здесь
    }

    public void sendNotification(String message) {
        System.out.println("📧 Отправка уведомления " + name + ": " + message);
    }

    public String getInfo() {
        return "Person{name='" + name + "', email='" + email + "'}";
    }

    // Метод для доступа к private полю
    protected String getPassword() {
        return password;
    }
}
