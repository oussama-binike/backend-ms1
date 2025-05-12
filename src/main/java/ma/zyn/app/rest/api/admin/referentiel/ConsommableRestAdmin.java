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

import ma.zyn.app.bean.core.referentiel.Consommable;
import ma.zyn.app.service.facade.admin.referentiel.ConsommableAdminService;
import ma.zyn.app.ws.mapper.referentiel.ConsommableMapper;
import ma.zyn.app.ws.dto.referentiel.ConsommableDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/consommable/")
public class ConsommableRestAdmin {




    @Operation(summary = "Finds a list of all consommables")
    @GetMapping("")
    public ResponseEntity<List<ConsommableDto>> findAll(){
        ResponseEntity<List<ConsommableDto>> res = null;
        List<Consommable> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
            mapper.initObject(true);
        List<ConsommableDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all consommables")
    @GetMapping("optimized")
    public ResponseEntity<List<ConsommableDto>> findAllOptimized(){
        ResponseEntity<List<ConsommableDto>> res = null;
        List<Consommable> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
        mapper.initObject(true);
        List<ConsommableDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a consommable by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ConsommableDto> findById(@PathVariable Long id) {
        Consommable t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ConsommableDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a consommable by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ConsommableDto> findByLibelle(@PathVariable String libelle) {
	    Consommable t = service.findByReferenceEntity(new Consommable(libelle));
        if (t != null) {
            mapper.init(true);
            ConsommableDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  consommable")
    @PostMapping("")
    public ResponseEntity<ConsommableDto> save(@RequestBody ConsommableDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Consommable myT = mapper.toItem(dto);
            Consommable t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ConsommableDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  consommable")
    @PutMapping("")
    public ResponseEntity<ConsommableDto> update(@RequestBody ConsommableDto dto){
        ResponseEntity<ConsommableDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Consommable t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Consommable updated = service.update(t);
            ConsommableDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of consommable")
    @PostMapping("multiple")
    public ResponseEntity<List<ConsommableDto>> delete(@RequestBody List<ConsommableDto> dtos){
        ResponseEntity<List<ConsommableDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Consommable> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified consommable")
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

    @Operation(summary = "find by unite code")
    @GetMapping("unite/code/{code}")
    public List<ConsommableDto> findByUniteCode(@PathVariable String code){
        return findDtos(service.findByUniteCode(code));
    }
    @Operation(summary = "delete by unite code")
    @DeleteMapping("unite/code/{code}")
    public int deleteByUniteCode(@PathVariable String code){
        return service.deleteByUniteCode(code);
    }

    @Operation(summary = "Finds a consommable and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ConsommableDto> findWithAssociatedLists(@PathVariable Long id) {
        Consommable loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ConsommableDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ConsommableDto> findDtos(List<Consommable> list){
        mapper.initList(false);
        mapper.initObject(true);
        List<ConsommableDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ConsommableDto> getDtoResponseEntity(ConsommableDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ConsommableRestAdmin(ConsommableAdminService service, ConsommableMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ConsommableAdminService service;
    private final ConsommableMapper mapper;





}
