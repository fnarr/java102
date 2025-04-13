import java.util.Date;

public class CarInsurance extends Insurance{

    public CarInsurance(String name, double fee, Date startDate, Date endDate) {
        super(name, fee, startDate, endDate);
    }

    @Override
    public double calculate() {
        return getFee() * 1.2;
    }
}
