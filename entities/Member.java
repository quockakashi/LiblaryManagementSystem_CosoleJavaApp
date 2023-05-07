package libraryManagement.entities;

public class Member {

    private static int counter; //Count the number of member is current list
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    {
        id = ++counter;
    }

    public Member(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "ID: " + id +
                "First name: " + firstName +
                "Last name: " + lastName;
    }


}
