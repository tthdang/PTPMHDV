package com.duc.manager.controller;

import com.duc.manager.entity.Customers;
import com.duc.manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

//    @PostMapping("/createCustomer")
//    Customers createCustomer(@RequestBody CustomerCreationRequest request){
//        return customerService.createCustomer(request);
//    }

//    @GetMapping("/getCustomer")
//    List<Customers> getCustomers(){
//        return customerService.getCustomers();
//    }

//    @GetMapping("getCustomer/{Id}")
//    Customers getCustomer(@PathVariable("Id") int Id){
//        return customerService.getCustomer(Id);
//    }

    @PostMapping("/createCustomers")
    public ResponseEntity<Customers> createCustomer(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String name = request.get("name");
        String email = request.get("email");
        String phone = request.get("phone");
        String address = request.get("address");

        Customers customer = customerService.createCustomerWithUser(username, password,name, email, phone, address);
        return ResponseEntity.ok(customer);
    }

    //Lấy dữ liệu
    @GetMapping("/getCustomer")
    public List<Map<String, Object>> getCustomersWithUsers() {
        return customerService.getCustomersWithUsers();
    }

    //Lấy dữ liệu theo id
    @GetMapping("/getCustomer/{customerId}")
    public List<Map<String, Object>> getCustomerWithUser(@PathVariable int customerId) {
        return customerService.getCustomerWithUserById(customerId);

    }
}

