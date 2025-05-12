package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.ConsommationSpecifique;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.ConsommationSpecifique;
import java.util.List;


@Repository
public interface ConsommationSpecifiqueRepository extends AbstractRepository<ConsommationSpecifique,Long>  {
    ConsommationSpecifique findByCode(String code);
    int deleteByCode(String code);

    List<ConsommationSpecifique> findByConsommableCode(String code);
    List<ConsommationSpecifique> findByConsommableId(Long id);
    int deleteByConsommableId(Long id);
    int deleteByConsommableCode(String code);
    long countByConsommableCode(String code);
    List<ConsommationSpecifique> findByStadeOperatoireCode(String code);
    List<ConsommationSpecifique> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<ConsommationSpecifique> findByUniteCode(String code);
    List<ConsommationSpecifique> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);

    @Query("SELECT NEW ConsommationSpecifique(item.id,item.libelle) FROM ConsommationSpecifique item")
    List<ConsommationSpecifique> findAllOptimized();

}
