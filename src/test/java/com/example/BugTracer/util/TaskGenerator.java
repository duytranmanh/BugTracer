package com.example.BugTracer.util;

import com.example.BugTracer.dto.TaskDTO;
import com.sb.factorium.FakerGenerator;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class TaskGenerator extends FakerGenerator<TaskDTO> {

    private final Faker faker = new Faker();

    @Override
    protected TaskDTO make() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(faker.lorem().sentence());
        taskDTO.setDescription(faker.lorem().paragraph());
        // Set other fields as needed

        return taskDTO;
    }
}
