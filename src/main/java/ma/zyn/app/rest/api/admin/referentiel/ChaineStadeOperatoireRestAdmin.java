package  ma.zyn.app.api.facade.admin.referentiel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;
import ma.zyn.app.service.facade.admin.referentiel.ChaineStadeOperatoireAdminService;
import ma.zyn.app.ws.mapper.referentiel.ChaineStadeOperatoireMapper;
import ma.zyn.app.ws.dto.referentiel.ChaineStadeOperatoireDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/chaineStadeOperatoire/")
public class ChaineStadeOperatoireRestAdmin {




    @Operation(summary = "Finds a list of all chaineStadeOperatoires")
    @GetMapping("")
    public ResponseEntity<List<ChaineStadeOperatoireDto>> findAll(){
        ResponseEntity<List<ChaineStadeOperatoireDto>> res = null;
        List<ChaineStadeOperatoire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ChaineStadeOperatoireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all chaineStadeOperatoires")
    @GetMapping("optimized")
    public ResponseEntity<List<ChaineStadeOperatoireDto>> findAllOptimized(){
        ResponseEntity<List<ChaineStadeOperatoireDto>> res = null;
        List<ChaineStadeOperatoire> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<ChaineStadeOperatoireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a chaineStadeOperatoire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ChaineStadeOperatoireDto> findById(@PathVariable Long id) {
        ChaineStadeOperatoire t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ChaineStadeOperatoireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a chaineStadeOperatoire by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ChaineStadeOperatoireDto> findByLibelle(@PathVariable String libelle) {
	    ChaineStadeOperatoire t = service.findByReferenceEntity(new ChaineStadeOperatoire(libelle));
        if (t != null) {
            mapper.init(true);
            ChaineStadeOperatoireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  chaineStadeOperatoire")
    @PostMapping("")
    public ResponseEntity<ChaineStadeOperatoireDto> save(@RequestBody ChaineStadeOperatoireDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            ChaineStadeOperatoire myT = mapper.toItem(dto);
            ChaineStadeOperatoire t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ChaineStadeOperatoireDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  chaineStadeOperatoire")
    @PutMapping("")
    public ResponseEntity<ChaineStadeOperatoireDto> update(@RequestBody ChaineStadeOperatoireDto dto){
        ResponseEntity<ChaineStadeOperatoireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ChaineStadeOperatoire t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ChaineStadeOperatoire updated = service.update(t);
            ChaineStadeOperatoireDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of chaineStadeOperatoire")
    @PostMapping("multiple")
    public ResponseEntity<List<ChaineStadeOperatoireDto>> delete(@RequestBody List<ChaineStadeOperatoireDto> dtos){
        ResponseEntity<List<ChaineStadeOperatoireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<ChaineStadeOperatoire> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified chaineStadeOperatoire")
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

    @Operation(summary = "find by stadeOperatoire code")
    @GetMapping("stadeOperatoire/code/{code}")
    public List<ChaineStadeOperatoireDto> findByStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by stadeOperatoire code")
    @DeleteMapping("stadeOperatoire/code/{code}")
    public int deleteByStadeOperatoireCode(@PathVariable String code){
        return service.deleteByStadeOperatoireCode(code);
    }

    @Operation(summary = "Finds a chaineStadeOperatoire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ChaineStadeOperatoireDto> findWithAssociatedLists(@PathVariable Long id) {
        ChaineStadeOperatoire loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ChaineStadeOperatoireDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ChaineStadeOperatoireDto> findDtos(List<ChaineStadeOperatoire> list){
        mapper.initObject(true);
        List<ChaineStadeOperatoireDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ChaineStadeOperatoireDto> getDtoResponseEntity(ChaineStadeOperatoireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ChaineStadeOperatoireRestAdmin(ChaineStadeOperatoireAdminService service, ChaineStadeOperatoireMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ChaineStadeOperatoireAdminService service;
    private final ChaineStadeOperatoireMapper mapper;





}
