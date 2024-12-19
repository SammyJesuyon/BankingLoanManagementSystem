package org.jesuyon.blms.usermanagement.user.service;

import org.jesuyon.blms.usermanagement.user.domain.Customer;
import org.jesuyon.blms.usermanagement.user.dto.CreateUserDto;
import org.jesuyon.blms.usermanagement.user.dto.CustomerDto;
import org.jesuyon.blms.usermanagement.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto createCustomer(CreateUserDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        return mapToDTO(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(String id, CustomerDto customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        return mapToDTO(customerRepository.save(customer));
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(String id) {
        return customerRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    private CustomerDto mapToDTO(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setCreatedAt(customer.getCreatedAt());
        dto.setUpdatedAt(customer.getUpdatedAt());
        return dto;
    }

    private Customer mapToEntity(CreateUserDto dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        return customer;
    }
}
