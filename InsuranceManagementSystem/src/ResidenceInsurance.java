import java.util.Date;

public class ResidenceInsurance extends Insurance{

    public ResidenceInsurance(String name, double fee, Date startDate, Date endDate) {
        super(name, fee, startDate, endDate);
    }

    @Override
    public double calculate() {
        return getFee() * 1.05;
    }
}
