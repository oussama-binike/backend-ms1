package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.StatusExercice;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.StatusExercice;
import java.util.List;


@Repository
public interface StatusExerciceRepository extends AbstractRepository<StatusExercice,Long>  {
    StatusExercice findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW StatusExercice(item.id,item.libelle) FROM StatusExercice item")
    List<StatusExercice> findAllOptimized();

}
