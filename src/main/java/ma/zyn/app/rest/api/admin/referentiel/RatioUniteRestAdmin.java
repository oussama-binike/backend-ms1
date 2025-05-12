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

import ma.zyn.app.bean.core.referentiel.RatioUnite;
import ma.zyn.app.service.facade.admin.referentiel.RatioUniteAdminService;
import ma.zyn.app.ws.mapper.referentiel.RatioUniteMapper;
import ma.zyn.app.ws.dto.referentiel.RatioUniteDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/ratioUnite/")
public class RatioUniteRestAdmin {




    @Operation(summary = "Finds a list of all ratioUnites")
    @GetMapping("")
    public ResponseEntity<List<RatioUniteDto>> findAll(){
        ResponseEntity<List<RatioUniteDto>> res = null;
        List<RatioUnite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<RatioUniteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a ratioUnite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RatioUniteDto> findById(@PathVariable Long id) {
        RatioUnite t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            RatioUniteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  ratioUnite")
    @PostMapping("")
    public ResponseEntity<RatioUniteDto> save(@RequestBody RatioUniteDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            RatioUnite myT = mapper.toItem(dto);
            RatioUnite t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                RatioUniteDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  ratioUnite")
    @PutMapping("")
    public ResponseEntity<RatioUniteDto> update(@RequestBody RatioUniteDto dto){
        ResponseEntity<RatioUniteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RatioUnite t = service.findById(dto.getId());
            mapper.copy(dto,t);
            RatioUnite updated = service.update(t);
            RatioUniteDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of ratioUnite")
    @PostMapping("multiple")
    public ResponseEntity<List<RatioUniteDto>> delete(@RequestBody List<RatioUniteDto> dtos){
        ResponseEntity<List<RatioUniteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<RatioUnite> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified ratioUnite")
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


    @Operation(summary = "Finds a ratioUnite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RatioUniteDto> findWithAssociatedLists(@PathVariable Long id) {
        RatioUnite loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        RatioUniteDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<RatioUniteDto> findDtos(List<RatioUnite> list){
        mapper.initObject(true);
        List<RatioUniteDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<RatioUniteDto> getDtoResponseEntity(RatioUniteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RatioUniteRestAdmin(RatioUniteAdminService service, RatioUniteMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final RatioUniteAdminService service;
    private final RatioUniteMapper mapper;





}
