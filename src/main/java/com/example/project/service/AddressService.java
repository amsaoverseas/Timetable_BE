package com.example.project.service;
import com.example.project.entity.Address;
import com.example.project.entity.Student;
import com.example.project.repository.AddressRepository;
import com.example.project.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AddressService {

	private final AddressRepository addressRepository;
	private final StudentRepository studentRepository;

	public AddressService(AddressRepository addressRepository,
	                      StudentRepository studentRepository) {
	    this.addressRepository = addressRepository;
	    this.studentRepository = studentRepository;
	}
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address updateAddress(Long id, Address addressDetails) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        address.setStreet(addressDetails.getStreet());
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setPincode(addressDetails.getPincode());
        Student student = studentRepository.findById(addressDetails.getStudent().getId())
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with id: " + addressDetails.getStudent().getId()));

        address.setStudent(student);
        return addressRepository.save(address);
    }

    public boolean deleteAddress(Long id) {

        if (!addressRepository.existsById(id)) {
            return false;
        }

        addressRepository.deleteById(id);
        return true;
    }

    public Optional<Address> getAddressByStudent(Long studentId) {
        return addressRepository.findByStudentId(studentId);
    }
}