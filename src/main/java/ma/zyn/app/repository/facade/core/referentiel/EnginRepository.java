package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Engin;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Engin;
import java.util.List;


@Repository
public interface EnginRepository extends AbstractRepository<Engin,Long>  {
    Engin findByCode(String code);
    int deleteByCode(String code);

    List<Engin> findByTypeEnginCode(String code);
    List<Engin> findByTypeEnginId(Long id);
    int deleteByTypeEnginId(Long id);
    int deleteByTypeEnginCode(String code);
    long countByTypeEnginCode(String code);
    List<Engin> findByOperationStadeOperatoireCode(String code);
    List<Engin> findByOperationStadeOperatoireId(Long id);
    int deleteByOperationStadeOperatoireId(Long id);
    int deleteByOperationStadeOperatoireCode(String code);
    long countByOperationStadeOperatoireCode(String code);
    List<Engin> findByStadeOperatoireCode(String code);
    List<Engin> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);

    @Query("SELECT NEW Engin(item.id,item.libelle) FROM Engin item")
    List<Engin> findAllOptimized();

}
