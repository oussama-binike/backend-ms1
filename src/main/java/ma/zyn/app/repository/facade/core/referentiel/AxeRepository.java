package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Axe;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Axe;
import java.util.List;


@Repository
public interface AxeRepository extends AbstractRepository<Axe,Long>  {
    Axe findByCode(String code);
    int deleteByCode(String code);

    List<Axe> findBySiteCode(String code);
    List<Axe> findBySiteId(Long id);
    int deleteBySiteId(Long id);
    int deleteBySiteCode(String code);
    long countBySiteCode(String code);

    @Query("SELECT NEW Axe(item.id,item.libelle) FROM Axe item")
    List<Axe> findAllOptimized();

}
