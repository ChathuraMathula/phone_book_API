package chathura.mathula.phonebook.controller;

import chathura.mathula.phonebook.model.Contact;
import chathura.mathula.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        if (contactRepository.existsById(contact.getId())) {
            System.out.println("Already Exists");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            Contact savedContact = contactRepository.save(contact);
            return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContactById(@PathVariable String id, @RequestBody Contact updatedContact) {
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact != null) {
            existingContact.setFirstname(updatedContact.getFirstname());
            existingContact.setLastname(updatedContact.getLastname());
            existingContact.setMobileNumber(updatedContact.getMobileNumber());
            existingContact.setHomeNumber(updatedContact.getHomeNumber());
            existingContact.setId(updatedContact.getId());
            contactRepository.save(existingContact);
            return new ResponseEntity<>(existingContact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable String id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
