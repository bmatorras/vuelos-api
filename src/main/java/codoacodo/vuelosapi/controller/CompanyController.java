package codoacodo.vuelosapi.controller;

import codoacodo.vuelosapi.exceptions.ResourceNotFoundException;
import codoacodo.vuelosapi.model.Company;
import codoacodo.vuelosapi.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")

public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @CrossOrigin
    @GetMapping("")
    public List<Company> getAllCompanies() {
        return companyService.allCompanies();
    }

    @PostMapping("/add")
    public void createCompany(@RequestBody Company company){
        companyService.createCompany(company);
    }

    @GetMapping("/{id}")
    public Optional<Company> findCompanyByID(@PathVariable Long id){
        return companyService.findCompanyByID(id);
    }

    @PutMapping("/update")
    public Optional<Company> updateCompany(@RequestBody Company company){
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id){
        try{
            companyService.deleteCompanyByID(id);
            return "company deleted";
            } catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());
            return "company not found";
        }
    }
}