package mindscratch.clould.controller;

import mindscratch.clould.dto.VMRequest;
import mindscratch.clould.dto.VMResponse;
import mindscratch.clould.service.VMService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vm")
public class VMController {

    private final VMService service;

    public VMController(VMService service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public List<VMResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VMResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public VMResponse create(@RequestBody VMRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public VMResponse update(
            @PathVariable Long id,
            @RequestBody VMRequest request) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
