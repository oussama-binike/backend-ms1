package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.CoutStade;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.CoutStade;
import java.util.List;


@Repository
public interface CoutStadeRepository extends AbstractRepository<CoutStade,Long>  {
    CoutStade findByCode(String code);
    int deleteByCode(String code);

    List<CoutStade> findByStadeOperatoireCode(String code);
    List<CoutStade> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<CoutStade> findByUniteCode(String code);
    List<CoutStade> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);

    @Query("SELECT NEW CoutStade(item.id,item.libelle) FROM CoutStade item")
    List<CoutStade> findAllOptimized();

}
