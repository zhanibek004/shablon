interface ICostCalculation{
    public double CalculateCost(int passanger, int TypeClass, boolean bag);

}
class AirPlaneTravel implements ICostCalculation {
    public double CalculateCost(int passanger, int TypeClass, boolean bag){
        double cost =0;
    if (TypeClass == 1)
        cost = passanger *1000.6574;
    else if (TypeClass==2)
        cost = passanger*900.6574;

    else
        cost = passanger*600.6574;

    if (bag)
        cost = cost*2;
    return cost;
    }
}
class BusTravel implements ICostCalculation {
    public double CalculateCost(int passanger, int TypeClass, boolean bag){
        double cost =0;
        if (TypeClass == 1)
            cost = passanger *500.6574;
        else if (TypeClass==2)
            cost = passanger*400.6574;

        else
            cost = passanger*300.6574;

        if (bag)
            cost = cost*2;
        return cost;
    }
}
class TrainTravel implements ICostCalculation {
    public double CalculateCost(int passanger, int TypeClass, boolean bag){
        double cost =0;
        if (TypeClass == 1)
            cost = passanger *200.6574;
        else if (TypeClass==2)
            cost = passanger*100.6574;

        else
            cost = passanger*100.6574;

        if (bag)
            cost = cost*2;
        return cost;
    }
}


class TravelContext{
    private ICostCalculation calculation;
    public void ChangeCalculation(ICostCalculation calculation){
        this.calculation = calculation;
    }

    public double CalculationTravelCost(int passanger, int TypeClass, boolean bag) throws Exception {
        if (calculation == null)
            throw new Exception("adAdaDAdAFSAFF");
        return calculation.CalculateCost(passanger, TypeClass, bag);
    }
}



public class Main {
    public static void main(String[] args) throws Exception {
        TravelContext context = new TravelContext();
        context.ChangeCalculation(new AirPlaneTravel());
        double result = context.CalculationTravelCost(1, 1, false);
        System.out.println(result);

    }
}
