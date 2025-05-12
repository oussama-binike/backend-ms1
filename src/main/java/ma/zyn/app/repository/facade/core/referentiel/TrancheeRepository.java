package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Tranchee;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Tranchee;
import java.util.List;


@Repository
public interface TrancheeRepository extends AbstractRepository<Tranchee,Long>  {
    Tranchee findByCode(String code);
    int deleteByCode(String code);

    List<Tranchee> findByPanneauCode(String code);
    List<Tranchee> findByPanneauId(Long id);
    int deleteByPanneauId(Long id);
    int deleteByPanneauCode(String code);
    long countByPanneauCode(String code);

    @Query("SELECT NEW Tranchee(item.id,item.libelle) FROM Tranchee item")
    List<Tranchee> findAllOptimized();

}
