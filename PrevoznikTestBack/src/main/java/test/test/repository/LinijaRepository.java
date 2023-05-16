package test.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import test.test.model.Linija;

@Repository
public interface LinijaRepository extends JpaRepository<Linija,Long> {

	Linija findOneById(Long id);

	
    @Query("SELECT l FROM Linija l WHERE " +
            "l.brojMesta BETWEEN :brojMestaOd AND :brojMestaDo AND " +
            "l.cenaKarte BETWEEN :cenaKarteOd AND :cenaKarteDo AND " +
            "(:destinacija IS NULL OR l.destinacija LIKE %:destinacija%) AND " +
            "(:prevoznikId IS NULL OR l.prevoznik.id = :prevoznikId)")
    Page<Linija> search(@Param("brojMestaOd") Integer brojMestaOd, 
                        @Param("brojMestaDo") Integer brojMestaDo, 
                        @Param("cenaKarteOd") Double cenaKarteOd, 
                        @Param("cenaKarteDo") Double cenaKarteDo,
                        @Param("destinacija") String destinacija,
                        @Param("prevoznikId") Long prevoznikId,
                        Pageable pageable);
	
}
