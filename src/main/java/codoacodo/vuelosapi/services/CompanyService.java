package codoacodo.vuelosapi.services;

import codoacodo.vuelosapi.exceptions.ResourceNotFoundException;
import codoacodo.vuelosapi.model.Company;
import codoacodo.vuelosapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    public List<Company> allCompanies(){
        return companyRepository.findAll();
    }

    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    public Optional<Company> findCompanyByID(Long id) {
        return companyRepository.findById(id);
    }

    public void deleteCompanyByID(Long id) throws ResourceNotFoundException {
        Company company = companyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("company", "id", id));
        companyRepository.deleteById(company.getId());
    }

    public Optional<Company> updateCompany(Company company) {
        companyRepository.save(company);
        return companyRepository.findById(company.getId());
    }
}