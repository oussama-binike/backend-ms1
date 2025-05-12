package ma.zyn.app.dao.facade.core.scenario;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.scenario.Exercice;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.scenario.Exercice;
import java.util.List;


@Repository
public interface ExerciceRepository extends AbstractRepository<Exercice,Long>  {
    Exercice findByCode(String code);
    int deleteByCode(String code);

    List<Exercice> findByStatusExerciceCode(String code);
    List<Exercice> findByStatusExerciceId(Long id);
    int deleteByStatusExerciceId(Long id);
    int deleteByStatusExerciceCode(String code);
    long countByStatusExerciceCode(String code);

    @Query("SELECT NEW Exercice(item.id,item.libelle) FROM Exercice item")
    List<Exercice> findAllOptimized();

}
