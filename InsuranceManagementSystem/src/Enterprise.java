public class Enterprise extends Account{
    public Enterprise(User user) {
        super(user);
    }

    @Override
    public void addInsurancePolicy(Insurance insurance) {
        System.out.println("Kurumsal müşteri için sigorta poliçesi ekleniyor: " + insurance.getName());
        getInsurances().add(insurance);
    }
}
