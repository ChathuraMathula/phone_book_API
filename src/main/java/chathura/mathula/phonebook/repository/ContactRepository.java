package chathura.mathula.phonebook.repository;

import chathura.mathula.phonebook.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
}
