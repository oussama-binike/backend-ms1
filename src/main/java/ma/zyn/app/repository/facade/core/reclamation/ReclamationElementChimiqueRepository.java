package ma.zyn.app.dao.facade.core.reclamation;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ReclamationElementChimiqueRepository extends AbstractRepository<ReclamationElementChimique,Long>  {

    List<ReclamationElementChimique> findByReclamationId(Long id);
    int deleteByReclamationId(Long id);
    long countByReclamationCode(String code);
    List<ReclamationElementChimique> findByElementChimiqueCode(String code);
    List<ReclamationElementChimique> findByElementChimiqueId(Long id);
    int deleteByElementChimiqueId(Long id);
    int deleteByElementChimiqueCode(String code);
    long countByElementChimiqueCode(String code);


}
