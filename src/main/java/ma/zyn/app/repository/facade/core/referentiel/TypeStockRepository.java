package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.TypeStock;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.TypeStock;
import java.util.List;


@Repository
public interface TypeStockRepository extends AbstractRepository<TypeStock,Long>  {
    TypeStock findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeStock(item.id,item.libelle) FROM TypeStock item")
    List<TypeStock> findAllOptimized();

}
