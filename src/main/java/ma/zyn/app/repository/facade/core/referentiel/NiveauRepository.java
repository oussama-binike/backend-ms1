package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Niveau;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Niveau;
import java.util.List;


@Repository
public interface NiveauRepository extends AbstractRepository<Niveau,Long>  {
    Niveau findByCode(String code);
    int deleteByCode(String code);

    List<Niveau> findByTrancheeCode(String code);
    List<Niveau> findByTrancheeId(Long id);
    int deleteByTrancheeId(Long id);
    int deleteByTrancheeCode(String code);
    long countByTrancheeCode(String code);

    @Query("SELECT NEW Niveau(item.id,item.libelle) FROM Niveau item")
    List<Niveau> findAllOptimized();

}
