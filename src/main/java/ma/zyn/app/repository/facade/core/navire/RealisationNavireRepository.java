package ma.zyn.app.dao.facade.core.navire;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.navire.RealisationNavire;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RealisationNavireRepository extends AbstractRepository<RealisationNavire,Long>  {

    List<RealisationNavire> findByDestinationNavireCode(String code);
    List<RealisationNavire> findByDestinationNavireId(Long id);
    int deleteByDestinationNavireId(Long id);
    int deleteByDestinationNavireCode(String code);
    long countByDestinationNavireCode(String code);

    @Query("SELECT NEW RealisationNavire(item.id,item.libelle) FROM RealisationNavire item")
    List<RealisationNavire> findAllOptimized();

}
