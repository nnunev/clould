package mindscratch.clould.service;

import mindscratch.clould.dto.VMRequest;
import mindscratch.clould.dto.VMResponse;
import mindscratch.clould.entity.VirtualMachine;
import mindscratch.clould.exception.VMNotFoundException;
import mindscratch.clould.repository.VMRepository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VMService {

    private final VMRepository repository;

    public VMService(VMRepository repository) {
        this.repository = repository;
    }

    @Cacheable("vms")
    public List<VMResponse> getAll() {

        System.out.println("Loading from PostgreSQL...");

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "vms", allEntries = true)
    public VMResponse create(VMRequest request) {

        VirtualMachine vm = new VirtualMachine();

        vm.setName(request.getName());
        vm.setStatus(request.getStatus());
        vm.setCpu(request.getCpu());
        vm.setRam(request.getRam());

        VirtualMachine saved = repository.save(vm);

        return mapToResponse(saved);
    }

    public VMResponse getById(Long id) {

        VirtualMachine vm = repository.findById(id)
                .orElseThrow(() -> new VMNotFoundException(id));

        return mapToResponse(vm);
    }

    @CacheEvict(value = "vms", allEntries = true)
    public VMResponse update(Long id, VMRequest request) {

        VirtualMachine vm = repository.findById(id)
                .orElseThrow(() -> new VMNotFoundException(id));

        vm.setName(request.getName());
        vm.setStatus(request.getStatus());
        vm.setCpu(request.getCpu());
        vm.setRam(request.getRam());

        VirtualMachine updated = repository.save(vm);

        return mapToResponse(updated);
    }

    @CacheEvict(value = "vms", allEntries = true)
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new VMNotFoundException(id);
        }

        repository.deleteById(id);
    }

    private VMResponse mapToResponse(VirtualMachine vm) {

        VMResponse response = new VMResponse();

        response.setId(vm.getId());
        response.setName(vm.getName());
        response.setStatus(vm.getStatus());
        response.setCpu(vm.getCpu());
        response.setRam(vm.getRam());

        return response;
    }
}



// package mindscratch.clould.service;

// import mindscratch.clould.dto.VMRequest;
// import mindscratch.clould.dto.VMResponse;
// import mindscratch.clould.entity.VirtualMachine;
// import mindscratch.clould.exception.VMNotFoundException;
// import mindscratch.clould.repository.VMRepository;
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.cache.annotation.Cacheable;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class VMService {

//     private final VMRepository repository;

//     public VMService(VMRepository repository) {
//         this.repository = repository;
//     }

//     @Cacheable("vms")
//     public List<VMResponse> getAll() {

//         System.out.println("Loading from PostgreSQL...");

//         return repository.findAll()
//                 .stream()
//                 .map(this::mapToResponse)
//                 .toList();
//     }

//     public VMResponse getById(Long id) {

//         VirtualMachine vm = repository.findById(id)
//                 .orElseThrow(() -> new VMNotFoundException(id));

//         return mapToResponse(vm);
//     }

//     @CacheEvict(value = "vms", allEntries = true)
//     public VMResponse create(VMRequest request) {

//         VirtualMachine vm = new VirtualMachine();

//         vm.setName(request.getName());
//         vm.setStatus(request.getStatus());
//         vm.setCpu(request.getCpu());
//         vm.setRam(request.getRam());

//         VirtualMachine saved = repository.save(vm);

//         return mapToResponse(saved);
//     }

//     @CacheEvict(value = "vms", allEntries = true)
//     public VMResponse update(Long id, VMRequest request) {

//         VirtualMachine vm = repository.findById(id)
//                 .orElseThrow(() -> new VMNotFoundException(id));

//         vm.setName(request.getName());
//         vm.setStatus(request.getStatus());
//         vm.setCpu(request.getCpu());
//         vm.setRam(request.getRam());

//         return mapToResponse(repository.save(vm));
//     }

//     @CacheEvict(value = "vms", allEntries = true)
//     public void delete(Long id) {

//         if (!repository.existsById(id)) {
//             throw new VMNotFoundException(id);
//         }

//         repository.deleteById(id);
//     }

//     private VMResponse mapToResponse(VirtualMachine vm) {

//         VMResponse response = new VMResponse();

//         response.setId(vm.getId());
//         response.setName(vm.getName());
//         response.setStatus(vm.getStatus());
//         response.setCpu(vm.getCpu());
//         response.setRam(vm.getRam());

//         return response;
//     }
// }
