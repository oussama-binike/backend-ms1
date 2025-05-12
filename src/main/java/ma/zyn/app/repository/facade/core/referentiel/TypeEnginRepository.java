package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.TypeEngin;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.TypeEngin;
import java.util.List;


@Repository
public interface TypeEnginRepository extends AbstractRepository<TypeEngin,Long>  {
    TypeEngin findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeEngin(item.id,item.libelle) FROM TypeEngin item")
    List<TypeEngin> findAllOptimized();

}
