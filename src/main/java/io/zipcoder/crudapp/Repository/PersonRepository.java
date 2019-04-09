package io.zipcoder.crudapp.Repository;
import io.zipcoder.crudapp.Entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person save(Person person);
}
