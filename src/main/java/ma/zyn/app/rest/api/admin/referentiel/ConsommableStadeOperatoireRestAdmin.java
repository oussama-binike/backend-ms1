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

import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;
import ma.zyn.app.service.facade.admin.referentiel.ConsommableStadeOperatoireAdminService;
import ma.zyn.app.ws.mapper.referentiel.ConsommableStadeOperatoireMapper;
import ma.zyn.app.ws.dto.referentiel.ConsommableStadeOperatoireDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/consommableStadeOperatoire/")
public class ConsommableStadeOperatoireRestAdmin {




    @Operation(summary = "Finds a list of all consommableStadeOperatoires")
    @GetMapping("")
    public ResponseEntity<List<ConsommableStadeOperatoireDto>> findAll(){
        ResponseEntity<List<ConsommableStadeOperatoireDto>> res = null;
        List<ConsommableStadeOperatoire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ConsommableStadeOperatoireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a consommableStadeOperatoire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ConsommableStadeOperatoireDto> findById(@PathVariable Long id) {
        ConsommableStadeOperatoire t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ConsommableStadeOperatoireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  consommableStadeOperatoire")
    @PostMapping("")
    public ResponseEntity<ConsommableStadeOperatoireDto> save(@RequestBody ConsommableStadeOperatoireDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            ConsommableStadeOperatoire myT = mapper.toItem(dto);
            ConsommableStadeOperatoire t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ConsommableStadeOperatoireDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  consommableStadeOperatoire")
    @PutMapping("")
    public ResponseEntity<ConsommableStadeOperatoireDto> update(@RequestBody ConsommableStadeOperatoireDto dto){
        ResponseEntity<ConsommableStadeOperatoireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ConsommableStadeOperatoire t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ConsommableStadeOperatoire updated = service.update(t);
            ConsommableStadeOperatoireDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of consommableStadeOperatoire")
    @PostMapping("multiple")
    public ResponseEntity<List<ConsommableStadeOperatoireDto>> delete(@RequestBody List<ConsommableStadeOperatoireDto> dtos){
        ResponseEntity<List<ConsommableStadeOperatoireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<ConsommableStadeOperatoire> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified consommableStadeOperatoire")
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
    public List<ConsommableStadeOperatoireDto> findByStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by stadeOperatoire code")
    @DeleteMapping("stadeOperatoire/code/{code}")
    public int deleteByStadeOperatoireCode(@PathVariable String code){
        return service.deleteByStadeOperatoireCode(code);
    }

    @Operation(summary = "Finds a consommableStadeOperatoire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ConsommableStadeOperatoireDto> findWithAssociatedLists(@PathVariable Long id) {
        ConsommableStadeOperatoire loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ConsommableStadeOperatoireDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ConsommableStadeOperatoireDto> findDtos(List<ConsommableStadeOperatoire> list){
        mapper.initObject(true);
        List<ConsommableStadeOperatoireDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ConsommableStadeOperatoireDto> getDtoResponseEntity(ConsommableStadeOperatoireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ConsommableStadeOperatoireRestAdmin(ConsommableStadeOperatoireAdminService service, ConsommableStadeOperatoireMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ConsommableStadeOperatoireAdminService service;
    private final ConsommableStadeOperatoireMapper mapper;





}
