import java.util.Date;

public class HealthInsurance extends Insurance{

    public HealthInsurance(String name, double fee, Date startDate, Date endDate) {
        super(name, fee, startDate, endDate);
    }

    @Override
    public double calculate() {
        return getFee() * 1.1;
    }
}
