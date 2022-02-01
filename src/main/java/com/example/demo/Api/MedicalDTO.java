package test.Lekarennwm.Api;

import java.util.List;

public class MedicalDTO {
    public Long id;
    public String name;
    public String description;
    public ProducerDTO producer;
    public SupplierDTO supplier;
    public List<CategoryDTO> categories;
    public Double price;
}
