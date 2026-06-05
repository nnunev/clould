package mindscratch.clould.service;

import mindscratch.clould.dto.VMRequest;
import mindscratch.clould.dto.VMResponse;
import mindscratch.clould.entity.VirtualMachine;
import mindscratch.clould.exception.VMNotFoundException;
import mindscratch.clould.repository.VMRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VMService {

    private final VMRepository repository;

    public VMService(VMRepository repository) {
        this.repository = repository;
    }

    public List<VMResponse> getAll() {

        System.out.println("Loading from PostgreSQL...");
        System.out.println("Repository count = " + repository.count());

        List<VirtualMachine> vms = repository.findAll();

        System.out.println("VM count = " + vms.size());

        return vms.stream()
                .map(this::mapToResponse)
                .toList();
    }

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

    public VMResponse update(Long id, VMRequest request) {

        VirtualMachine vm = repository.findById(id)
                .orElseThrow(() -> new VMNotFoundException(id));

        vm.setName(request.getName());
        vm.setStatus(request.getStatus());
        vm.setCpu(request.getCpu());
        vm.setRam(request.getRam());

        return mapToResponse(repository.save(vm));
    }

    public void delete(Long id) {
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
