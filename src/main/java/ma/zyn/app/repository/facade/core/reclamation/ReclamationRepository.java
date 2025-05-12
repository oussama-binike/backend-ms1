package ma.zyn.app.dao.facade.core.reclamation;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.reclamation.Reclamation;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.reclamation.Reclamation;
import java.util.List;


@Repository
public interface ReclamationRepository extends AbstractRepository<Reclamation,Long>  {
    Reclamation findByCode(String code);
    int deleteByCode(String code);

    List<Reclamation> findByEntiteEmettriceCode(String code);
    List<Reclamation> findByEntiteEmettriceId(Long id);
    int deleteByEntiteEmettriceId(Long id);
    int deleteByEntiteEmettriceCode(String code);
    long countByEntiteEmettriceCode(String code);
    List<Reclamation> findByEntiteDistinataireCode(String code);
    List<Reclamation> findByEntiteDistinataireId(Long id);
    int deleteByEntiteDistinataireId(Long id);
    int deleteByEntiteDistinataireCode(String code);
    long countByEntiteDistinataireCode(String code);
    List<Reclamation> findByProduitMarchandCode(String code);
    List<Reclamation> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);
    List<Reclamation> findByEtatReclamationCode(String code);
    List<Reclamation> findByEtatReclamationId(Long id);
    int deleteByEtatReclamationId(Long id);
    int deleteByEtatReclamationCode(String code);
    long countByEtatReclamationCode(String code);

    @Query("SELECT NEW Reclamation(item.id,item.code) FROM Reclamation item")
    List<Reclamation> findAllOptimized();

}
