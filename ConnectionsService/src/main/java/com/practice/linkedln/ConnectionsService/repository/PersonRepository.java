package com.practice.linkedln.ConnectionsService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.practice.linkedln.ConnectionsService.entity.Person;

@Repository
public interface PersonRepository extends Neo4jRepository<Person , Long>{

    Optional<Person> findByUserId(Long userId);

    @Query("match (personA:Person) -[:CONNECTED_TO]- (personB:Person) " +
            "where personA.userId = $userId " +
            "return personB")
    List<Person> getFirstDegreeConnections(Long userId);

}
