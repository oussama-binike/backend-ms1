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

import ma.zyn.app.bean.core.referentiel.Produit;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.ws.dto.referentiel.ProduitDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/produit/")
public class ProduitRestAdmin {




    @Operation(summary = "Finds a list of all produits")
    @GetMapping("")
    public ResponseEntity<List<ProduitDto>> findAll(){
        ResponseEntity<List<ProduitDto>> res = null;
        List<Produit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all produits")
    @GetMapping("optimized")
    public ResponseEntity<List<ProduitDto>> findAllOptimized(){
        ResponseEntity<List<ProduitDto>> res = null;
        List<Produit> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a produit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProduitDto> findById(@PathVariable Long id) {
        Produit t = service.findById(id);
        if (t != null) {
            ProduitDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a produit by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProduitDto> findByLibelle(@PathVariable String libelle) {
	    Produit t = service.findByReferenceEntity(new Produit(libelle));
        if (t != null) {
            ProduitDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  produit")
    @PostMapping("")
    public ResponseEntity<ProduitDto> save(@RequestBody ProduitDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            Produit myT = mapper.toItem(dto);
            Produit t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ProduitDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  produit")
    @PutMapping("")
    public ResponseEntity<ProduitDto> update(@RequestBody ProduitDto dto){
        ResponseEntity<ProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Produit t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Produit updated = service.update(t);
            ProduitDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of produit")
    @PostMapping("multiple")
    public ResponseEntity<List<ProduitDto>> delete(@RequestBody List<ProduitDto> dtos){
        ResponseEntity<List<ProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Produit> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified produit")
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


    @Operation(summary = "Finds a produit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        Produit loaded =  service.findWithAssociatedLists(id);
        ProduitDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ProduitDto> findDtos(List<Produit> list){
        List<ProduitDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProduitDto> getDtoResponseEntity(ProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ProduitRestAdmin(ProduitAdminService service, ProduitMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ProduitAdminService service;
    private final ProduitMapper mapper;





}
