package com.capgemini.pecunia.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.pecunia.bean.Cheque;
@Repository
public interface ChequeDAO extends JpaRepository<Cheque,String>
{

}
