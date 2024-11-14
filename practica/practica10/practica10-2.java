import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class prac2 {
    public static void main(String[] args) {

        Department department = new Department();
        department.Name = "ss";
        Employee John = new Employee();
        Employee Jane = new Employee();
        Employee Jacl = new Employee();
        department.Add(John);
        department.Add(Jane);
        department.Add(Jacl);

        Department department1 = new Department();
        department1.Name = "aa";
        Employee Doe = new Employee();
        Employee Doe1 = new Employee();
        department1.Add(Doe);
        department1.Add(Doe1);
        
        Department department2 = new Department();
        department2.Name = "bb";
        department2.Add(department);
        department2.Add(department1);

    }

}
abstract class OrganizationComponent{
    public String Name;
    public double Salary;
    public abstract void Add(OrganizationComponent component);
    public abstract void Delete(OrganizationComponent component);

}
class Employee extends OrganizationComponent{

    @Override
    public void Add(OrganizationComponent component) {

    }

    @Override
    public void Delete(OrganizationComponent component) {

    }
}
class Department extends OrganizationComponent{

    private List<OrganizationComponent> components;

    public Department() {
        components = new List<OrganizationComponent>();
    }

    @Override
    public void Add(OrganizationComponent component) {

    }

    @Override
    public void Delete(OrganizationComponent component) {

    }
    public double GetSalary(){
        return components.Sum(components => components.Salary)
    }
    public int GetCount(){
        return (int) components.stream().count();
    }
}
