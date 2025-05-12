package  ma.zyn.app.api.facade.admin.reclamation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.reclamation.Reclamation;
import ma.zyn.app.service.facade.admin.reclamation.ReclamationAdminService;
import ma.zyn.app.ws.mapper.reclamation.ReclamationMapper;
import ma.zyn.app.ws.dto.reclamation.ReclamationDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/reclamation/")
public class ReclamationRestAdmin {




    @Operation(summary = "Finds a list of all reclamations")
    @GetMapping("")
    public ResponseEntity<List<ReclamationDto>> findAll(){
        ResponseEntity<List<ReclamationDto>> res = null;
        List<Reclamation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
            mapper.initObject(true);
        List<ReclamationDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all reclamations")
    @GetMapping("optimized")
    public ResponseEntity<List<ReclamationDto>> findAllOptimized(){
        ResponseEntity<List<ReclamationDto>> res = null;
        List<Reclamation> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
        mapper.initObject(true);
        List<ReclamationDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a reclamation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ReclamationDto> findById(@PathVariable Long id) {
        Reclamation t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ReclamationDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a reclamation by code")
    @GetMapping("code/{code}")
    public ResponseEntity<ReclamationDto> findByCode(@PathVariable String code) {
	    Reclamation t = service.findByReferenceEntity(new Reclamation(code));
        if (t != null) {
            mapper.init(true);
            ReclamationDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  reclamation")
    @PostMapping("")
    public ResponseEntity<ReclamationDto> save(@RequestBody ReclamationDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Reclamation myT = mapper.toItem(dto);
            Reclamation t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ReclamationDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  reclamation")
    @PutMapping("")
    public ResponseEntity<ReclamationDto> update(@RequestBody ReclamationDto dto){
        ResponseEntity<ReclamationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Reclamation t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Reclamation updated = service.update(t);
            ReclamationDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of reclamation")
    @PostMapping("multiple")
    public ResponseEntity<List<ReclamationDto>> delete(@RequestBody List<ReclamationDto> dtos){
        ResponseEntity<List<ReclamationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Reclamation> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified reclamation")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by entiteEmettrice code")
    @GetMapping("entiteEmettrice/code/{code}")
    public List<ReclamationDto> findByEntiteEmettriceCode(@PathVariable String code){
        return findDtos(service.findByEntiteEmettriceCode(code));
    }
    @Operation(summary = "delete by entiteEmettrice code")
    @DeleteMapping("entiteEmettrice/code/{code}")
    public int deleteByEntiteEmettriceCode(@PathVariable String code){
        return service.deleteByEntiteEmettriceCode(code);
    }
    @Operation(summary = "find by entiteDistinataire code")
    @GetMapping("entiteDistinataire/code/{code}")
    public List<ReclamationDto> findByEntiteDistinataireCode(@PathVariable String code){
        return findDtos(service.findByEntiteDistinataireCode(code));
    }
    @Operation(summary = "delete by entiteDistinataire code")
    @DeleteMapping("entiteDistinataire/code/{code}")
    public int deleteByEntiteDistinataireCode(@PathVariable String code){
        return service.deleteByEntiteDistinataireCode(code);
    }
    @Operation(summary = "find by produitMarchand code")
    @GetMapping("produitMarchand/code/{code}")
    public List<ReclamationDto> findByProduitMarchandCode(@PathVariable String code){
        return findDtos(service.findByProduitMarchandCode(code));
    }
    @Operation(summary = "delete by produitMarchand code")
    @DeleteMapping("produitMarchand/code/{code}")
    public int deleteByProduitMarchandCode(@PathVariable String code){
        return service.deleteByProduitMarchandCode(code);
    }
    @Operation(summary = "find by etatReclamation code")
    @GetMapping("etatReclamation/code/{code}")
    public List<ReclamationDto> findByEtatReclamationCode(@PathVariable String code){
        return findDtos(service.findByEtatReclamationCode(code));
    }
    @Operation(summary = "delete by etatReclamation code")
    @DeleteMapping("etatReclamation/code/{code}")
    public int deleteByEtatReclamationCode(@PathVariable String code){
        return service.deleteByEtatReclamationCode(code);
    }

    @Operation(summary = "Finds a reclamation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ReclamationDto> findWithAssociatedLists(@PathVariable Long id) {
        Reclamation loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ReclamationDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ReclamationDto> findDtos(List<Reclamation> list){
        mapper.initList(false);
        mapper.initObject(true);
        List<ReclamationDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ReclamationDto> getDtoResponseEntity(ReclamationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ReclamationRestAdmin(ReclamationAdminService service, ReclamationMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ReclamationAdminService service;
    private final ReclamationMapper mapper;





}
