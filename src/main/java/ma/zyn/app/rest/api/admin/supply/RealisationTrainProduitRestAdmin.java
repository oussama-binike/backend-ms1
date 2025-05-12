package  ma.zyn.app.api.facade.admin.supply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.supply.RealisationTrainProduit;
import ma.zyn.app.service.facade.admin.supply.RealisationTrainProduitAdminService;
import ma.zyn.app.ws.mapper.supply.RealisationTrainProduitMapper;
import ma.zyn.app.ws.dto.supply.RealisationTrainProduitDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/realisationTrainProduit/")
public class RealisationTrainProduitRestAdmin {




    @Operation(summary = "Finds a list of all realisationTrainProduits")
    @GetMapping("")
    public ResponseEntity<List<RealisationTrainProduitDto>> findAll(){
        ResponseEntity<List<RealisationTrainProduitDto>> res = null;
        List<RealisationTrainProduit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<RealisationTrainProduitDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a realisationTrainProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RealisationTrainProduitDto> findById(@PathVariable Long id) {
        RealisationTrainProduit t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            RealisationTrainProduitDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  realisationTrainProduit")
    @PostMapping("")
    public ResponseEntity<RealisationTrainProduitDto> save(@RequestBody RealisationTrainProduitDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            RealisationTrainProduit myT = mapper.toItem(dto);
            RealisationTrainProduit t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                RealisationTrainProduitDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  realisationTrainProduit")
    @PutMapping("")
    public ResponseEntity<RealisationTrainProduitDto> update(@RequestBody RealisationTrainProduitDto dto){
        ResponseEntity<RealisationTrainProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RealisationTrainProduit t = service.findById(dto.getId());
            mapper.copy(dto,t);
            RealisationTrainProduit updated = service.update(t);
            RealisationTrainProduitDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of realisationTrainProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<RealisationTrainProduitDto>> delete(@RequestBody List<RealisationTrainProduitDto> dtos){
        ResponseEntity<List<RealisationTrainProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<RealisationTrainProduit> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified realisationTrainProduit")
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


    @Operation(summary = "Finds a realisationTrainProduit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RealisationTrainProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        RealisationTrainProduit loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        RealisationTrainProduitDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<RealisationTrainProduitDto> findDtos(List<RealisationTrainProduit> list){
        mapper.initObject(true);
        List<RealisationTrainProduitDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<RealisationTrainProduitDto> getDtoResponseEntity(RealisationTrainProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RealisationTrainProduitRestAdmin(RealisationTrainProduitAdminService service, RealisationTrainProduitMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final RealisationTrainProduitAdminService service;
    private final RealisationTrainProduitMapper mapper;





}
