package com.scrapper.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrapper.bean.Person;


@Transactional
public interface PersonRepository extends JpaRepository<Person, String> {
}