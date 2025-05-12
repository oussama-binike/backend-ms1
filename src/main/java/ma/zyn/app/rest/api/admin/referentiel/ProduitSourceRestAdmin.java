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

import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.service.facade.admin.referentiel.ProduitSourceAdminService;
import ma.zyn.app.ws.mapper.referentiel.ProduitSourceMapper;
import ma.zyn.app.ws.dto.referentiel.ProduitSourceDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/produitSource/")
public class ProduitSourceRestAdmin {




    @Operation(summary = "Finds a list of all produitSources")
    @GetMapping("")
    public ResponseEntity<List<ProduitSourceDto>> findAll(){
        ResponseEntity<List<ProduitSourceDto>> res = null;
        List<ProduitSource> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitSourceDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all produitSources")
    @GetMapping("optimized")
    public ResponseEntity<List<ProduitSourceDto>> findAllOptimized(){
        ResponseEntity<List<ProduitSourceDto>> res = null;
        List<ProduitSource> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitSourceDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a produitSource by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProduitSourceDto> findById(@PathVariable Long id) {
        ProduitSource t = service.findById(id);
        if (t != null) {
            ProduitSourceDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a produitSource by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProduitSourceDto> findByLibelle(@PathVariable String libelle) {
	    ProduitSource t = service.findByReferenceEntity(new ProduitSource(libelle));
        if (t != null) {
            ProduitSourceDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  produitSource")
    @PostMapping("")
    public ResponseEntity<ProduitSourceDto> save(@RequestBody ProduitSourceDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            ProduitSource myT = mapper.toItem(dto);
            ProduitSource t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ProduitSourceDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  produitSource")
    @PutMapping("")
    public ResponseEntity<ProduitSourceDto> update(@RequestBody ProduitSourceDto dto){
        ResponseEntity<ProduitSourceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProduitSource t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ProduitSource updated = service.update(t);
            ProduitSourceDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of produitSource")
    @PostMapping("multiple")
    public ResponseEntity<List<ProduitSourceDto>> delete(@RequestBody List<ProduitSourceDto> dtos){
        ResponseEntity<List<ProduitSourceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProduitSource> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified produitSource")
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


    @Operation(summary = "Finds a produitSource and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProduitSourceDto> findWithAssociatedLists(@PathVariable Long id) {
        ProduitSource loaded =  service.findWithAssociatedLists(id);
        ProduitSourceDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ProduitSourceDto> findDtos(List<ProduitSource> list){
        List<ProduitSourceDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProduitSourceDto> getDtoResponseEntity(ProduitSourceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ProduitSourceRestAdmin(ProduitSourceAdminService service, ProduitSourceMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ProduitSourceAdminService service;
    private final ProduitSourceMapper mapper;





}
