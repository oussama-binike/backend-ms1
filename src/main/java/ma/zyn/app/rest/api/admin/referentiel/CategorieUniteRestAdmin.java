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

import ma.zyn.app.bean.core.referentiel.CategorieUnite;
import ma.zyn.app.service.facade.admin.referentiel.CategorieUniteAdminService;
import ma.zyn.app.ws.mapper.referentiel.CategorieUniteMapper;
import ma.zyn.app.ws.dto.referentiel.CategorieUniteDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/categorieUnite/")
public class CategorieUniteRestAdmin {




    @Operation(summary = "Finds a list of all categorieUnites")
    @GetMapping("")
    public ResponseEntity<List<CategorieUniteDto>> findAll(){
        ResponseEntity<List<CategorieUniteDto>> res = null;
        List<CategorieUnite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieUniteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all categorieUnites")
    @GetMapping("optimized")
    public ResponseEntity<List<CategorieUniteDto>> findAllOptimized(){
        ResponseEntity<List<CategorieUniteDto>> res = null;
        List<CategorieUnite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieUniteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a categorieUnite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategorieUniteDto> findById(@PathVariable Long id) {
        CategorieUnite t = service.findById(id);
        if (t != null) {
            CategorieUniteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a categorieUnite by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CategorieUniteDto> findByLibelle(@PathVariable String libelle) {
	    CategorieUnite t = service.findByReferenceEntity(new CategorieUnite(libelle));
        if (t != null) {
            CategorieUniteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  categorieUnite")
    @PostMapping("")
    public ResponseEntity<CategorieUniteDto> save(@RequestBody CategorieUniteDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            CategorieUnite myT = mapper.toItem(dto);
            CategorieUnite t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                CategorieUniteDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  categorieUnite")
    @PutMapping("")
    public ResponseEntity<CategorieUniteDto> update(@RequestBody CategorieUniteDto dto){
        ResponseEntity<CategorieUniteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CategorieUnite t = service.findById(dto.getId());
            mapper.copy(dto,t);
            CategorieUnite updated = service.update(t);
            CategorieUniteDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of categorieUnite")
    @PostMapping("multiple")
    public ResponseEntity<List<CategorieUniteDto>> delete(@RequestBody List<CategorieUniteDto> dtos){
        ResponseEntity<List<CategorieUniteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CategorieUnite> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified categorieUnite")
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


    @Operation(summary = "Finds a categorieUnite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CategorieUniteDto> findWithAssociatedLists(@PathVariable Long id) {
        CategorieUnite loaded =  service.findWithAssociatedLists(id);
        CategorieUniteDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<CategorieUniteDto> findDtos(List<CategorieUnite> list){
        List<CategorieUniteDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<CategorieUniteDto> getDtoResponseEntity(CategorieUniteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CategorieUniteRestAdmin(CategorieUniteAdminService service, CategorieUniteMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final CategorieUniteAdminService service;
    private final CategorieUniteMapper mapper;





}
