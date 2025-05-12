package ma.zyn.app.dao.facade.core.scenario;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;
import java.util.List;


@Repository
public interface ScenarioFluxRepository extends AbstractRepository<ScenarioFlux,Long>  {
    ScenarioFlux findByCode(String code);
    int deleteByCode(String code);

    List<ScenarioFlux> findByExerciceId(Long id);
    int deleteByExerciceId(Long id);
    long countByExerciceCode(String code);
    List<ScenarioFlux> findByStatusScenarioFluxCode(String code);
    List<ScenarioFlux> findByStatusScenarioFluxId(Long id);
    int deleteByStatusScenarioFluxId(Long id);
    int deleteByStatusScenarioFluxCode(String code);
    long countByStatusScenarioFluxCode(String code);

    @Query("SELECT NEW ScenarioFlux(item.id,item.libelle) FROM ScenarioFlux item")
    List<ScenarioFlux> findAllOptimized();

}
