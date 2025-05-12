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

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService;
import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/stadeOperatoire/")
public class StadeOperatoireRestAdmin {




    @Operation(summary = "Finds a list of all stadeOperatoires")
    @GetMapping("")
    public ResponseEntity<List<StadeOperatoireDto>> findAll(){
        ResponseEntity<List<StadeOperatoireDto>> res = null;
        List<StadeOperatoire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
            mapper.initObject(true);
        List<StadeOperatoireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all stadeOperatoires")
    @GetMapping("optimized")
    public ResponseEntity<List<StadeOperatoireDto>> findAllOptimized(){
        ResponseEntity<List<StadeOperatoireDto>> res = null;
        List<StadeOperatoire> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
        mapper.initObject(true);
        List<StadeOperatoireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a stadeOperatoire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StadeOperatoireDto> findById(@PathVariable Long id) {
        StadeOperatoire t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            StadeOperatoireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a stadeOperatoire by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<StadeOperatoireDto> findByLibelle(@PathVariable String libelle) {
	    StadeOperatoire t = service.findByReferenceEntity(new StadeOperatoire(libelle));
        if (t != null) {
            mapper.init(true);
            StadeOperatoireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  stadeOperatoire")
    @PostMapping("")
    public ResponseEntity<StadeOperatoireDto> save(@RequestBody StadeOperatoireDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            StadeOperatoire myT = mapper.toItem(dto);
            StadeOperatoire t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                StadeOperatoireDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  stadeOperatoire")
    @PutMapping("")
    public ResponseEntity<StadeOperatoireDto> update(@RequestBody StadeOperatoireDto dto){
        ResponseEntity<StadeOperatoireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StadeOperatoire t = service.findById(dto.getId());
            mapper.copy(dto,t);
            StadeOperatoire updated = service.update(t);
            StadeOperatoireDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stadeOperatoire")
    @PostMapping("multiple")
    public ResponseEntity<List<StadeOperatoireDto>> delete(@RequestBody List<StadeOperatoireDto> dtos){
        ResponseEntity<List<StadeOperatoireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<StadeOperatoire> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified stadeOperatoire")
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


    @Operation(summary = "Finds a stadeOperatoire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StadeOperatoireDto> findWithAssociatedLists(@PathVariable Long id) {
        StadeOperatoire loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        StadeOperatoireDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<StadeOperatoireDto> findDtos(List<StadeOperatoire> list){
        mapper.initList(false);
        mapper.initObject(true);
        List<StadeOperatoireDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<StadeOperatoireDto> getDtoResponseEntity(StadeOperatoireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public StadeOperatoireRestAdmin(StadeOperatoireAdminService service, StadeOperatoireMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final StadeOperatoireAdminService service;
    private final StadeOperatoireMapper mapper;





}
