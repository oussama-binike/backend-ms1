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

import ma.zyn.app.bean.core.referentiel.CoutStade;
import ma.zyn.app.service.facade.admin.referentiel.CoutStadeAdminService;
import ma.zyn.app.ws.mapper.referentiel.CoutStadeMapper;
import ma.zyn.app.ws.dto.referentiel.CoutStadeDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/coutStade/")
public class CoutStadeRestAdmin {




    @Operation(summary = "Finds a list of all coutStades")
    @GetMapping("")
    public ResponseEntity<List<CoutStadeDto>> findAll(){
        ResponseEntity<List<CoutStadeDto>> res = null;
        List<CoutStade> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<CoutStadeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all coutStades")
    @GetMapping("optimized")
    public ResponseEntity<List<CoutStadeDto>> findAllOptimized(){
        ResponseEntity<List<CoutStadeDto>> res = null;
        List<CoutStade> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<CoutStadeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a coutStade by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CoutStadeDto> findById(@PathVariable Long id) {
        CoutStade t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            CoutStadeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a coutStade by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CoutStadeDto> findByLibelle(@PathVariable String libelle) {
	    CoutStade t = service.findByReferenceEntity(new CoutStade(libelle));
        if (t != null) {
            mapper.init(true);
            CoutStadeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  coutStade")
    @PostMapping("")
    public ResponseEntity<CoutStadeDto> save(@RequestBody CoutStadeDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            CoutStade myT = mapper.toItem(dto);
            CoutStade t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                CoutStadeDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  coutStade")
    @PutMapping("")
    public ResponseEntity<CoutStadeDto> update(@RequestBody CoutStadeDto dto){
        ResponseEntity<CoutStadeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CoutStade t = service.findById(dto.getId());
            mapper.copy(dto,t);
            CoutStade updated = service.update(t);
            CoutStadeDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of coutStade")
    @PostMapping("multiple")
    public ResponseEntity<List<CoutStadeDto>> delete(@RequestBody List<CoutStadeDto> dtos){
        ResponseEntity<List<CoutStadeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<CoutStade> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified coutStade")
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


    @Operation(summary = "Finds a coutStade and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CoutStadeDto> findWithAssociatedLists(@PathVariable Long id) {
        CoutStade loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        CoutStadeDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<CoutStadeDto> findDtos(List<CoutStade> list){
        mapper.initObject(true);
        List<CoutStadeDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<CoutStadeDto> getDtoResponseEntity(CoutStadeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CoutStadeRestAdmin(CoutStadeAdminService service, CoutStadeMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final CoutStadeAdminService service;
    private final CoutStadeMapper mapper;





}
