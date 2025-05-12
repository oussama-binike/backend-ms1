package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import java.util.List;


@Repository
public interface StatusScenarioFluxRepository extends AbstractRepository<StatusScenarioFlux,Long>  {
    StatusScenarioFlux findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW StatusScenarioFlux(item.id,item.libelle) FROM StatusScenarioFlux item")
    List<StatusScenarioFlux> findAllOptimized();

}
