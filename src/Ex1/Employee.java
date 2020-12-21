package Ex1;

import org.w3c.dom.ls.LSOutput;

import java.io.Serializable;

public class Employee extends Person implements Serializable {
    private double sallary;

    public Employee(String name, String surname, double sallary) {
        super(name, surname);
        this.sallary = sallary;
    }

    public double getSallary() {
        return sallary;
    }

    public void setSallary(double sallary) {
        this.sallary = sallary;
    }

    @Override
    String getInfo() {
        return super.getInfo() + " Płaca: " + sallary;
    }
}
