package  ma.zyn.app.api.facade.admin.navire;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.navire.DestinationNavire;
import ma.zyn.app.service.facade.admin.navire.DestinationNavireAdminService;
import ma.zyn.app.ws.mapper.navire.DestinationNavireMapper;
import ma.zyn.app.ws.dto.navire.DestinationNavireDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/destinationNavire/")
public class DestinationNavireRestAdmin {




    @Operation(summary = "Finds a list of all destinationNavires")
    @GetMapping("")
    public ResponseEntity<List<DestinationNavireDto>> findAll(){
        ResponseEntity<List<DestinationNavireDto>> res = null;
        List<DestinationNavire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DestinationNavireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all destinationNavires")
    @GetMapping("optimized")
    public ResponseEntity<List<DestinationNavireDto>> findAllOptimized(){
        ResponseEntity<List<DestinationNavireDto>> res = null;
        List<DestinationNavire> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DestinationNavireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a destinationNavire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DestinationNavireDto> findById(@PathVariable Long id) {
        DestinationNavire t = service.findById(id);
        if (t != null) {
            DestinationNavireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a destinationNavire by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DestinationNavireDto> findByLibelle(@PathVariable String libelle) {
	    DestinationNavire t = service.findByReferenceEntity(new DestinationNavire(libelle));
        if (t != null) {
            DestinationNavireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  destinationNavire")
    @PostMapping("")
    public ResponseEntity<DestinationNavireDto> save(@RequestBody DestinationNavireDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            DestinationNavire myT = mapper.toItem(dto);
            DestinationNavire t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                DestinationNavireDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  destinationNavire")
    @PutMapping("")
    public ResponseEntity<DestinationNavireDto> update(@RequestBody DestinationNavireDto dto){
        ResponseEntity<DestinationNavireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DestinationNavire t = service.findById(dto.getId());
            mapper.copy(dto,t);
            DestinationNavire updated = service.update(t);
            DestinationNavireDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of destinationNavire")
    @PostMapping("multiple")
    public ResponseEntity<List<DestinationNavireDto>> delete(@RequestBody List<DestinationNavireDto> dtos){
        ResponseEntity<List<DestinationNavireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DestinationNavire> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified destinationNavire")
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


    @Operation(summary = "Finds a destinationNavire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DestinationNavireDto> findWithAssociatedLists(@PathVariable Long id) {
        DestinationNavire loaded =  service.findWithAssociatedLists(id);
        DestinationNavireDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<DestinationNavireDto> findDtos(List<DestinationNavire> list){
        List<DestinationNavireDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<DestinationNavireDto> getDtoResponseEntity(DestinationNavireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public DestinationNavireRestAdmin(DestinationNavireAdminService service, DestinationNavireMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final DestinationNavireAdminService service;
    private final DestinationNavireMapper mapper;





}
