package ma.zyn.app.dao.facade.core.navire;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.navire.RealisationNavireProduit;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RealisationNavireProduitRepository extends AbstractRepository<RealisationNavireProduit,Long>  {

    List<RealisationNavireProduit> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<RealisationNavireProduit> findByRealisationNavireId(Long id);
    int deleteByRealisationNavireId(Long id);
    long countByRealisationNavireId(Long id);


}
