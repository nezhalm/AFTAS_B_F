package com.example.aftas.repositories;
import com.example.aftas.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByNum(Integer num);
    Member findByNameContaining(String name);
    Member findByFamilyNameContaining(String familyName);
    boolean existsByIdentityNumber(String identityNumber);
}
