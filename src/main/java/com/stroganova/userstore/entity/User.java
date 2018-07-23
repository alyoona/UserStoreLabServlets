package com.stroganova.userstore.entity;

public class User {
    private long id;
    private String name;
    private Double salary;

    public User() {
    }

    public User(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        /*if (id != user.id) return false;*/
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return salary != null ? salary.equals(user.salary) : user.salary == null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }

}
