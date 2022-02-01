package test.Lekarennwm.Api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.Lekarennwm.Service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryRestController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDTO> getAllCategories() {
        return this.categoryService.getAllCategories().stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }
}
