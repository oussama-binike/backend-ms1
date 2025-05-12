package  ma.zyn.app.api.facade.admin.camion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.camion.RealisationCamionProduit;
import ma.zyn.app.service.facade.admin.camion.RealisationCamionProduitAdminService;
import ma.zyn.app.ws.mapper.camion.RealisationCamionProduitMapper;
import ma.zyn.app.ws.dto.camion.RealisationCamionProduitDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/realisationCamionProduit/")
public class RealisationCamionProduitRestAdmin {




    @Operation(summary = "Finds a list of all realisationCamionProduits")
    @GetMapping("")
    public ResponseEntity<List<RealisationCamionProduitDto>> findAll(){
        ResponseEntity<List<RealisationCamionProduitDto>> res = null;
        List<RealisationCamionProduit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<RealisationCamionProduitDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a realisationCamionProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RealisationCamionProduitDto> findById(@PathVariable Long id) {
        RealisationCamionProduit t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            RealisationCamionProduitDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  realisationCamionProduit")
    @PostMapping("")
    public ResponseEntity<RealisationCamionProduitDto> save(@RequestBody RealisationCamionProduitDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            RealisationCamionProduit myT = mapper.toItem(dto);
            RealisationCamionProduit t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                RealisationCamionProduitDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  realisationCamionProduit")
    @PutMapping("")
    public ResponseEntity<RealisationCamionProduitDto> update(@RequestBody RealisationCamionProduitDto dto){
        ResponseEntity<RealisationCamionProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RealisationCamionProduit t = service.findById(dto.getId());
            mapper.copy(dto,t);
            RealisationCamionProduit updated = service.update(t);
            RealisationCamionProduitDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of realisationCamionProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<RealisationCamionProduitDto>> delete(@RequestBody List<RealisationCamionProduitDto> dtos){
        ResponseEntity<List<RealisationCamionProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<RealisationCamionProduit> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified realisationCamionProduit")
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


    @Operation(summary = "Finds a realisationCamionProduit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RealisationCamionProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        RealisationCamionProduit loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        RealisationCamionProduitDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<RealisationCamionProduitDto> findDtos(List<RealisationCamionProduit> list){
        mapper.initObject(true);
        List<RealisationCamionProduitDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<RealisationCamionProduitDto> getDtoResponseEntity(RealisationCamionProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RealisationCamionProduitRestAdmin(RealisationCamionProduitAdminService service, RealisationCamionProduitMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final RealisationCamionProduitAdminService service;
    private final RealisationCamionProduitMapper mapper;





}
