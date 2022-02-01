package test.Lekarennwm.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Supplier extends Company {

    private String licence;

    @OneToMany(mappedBy = "supplier")
    private List<Medical> medicals;

    public Supplier() {
    }

    public Supplier(String licence, Long id, String name, String country, String address) {
        super(id, name, country, address);
        this.licence = licence;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }
}
