package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.PointControle;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.PointControle;
import java.util.List;


@Repository
public interface PointControleRepository extends AbstractRepository<PointControle,Long>  {
    PointControle findByCode(String code);
    int deleteByCode(String code);

    List<PointControle> findByStadeOperatoireCode(String code);
    List<PointControle> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);

    @Query("SELECT NEW PointControle(item.id,item.libelle) FROM PointControle item")
    List<PointControle> findAllOptimized();

}
