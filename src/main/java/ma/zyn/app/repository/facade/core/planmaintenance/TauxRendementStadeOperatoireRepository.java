package ma.zyn.app.dao.facade.core.planmaintenance;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TauxRendementStadeOperatoireRepository extends AbstractRepository<TauxRendementStadeOperatoire,Long>  {

    List<TauxRendementStadeOperatoire> findByScenarioFluxId(Long id);
    int deleteByScenarioFluxId(Long id);
    long countByScenarioFluxCode(String code);


}
