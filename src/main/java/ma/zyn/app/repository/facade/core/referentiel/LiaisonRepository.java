package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Liaison;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Liaison;
import java.util.List;


@Repository
public interface LiaisonRepository extends AbstractRepository<Liaison,Long>  {
    Liaison findByCode(String code);
    int deleteByCode(String code);

    List<Liaison> findByStockSourceId(Long id);
    int deleteByStockSourceId(Long id);
    long countByStockSourceCode(String code);
    List<Liaison> findByStockDestinationId(Long id);
    int deleteByStockDestinationId(Long id);
    long countByStockDestinationCode(String code);
    List<Liaison> findByEnginId(Long id);
    int deleteByEnginId(Long id);
    long countByEnginCode(String code);
    List<Liaison> findByOperationStadeOperatoireCode(String code);
    List<Liaison> findByOperationStadeOperatoireId(Long id);
    int deleteByOperationStadeOperatoireId(Long id);
    int deleteByOperationStadeOperatoireCode(String code);
    long countByOperationStadeOperatoireCode(String code);
    List<Liaison> findByProdduitId(Long id);
    int deleteByProdduitId(Long id);
    long countByProdduitCode(String code);

    @Query("SELECT NEW Liaison(item.id,item.libelle) FROM Liaison item")
    List<Liaison> findAllOptimized();

}
