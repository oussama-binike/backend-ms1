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

import ma.zyn.app.bean.core.referentiel.Engin;
import ma.zyn.app.service.facade.admin.referentiel.EnginAdminService;
import ma.zyn.app.ws.mapper.referentiel.EnginMapper;
import ma.zyn.app.ws.dto.referentiel.EnginDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/engin/")
public class EnginRestAdmin {




    @Operation(summary = "Finds a list of all engins")
    @GetMapping("")
    public ResponseEntity<List<EnginDto>> findAll(){
        ResponseEntity<List<EnginDto>> res = null;
        List<Engin> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<EnginDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all engins")
    @GetMapping("optimized")
    public ResponseEntity<List<EnginDto>> findAllOptimized(){
        ResponseEntity<List<EnginDto>> res = null;
        List<Engin> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<EnginDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a engin by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EnginDto> findById(@PathVariable Long id) {
        Engin t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            EnginDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a engin by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EnginDto> findByLibelle(@PathVariable String libelle) {
	    Engin t = service.findByReferenceEntity(new Engin(libelle));
        if (t != null) {
            mapper.init(true);
            EnginDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  engin")
    @PostMapping("")
    public ResponseEntity<EnginDto> save(@RequestBody EnginDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Engin myT = mapper.toItem(dto);
            Engin t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                EnginDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  engin")
    @PutMapping("")
    public ResponseEntity<EnginDto> update(@RequestBody EnginDto dto){
        ResponseEntity<EnginDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Engin t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Engin updated = service.update(t);
            EnginDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of engin")
    @PostMapping("multiple")
    public ResponseEntity<List<EnginDto>> delete(@RequestBody List<EnginDto> dtos){
        ResponseEntity<List<EnginDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Engin> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified engin")
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

    @Operation(summary = "find by typeEngin code")
    @GetMapping("typeEngin/code/{code}")
    public List<EnginDto> findByTypeEnginCode(@PathVariable String code){
        return findDtos(service.findByTypeEnginCode(code));
    }
    @Operation(summary = "delete by typeEngin code")
    @DeleteMapping("typeEngin/code/{code}")
    public int deleteByTypeEnginCode(@PathVariable String code){
        return service.deleteByTypeEnginCode(code);
    }
    @Operation(summary = "find by operationStadeOperatoire code")
    @GetMapping("operationStadeOperatoire/code/{code}")
    public List<EnginDto> findByOperationStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByOperationStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by operationStadeOperatoire code")
    @DeleteMapping("operationStadeOperatoire/code/{code}")
    public int deleteByOperationStadeOperatoireCode(@PathVariable String code){
        return service.deleteByOperationStadeOperatoireCode(code);
    }
    @Operation(summary = "find by stadeOperatoire code")
    @GetMapping("stadeOperatoire/code/{code}")
    public List<EnginDto> findByStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by stadeOperatoire code")
    @DeleteMapping("stadeOperatoire/code/{code}")
    public int deleteByStadeOperatoireCode(@PathVariable String code){
        return service.deleteByStadeOperatoireCode(code);
    }

    @Operation(summary = "Finds a engin and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EnginDto> findWithAssociatedLists(@PathVariable Long id) {
        Engin loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        EnginDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<EnginDto> findDtos(List<Engin> list){
        mapper.initObject(true);
        List<EnginDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<EnginDto> getDtoResponseEntity(EnginDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public EnginRestAdmin(EnginAdminService service, EnginMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final EnginAdminService service;
    private final EnginMapper mapper;





}
