package ru.backendpro.inheritance_3;

class Employee extends Person {
    private Long employeeId;
    private String position;
    private double salary;

    public Employee(Long employeeId, String name, String email, String position, double salary) {
        super(name, email); // Вызов конструктора суперкласса
        this.employeeId = employeeId;
        this.position = position;
        this.salary = salary;
    }

    public void processPayment(double amount) {
        System.out.println("⚙️ Сотрудник " + name + " обрабатывает платёж: " + amount + " руб.");
    }

    @Override
    public String getInfo() {
        return "Employee{id=" + employeeId + ", name='" + name + "', position='" + position + "'}";
    }
}
