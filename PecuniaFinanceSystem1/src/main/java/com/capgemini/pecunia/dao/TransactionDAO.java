package com.capgemini.pecunia.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.pecunia.bean.Transaction;
@Repository
public interface TransactionDAO extends JpaRepository<Transaction,String>
{


}
