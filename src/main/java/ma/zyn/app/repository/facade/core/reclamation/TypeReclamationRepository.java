package ma.zyn.app.dao.facade.core.reclamation;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.reclamation.TypeReclamation;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.reclamation.TypeReclamation;
import java.util.List;


@Repository
public interface TypeReclamationRepository extends AbstractRepository<TypeReclamation,Long>  {
    TypeReclamation findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeReclamation(item.id,item.libelle) FROM TypeReclamation item")
    List<TypeReclamation> findAllOptimized();

}
