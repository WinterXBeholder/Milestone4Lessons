package learn.memories.domain;

import learn.memories.data.DataAccessException;
import learn.memories.data.MemoryRepository;
import learn.memories.models.Memory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //@Service works exactly like @Component, but it more accurately describes the class's role.
    // In future versions of Spring, the @Service annotation may provide service-specific capabilities.
public class MemoryService {

    private final MemoryRepository repository;
    /* MemoryService requires a MemoryRepository interface. We can't annotate the interface since there's no way to
    instantiate it, so we annotate the concrete MemoryFileRepository to satisfy the interface requirement. */

    public MemoryService(MemoryRepository repository) {
        this.repository = repository;
    }

    public List<Memory> findPublicMemories() throws DataAccessException {
        return repository.findShareable(true);
    }

    public List<Memory> findPrivateMemories() throws DataAccessException {
        return repository.findShareable(false);
    }

    public MemoryResult add(Memory memory) throws DataAccessException {
        MemoryResult result = validate(memory);

        if (memory.getId() > 0) {
            result.addErrorMessage("Memory `id` should not be set.");
        }

        if (result.isSuccess()) {
            memory = repository.add(memory);
            result.setMemory(memory);
        }
        return result;
    }

    public MemoryResult update(Memory memory) throws DataAccessException {
        MemoryResult result = validate(memory);

        if (memory.getId() <= 0) {
            result.addErrorMessage("Memory `id` is required.");
        }

        if (result.isSuccess()) {
            if (repository.update(memory)) {
                result.setMemory(memory);
            } else {
                String message = String.format("Memory id %s was not found.", memory.getId());
                result.addErrorMessage(message);
            }
        }
        return result;
    }

    public MemoryResult deleteById(int memoryId) throws DataAccessException {
        MemoryResult result = new MemoryResult();
        if (!repository.deleteById(memoryId)) {
            String message = String.format("Memory id %s was not found.", memoryId);
            result.addErrorMessage(message);
        }
        return result;
    }

    private MemoryResult validate(Memory memory) {
        MemoryResult result = new MemoryResult();

        if (memory == null) {
            result.addErrorMessage("Memory cannot be null.");
            return result;
        }

        if (memory.getFrom() == null || memory.getFrom().isBlank()) {
            result.addErrorMessage("Memory `from` is required.");
        }

        if (memory.getContent() == null || memory.getContent().isBlank()) {
            result.addErrorMessage("Memory `content` is required.");
        }

        return result;
    }
}
