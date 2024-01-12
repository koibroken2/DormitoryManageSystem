package Manager;

public class Dormitory {
    private int num; //房号
    private int Floor; //楼层
    private String Building; //栋属
    private String Area; //区
    private int Capacity; //容量
    private int Persons; //人数

    public Dormitory(){

    }

    public Dormitory(int num,int Floor, String Building, String Area, int Capacity, int Persons){
        this.num = num;
        this.Floor = Floor;
        this.Building = Building;
        this.Area  = Area;
        this.Capacity = Capacity;
        this.Persons = Persons;

    }
    public  int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num = num;

    }

    public int getFloor() {
        return Floor;
    }

    public void setFloor(int Floor) {
        this.Floor = Floor;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public void setPersons(int persons){
        Persons = persons;
    }
    public int getPersons(){
        return Persons;
    }
}
