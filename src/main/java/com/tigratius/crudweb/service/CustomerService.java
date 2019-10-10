package com.tigratius.crudweb.service;

import com.tigratius.crudweb.entity.Customer;
import com.tigratius.crudweb.entity.Project;
import com.tigratius.crudweb.repository.CustomerRepository;
import com.tigratius.crudweb.repository.ProjectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerService {

    private CustomerRepository customerRepository;
    private ProjectRepository projectRepository;

    public CustomerService(CustomerRepository customerRepository, ProjectRepository projectRepository) {

        this.customerRepository = customerRepository;
        this.projectRepository = projectRepository;
    }

    public Customer getById(Long id) {
        try {
            return customerRepository.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public void delete(Long id) {
        customerRepository.delete(id);
    }

    public void create(String name, List<String> projectIds) {
       /* Customer customer = new Customer();
        customer.setName(name);
        Set<Project> projects= new HashSet<>();
        for (String projectId : projectIds) {
            Project project = projectRepository.getById(Long.parseLong(projectId));
            projects.add(project);
        }
        customer.setProjects(projects);*/

        Customer customer = getNewCustomer(name, projectIds);
        customerRepository.add(customer);
    }

    public void update(Long id, String name, List<String> projectIds) {
        /*Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        Set<Project> projects= new HashSet<>();
        for (String projectId : projectIds) {
            Project project = projectRepository.getById(Long.parseLong(projectId));
            projects.add(project);
        }
        customer.setProjects(projects);*/

        Customer customer = getNewCustomer(name, projectIds);
        customer.setId(id);
        customerRepository.update(customer);
    }

    public List<Project> getProjects()
    {
        return projectRepository.getAll();
    }

    private Customer getNewCustomer(String name, List<String> projectIds)
    {
        Customer customer = new Customer();
        customer.setName(name);
        Set<Project> projects= new HashSet<>();
        for (String projectId : projectIds) {
            Project project = projectRepository.getById(Long.parseLong(projectId));
            projects.add(project);
        }
        customer.setProjects(projects);
        return customer;
    }
}
