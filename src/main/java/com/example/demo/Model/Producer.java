package test.Lekarennwm.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Producer extends Company {

    private String certificate;

    @OneToMany(mappedBy = "producer")
    @JsonManagedReference
    private List<Medical> medicals;

    public Producer() {
    }

    public Producer(Long id, String name, String country, String address, String certificate) {
        super(id, name, country, address);
        this.certificate = certificate;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

}
