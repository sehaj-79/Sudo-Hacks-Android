package Model;

public class User { private String name, gender,email,blood,locality,city,state,pin, age,id, DonorType;

    public User(String name, String gender, String email, String blood, String locality, String city, String state, String pin, String age, String id, String DonorType) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.blood = blood;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.pin = pin;
        this.age = age;
        this.id = id;
        this.DonorType = DonorType;

    }

    public User() {}

    public String getDonorType() {
        return DonorType;
    }

    public void setDonorType(String DonorType) {
        DonorType = DonorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}