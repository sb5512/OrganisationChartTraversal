package organisation_chart;

//Helper class used by Organisation parser as a value in its Hashmap which requires two fields, Name of employee and the managerId it belongs to.

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
