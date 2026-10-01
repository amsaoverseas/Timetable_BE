package com.example.project.controller;

import com.example.project.entity.Address;
import com.example.project.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @Operation(
            summary = "Create Student Address",
            description = "Creates a new address for a student"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return new ResponseEntity<>(
                addressService.createAddress(address),
                HttpStatus.CREATED
        );
    }
    @Operation(
            summary = "Get All Student Addresses",
            description = "Returns all student addresses"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }
    @Operation(
            summary = "Get Address By ID",
            description = "Returns a student address using the address ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address found successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {

        Optional<Address> address = addressService.getAddressById(id);

        return address.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Update Student Address",
            description = "Updates an existing student address using the address ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Address or student not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long id,
            @RequestBody Address address) {

        try {
            return ResponseEntity.ok(
                    addressService.updateAddress(id, address)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(
            summary = "Delete Student Address",
            description = "Deletes a student address using the address ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {

        if (addressService.deleteAddress(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    @Operation(
            summary = "Get Address By Student",
            description = "Returns the address associated with a student ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address found successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found for the student")
    })
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Address> getAddressByStudent(
            @PathVariable Long studentId) {

        Optional<Address> address =
                addressService.getAddressByStudent(studentId);

        return address.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}