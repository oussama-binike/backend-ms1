package ma.zyn.app.dao.facade.core.aleas;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.aleas.ArretNonPlanifie;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.aleas.ArretNonPlanifie;
import java.util.List;


@Repository
public interface ArretNonPlanifieRepository extends AbstractRepository<ArretNonPlanifie,Long>  {
    ArretNonPlanifie findByCode(String code);
    int deleteByCode(String code);

    List<ArretNonPlanifie> findByStadeOperatoireCode(String code);
    List<ArretNonPlanifie> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<ArretNonPlanifie> findByCauseArretCode(String code);
    List<ArretNonPlanifie> findByCauseArretId(Long id);
    int deleteByCauseArretId(Long id);
    int deleteByCauseArretCode(String code);
    long countByCauseArretCode(String code);
    List<ArretNonPlanifie> findByActionEntrepriseCode(String code);
    List<ArretNonPlanifie> findByActionEntrepriseId(Long id);
    int deleteByActionEntrepriseId(Long id);
    int deleteByActionEntrepriseCode(String code);
    long countByActionEntrepriseCode(String code);

    @Query("SELECT NEW ArretNonPlanifie(item.id,item.code) FROM ArretNonPlanifie item")
    List<ArretNonPlanifie> findAllOptimized();

}
