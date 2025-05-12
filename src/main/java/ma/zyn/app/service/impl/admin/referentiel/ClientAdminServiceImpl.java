package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Client;
import ma.zyn.app.repository.facade.core.referentiel.ClientRepository;
import ma.zyn.app.service.facade.admin.referentiel.ClientAdminService;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.referentiel.TypeClientAdminService ;
import ma.zyn.app.bean.core.referentiel.TypeClient ;

import java.util.List;
@Service
public class ClientAdminServiceImpl implements ClientAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Client update(Client t) {
        Client loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Client findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Client findOrSave(Client t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Client result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Client> findAll() {
        return repository.findAll();
    }


    public List<Client> findByTypeClientCode(String code){
        return repository.findByTypeClientCode(code);
    }
    public List<Client> findByTypeClientId(Long id){
        return repository.findByTypeClientId(id);
    }
    public int deleteByTypeClientCode(String code){
        return repository.deleteByTypeClientCode(code);
    }
    public int deleteByTypeClientId(Long id){
        return repository.deleteByTypeClientId(id);
    }
    public long countByTypeClientCode(String code){
        return repository.countByTypeClientCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            repository.deleteById(id);
        }
        return condition;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Client> delete(List<Client> list) {
		List<Client> result = new ArrayList();
        if (list != null) {
            for (Client t : list) {
                if(repository.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    repository.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Client create(Client t) {
        Client loaded = findByReferenceEntity(t);
        Client saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Client findWithAssociatedLists(Long id){
        Client result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Client> update(List<Client> ts, boolean createIfNotExist) {
        List<Client> result = new ArrayList<>();
        if (ts != null) {
            for (Client t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Client loadedItem = repository.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        repository.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Client t, Client loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Client findByReferenceEntity(Client t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Client t){
        if( t != null) {
            t.setTypeClient(typeClientService.findOrSave(t.getTypeClient()));
        }
    }



    public List<Client> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Client>> getToBeSavedAndToBeDeleted(List<Client> oldList, List<Client> newList) {
        List<List<Client>> result = new ArrayList<>();
        List<Client> resultDelete = new ArrayList<>();
        List<Client> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Client> oldList, List<Client> newList, List<Client> resultUpdateOrSave, List<Client> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Client myOld = oldList.get(i);
                Client t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Client myNew = newList.get(i);
                Client t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private TypeClientAdminService typeClientService ;

    public ClientAdminServiceImpl(ClientRepository repository, TypeClientAdminService typeClientService) {
        this.repository = repository;
        this.typeClientService = typeClientService;
    }

    private ClientRepository repository;
}
