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

import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.service.facade.admin.referentiel.LiaisonAdminService;
import ma.zyn.app.ws.mapper.referentiel.LiaisonMapper;
import ma.zyn.app.ws.dto.referentiel.LiaisonDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/liaison/")
public class LiaisonRestAdmin {




    @Operation(summary = "Finds a list of all liaisons")
    @GetMapping("")
    public ResponseEntity<List<LiaisonDto>> findAll(){
        ResponseEntity<List<LiaisonDto>> res = null;
        List<Liaison> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<LiaisonDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all liaisons")
    @GetMapping("optimized")
    public ResponseEntity<List<LiaisonDto>> findAllOptimized(){
        ResponseEntity<List<LiaisonDto>> res = null;
        List<Liaison> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<LiaisonDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a liaison by id")
    @GetMapping("id/{id}")
    public ResponseEntity<LiaisonDto> findById(@PathVariable Long id) {
        Liaison t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            LiaisonDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a liaison by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<LiaisonDto> findByLibelle(@PathVariable String libelle) {
	    Liaison t = service.findByReferenceEntity(new Liaison(libelle));
        if (t != null) {
            mapper.init(true);
            LiaisonDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  liaison")
    @PostMapping("")
    public ResponseEntity<LiaisonDto> save(@RequestBody LiaisonDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Liaison myT = mapper.toItem(dto);
            Liaison t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                LiaisonDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  liaison")
    @PutMapping("")
    public ResponseEntity<LiaisonDto> update(@RequestBody LiaisonDto dto){
        ResponseEntity<LiaisonDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Liaison t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Liaison updated = service.update(t);
            LiaisonDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of liaison")
    @PostMapping("multiple")
    public ResponseEntity<List<LiaisonDto>> delete(@RequestBody List<LiaisonDto> dtos){
        ResponseEntity<List<LiaisonDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Liaison> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified liaison")
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

    @Operation(summary = "find by operationStadeOperatoire code")
    @GetMapping("operationStadeOperatoire/code/{code}")
    public List<LiaisonDto> findByOperationStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByOperationStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by operationStadeOperatoire code")
    @DeleteMapping("operationStadeOperatoire/code/{code}")
    public int deleteByOperationStadeOperatoireCode(@PathVariable String code){
        return service.deleteByOperationStadeOperatoireCode(code);
    }

    @Operation(summary = "Finds a liaison and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<LiaisonDto> findWithAssociatedLists(@PathVariable Long id) {
        Liaison loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        LiaisonDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<LiaisonDto> findDtos(List<Liaison> list){
        mapper.initObject(true);
        List<LiaisonDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<LiaisonDto> getDtoResponseEntity(LiaisonDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public LiaisonRestAdmin(LiaisonAdminService service, LiaisonMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final LiaisonAdminService service;
    private final LiaisonMapper mapper;





}
