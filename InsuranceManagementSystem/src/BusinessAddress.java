public class BusinessAddress implements Address{
    private String companyName;
    private String department;
    private String fullAddress;

    public BusinessAddress(String companyName, String department, String fullAddress) {
        this.companyName = companyName;
        this.department = department;
        this.fullAddress = fullAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String getAddressDetails() {
        return "İş Adresi: " + companyName + " (" + department + ") " + fullAddress;
    }
}
