package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IContractRepository extends JpaRepository<Contract, Integer>{
}
