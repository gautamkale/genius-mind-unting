package guru.springframework.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Person;



@Transactional
public interface PersonRepository extends JpaRepository<Person, String> {
}