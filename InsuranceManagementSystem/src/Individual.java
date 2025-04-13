public class Individual extends  Account{
    public Individual(User user) {
        super(user);
    }

    @Override
    public void addInsurancePolicy(Insurance insurance) {
        System.out.println("Bireysel müşteri için sigorta poliçesi ekleniyor:" + insurance.getName());
        getInsurances().add(insurance);
    }
}
