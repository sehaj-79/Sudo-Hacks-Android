package Model;

public class BloodDonors {
    private String bd_name, bd_locality, bd_gender, bd_age, bd_pincode, bd_blood;

   public BloodDonors(){}

    public BloodDonors(String bd_name, String bd_locality, String bd_gender, String bd_age, String bd_pincode, String bd_blood) {
        this.bd_name = bd_name;
        this.bd_locality = bd_locality;
        this.bd_gender = bd_gender;
        this.bd_age = bd_age;
        this.bd_pincode = bd_pincode;
        this.bd_blood = bd_blood;
    }

    public String getBd_name() {
        return bd_name;
    }

    public void setBd_name(String bd_name) {
        this.bd_name = bd_name;
    }

    public String getBd_locality() {
        return bd_locality;
    }

    public void setBd_locality(String bd_locality) {
        this.bd_locality = bd_locality;
    }

    public String getBd_gender() {
        return bd_gender;
    }

    public void setBd_gender(String bd_gender) {
        this.bd_gender = bd_gender;
    }

    public String getBd_age() {
        return bd_age;
    }

    public void setBd_age(String bd_age) {
        this.bd_age = bd_age;
    }

    public String getBd_pincode() {
        return bd_pincode;
    }

    public void setBd_pincode(String bd_pincode) {
        this.bd_pincode = bd_pincode;
    }

    public String getBd_blood() {
        return bd_blood;
    }

    public void setBd_blood(String bd_blood) {
        this.bd_blood = bd_blood;
    }
}
