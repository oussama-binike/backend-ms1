package ma.zyn.app.dao.facade.core.referentiel;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ConsommableStadeOperatoireRepository extends AbstractRepository<ConsommableStadeOperatoire,Long>  {

    List<ConsommableStadeOperatoire> findByStadeOperatoireCode(String code);
    List<ConsommableStadeOperatoire> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<ConsommableStadeOperatoire> findByConsommableCode(String code);
    List<ConsommableStadeOperatoire> findByConsommableId(Long id);
    int deleteByConsommableId(Long id);
    int deleteByConsommableCode(String code);
    long countByConsommableCode(String code);


}
