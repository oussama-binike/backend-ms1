package ma.zyn.app.dao.facade.core.planmaintenance;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import java.util.List;


@Repository
public interface PlanDisponibiliteRepository extends AbstractRepository<PlanDisponibilite,Long>  {
    PlanDisponibilite findByCode(String code);
    int deleteByCode(String code);

    List<PlanDisponibilite> findByStadeOperatoireCode(String code);
    List<PlanDisponibilite> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<PlanDisponibilite> findByScenarioFluxId(Long id);
    int deleteByScenarioFluxId(Long id);
    long countByScenarioFluxCode(String code);

    @Query("SELECT NEW PlanDisponibilite(item.id,item.libelle) FROM PlanDisponibilite item")
    List<PlanDisponibilite> findAllOptimized();

}
