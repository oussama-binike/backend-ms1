package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;
import java.util.List;


@Repository
public interface ChaineStadeOperatoireRepository extends AbstractRepository<ChaineStadeOperatoire,Long>  {
    ChaineStadeOperatoire findByCode(String code);
    int deleteByCode(String code);

    List<ChaineStadeOperatoire> findByStadeOperatoireCode(String code);
    List<ChaineStadeOperatoire> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);

    @Query("SELECT NEW ChaineStadeOperatoire(item.id,item.libelle) FROM ChaineStadeOperatoire item")
    List<ChaineStadeOperatoire> findAllOptimized();

}
