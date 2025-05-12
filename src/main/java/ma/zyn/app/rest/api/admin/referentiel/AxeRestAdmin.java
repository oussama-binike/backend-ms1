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

import ma.zyn.app.bean.core.referentiel.Axe;
import ma.zyn.app.service.facade.admin.referentiel.AxeAdminService;
import ma.zyn.app.ws.mapper.referentiel.AxeMapper;
import ma.zyn.app.ws.dto.referentiel.AxeDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/axe/")
public class AxeRestAdmin {




    @Operation(summary = "Finds a list of all axes")
    @GetMapping("")
    public ResponseEntity<List<AxeDto>> findAll(){
        ResponseEntity<List<AxeDto>> res = null;
        List<Axe> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<AxeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all axes")
    @GetMapping("optimized")
    public ResponseEntity<List<AxeDto>> findAllOptimized(){
        ResponseEntity<List<AxeDto>> res = null;
        List<Axe> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<AxeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a axe by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AxeDto> findById(@PathVariable Long id) {
        Axe t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            AxeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a axe by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<AxeDto> findByLibelle(@PathVariable String libelle) {
	    Axe t = service.findByReferenceEntity(new Axe(libelle));
        if (t != null) {
            mapper.init(true);
            AxeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  axe")
    @PostMapping("")
    public ResponseEntity<AxeDto> save(@RequestBody AxeDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Axe myT = mapper.toItem(dto);
            Axe t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                AxeDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  axe")
    @PutMapping("")
    public ResponseEntity<AxeDto> update(@RequestBody AxeDto dto){
        ResponseEntity<AxeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Axe t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Axe updated = service.update(t);
            AxeDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of axe")
    @PostMapping("multiple")
    public ResponseEntity<List<AxeDto>> delete(@RequestBody List<AxeDto> dtos){
        ResponseEntity<List<AxeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Axe> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified axe")
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


    @Operation(summary = "Finds a axe and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AxeDto> findWithAssociatedLists(@PathVariable Long id) {
        Axe loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        AxeDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<AxeDto> findDtos(List<Axe> list){
        mapper.initObject(true);
        List<AxeDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<AxeDto> getDtoResponseEntity(AxeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public AxeRestAdmin(AxeAdminService service, AxeMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final AxeAdminService service;
    private final AxeMapper mapper;





}
