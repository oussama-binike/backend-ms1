package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.TypeWagon;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.TypeWagon;
import java.util.List;


@Repository
public interface TypeWagonRepository extends AbstractRepository<TypeWagon,Long>  {
    TypeWagon findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeWagon(item.id,item.libelle) FROM TypeWagon item")
    List<TypeWagon> findAllOptimized();

}
