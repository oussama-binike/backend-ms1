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

import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.service.facade.admin.referentiel.UniteAdminService;
import ma.zyn.app.ws.mapper.referentiel.UniteMapper;
import ma.zyn.app.ws.dto.referentiel.UniteDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/unite/")
public class UniteRestAdmin {




    @Operation(summary = "Finds a list of all unites")
    @GetMapping("")
    public ResponseEntity<List<UniteDto>> findAll(){
        ResponseEntity<List<UniteDto>> res = null;
        List<Unite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<UniteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all unites")
    @GetMapping("optimized")
    public ResponseEntity<List<UniteDto>> findAllOptimized(){
        ResponseEntity<List<UniteDto>> res = null;
        List<Unite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<UniteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a unite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<UniteDto> findById(@PathVariable Long id) {
        Unite t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            UniteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a unite by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<UniteDto> findByLibelle(@PathVariable String libelle) {
	    Unite t = service.findByReferenceEntity(new Unite(libelle));
        if (t != null) {
            mapper.init(true);
            UniteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  unite")
    @PostMapping("")
    public ResponseEntity<UniteDto> save(@RequestBody UniteDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Unite myT = mapper.toItem(dto);
            Unite t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                UniteDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  unite")
    @PutMapping("")
    public ResponseEntity<UniteDto> update(@RequestBody UniteDto dto){
        ResponseEntity<UniteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Unite t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Unite updated = service.update(t);
            UniteDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of unite")
    @PostMapping("multiple")
    public ResponseEntity<List<UniteDto>> delete(@RequestBody List<UniteDto> dtos){
        ResponseEntity<List<UniteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Unite> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified unite")
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


    @Operation(summary = "Finds a unite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<UniteDto> findWithAssociatedLists(@PathVariable Long id) {
        Unite loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        UniteDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<UniteDto> findDtos(List<Unite> list){
        mapper.initObject(true);
        List<UniteDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<UniteDto> getDtoResponseEntity(UniteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public UniteRestAdmin(UniteAdminService service, UniteMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final UniteAdminService service;
    private final UniteMapper mapper;





}
