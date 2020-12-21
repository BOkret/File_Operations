package Ex1;

import java.io.Serializable;

public class Company implements Serializable {
    private static final long serialVersionUID = 3812017177088226599L;
    public static final int MAX_EMPLOYEES_NUMBER = 3;
    private Employee[] employees = new Employee[MAX_EMPLOYEES_NUMBER];
    private int index = 0;

//    public Company(Employee[] employees) {
//        this.employees = employees;
//    }

    public void add(Employee employee){
        employees[index] = employee;
        index++;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    void getInfo(){
        for (int i = 0; i < MAX_EMPLOYEES_NUMBER ; i++) {
            System.out.println(employees[i].getInfo());
        }
    }


}
