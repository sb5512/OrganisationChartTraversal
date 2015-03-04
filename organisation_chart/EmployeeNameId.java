package organisation_chart;

public class EmployeeNameId {
    private String name;
    private Integer managerId;
    public EmployeeNameId(String name , Integer managerId ){
        this.name = name;
        this.managerId = managerId;
    }
    public  String getName(){
        return name;
    }

    public  Integer getManagerId(){
        return managerId;
    }

}
